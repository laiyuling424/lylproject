#include <jni.h>
#include <string>
#include "opencv2/opencv.hpp"
#include <android/log.h>
#include "opencv2/face.hpp"
#include <vector>
#include <android/bitmap.h>

#include "facedetector.h"

using namespace cv;
using namespace face;
using namespace std;
#define TAG "FACE_TAG"

#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)

extern "C" {
extern int bsPatch_main(int argc, const char *argv[]);
}

//将Mat转换为bitmap
jobject mat2bitmap(JNIEnv *env, cv::Mat &src, bool needPremultiplyAlpha, jobject bitmap_config) {
    jclass java_bitmap_class = (jclass) env->FindClass("android/graphics/Bitmap");
    jmethodID mid = env->GetStaticMethodID(java_bitmap_class, "createBitmap",
                                           "(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;");
    jobject bitmap = env->CallStaticObjectMethod(java_bitmap_class,
                                                 mid, src.size().width, src.size().height,
                                                 bitmap_config);
    AndroidBitmapInfo info;
    void *pixels = 0;

    try {
        CV_Assert(AndroidBitmap_getInfo(env, bitmap, &info) >= 0);
        CV_Assert(src.type() == CV_8UC1 || src.type() == CV_8UC3 || src.type() == CV_8UC4);
        CV_Assert(AndroidBitmap_lockPixels(env, bitmap, &pixels) >= 0);
        CV_Assert(pixels);

        if (info.format == ANDROID_BITMAP_FORMAT_RGBA_8888) {
            cv::Mat tmp(info.height, info.width, CV_8UC4, pixels);
            if (src.type() == CV_8UC1) {
                cvtColor(src, tmp, cv::COLOR_GRAY2RGBA);
            } else if (src.type() == CV_8UC3) {
                cvtColor(src, tmp, cv::COLOR_RGB2BGRA);
            } else if (src.type() == CV_8UC4) {
                if (needPremultiplyAlpha) {
                    cvtColor(src, tmp, cv::COLOR_RGBA2mRGBA);
                } else {
                    src.copyTo(tmp);
                }
            }
        } else {
            // info.format == ANDROID_BITMAP_FORMAT_RGB_565
            cv::Mat tmp(info.height, info.width, CV_8UC2, pixels);
            if (src.type() == CV_8UC1) {
                cvtColor(src, tmp, cv::COLOR_GRAY2BGR565);
            } else if (src.type() == CV_8UC3) {
                cvtColor(src, tmp, cv::COLOR_RGB2BGR565);
            } else if (src.type() == CV_8UC4) {
                cvtColor(src, tmp, cv::COLOR_RGBA2BGR565);
            }
        }
        AndroidBitmap_unlockPixels(env, bitmap);
        return bitmap;
    } catch (cv::Exception e) {
        AndroidBitmap_unlockPixels(env, bitmap);
//        jclass je = env->FindClass("org/opencv/core/CvException");
//        if (!je)
        jclass je = env->FindClass("java/lang/Exception");
        env->ThrowNew(je, e.what());
        return bitmap;
    } catch (...) {
        AndroidBitmap_unlockPixels(env, bitmap);
        jclass je = env->FindClass("java/lang/Exception");
        env->ThrowNew(je, "Unknown exception in JNI code {nMatToBitmap}");
        return bitmap;
    }
}


extern "C"
JNIEXPORT void JNICALL
Java_com_lyl_mvptest_aboutc_JniClass_bsPatch(JNIEnv *env, jclass instance, jstring oldApk_,
                                             jstring patch_, jstring output_) {
    const char *oldApk = env->GetStringUTFChars(oldApk_, 0);
    const char *patch = env->GetStringUTFChars(patch_, 0);
    const char *output = env->GetStringUTFChars(output_, 0);

    // TODO
    const char *argv[] = {"", oldApk, output, patch};
    bsPatch_main(4, argv);

    env->ReleaseStringUTFChars(oldApk_, oldApk);
    env->ReleaseStringUTFChars(patch_, patch);
    env->ReleaseStringUTFChars(output_, output);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_lyl_mvptest_aboutc_JniClass_loadcascade(JNIEnv *env, jclass clazz, jstring filePath_) {
    const char *filePath = env->GetStringUTFChars(filePath_, 0);

    facedetector::loadcascade(filePath);
    LOGE("人脸识别级联分类器加载成功");
    env->ReleaseStringUTFChars(filePath_, filePath);
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_lyl_mvptest_aboutc_JniClass_getCameraFrameBitbmp(JNIEnv *env, jclass clazz, jobject bmp) {

    //解析bitmap

    //将bitmap转成mat


    AndroidBitmapInfo bitmapInfo;
    void *pixelscolor;
    int ret;

    //获取图像信息，如果返回值小于0就是执行失败
    if ((ret = AndroidBitmap_getInfo(env, bmp, &bitmapInfo)) < 0) {
        LOGE("AndroidBitmap_getInfo failed! error-%d", ret);
        return NULL;
    }

    //判断图像类型是不是RGBA_8888类型
    if (bitmapInfo.format != ANDROID_BITMAP_FORMAT_RGBA_8888) {
        LOGE("BitmapInfoFormat error");
        return NULL;
    }

    //获取图像像素值
    if ((ret = AndroidBitmap_lockPixels(env, bmp, &pixelscolor)) < 0) {
        LOGE("AndroidBitmap_lockPixels() failed ! error=%d", ret);
        return NULL;
    }

    AndroidBitmap_unlockPixels(env, bmp);

    //生成源图像
    Mat src(bitmapInfo.height, bitmapInfo.width, CV_8UC4, pixelscolor);

    //图像处理
//    std::vector<cv::Mat> outdsts=testcv::getrectdetector(src);
//    std::vector<cv::Mat> outdsts = facedetector::detectorface(src);

    //识别mat

    int dst = facedetector::detectorface(src);

    //获取原图片的参数
    jclass java_bitmap_class = (jclass) env->FindClass("android/graphics/Bitmap");
    jmethodID mid = env->GetMethodID(java_bitmap_class, "getConfig",
                                     "()Landroid/graphics/Bitmap$Config;");
    jobject bitmap_config = env->CallObjectMethod(bmp, mid);
    //将SRC转换为图片
    //将识别的mat转成bitmap并返回
    jobject _bitmap = mat2bitmap(env, src, false, bitmap_config);

//    env->CallBooleanMethod(list_obj, list_add, _bitmap);
//
//    //判断有截出的图像后加入到返回的List<Bitmap>列表中
//    if(outdsts.size()>0) {
//        for (int i = 0; i < outdsts.size(); i++) {
//            jobject dstbmp = mat2bitmap(env, outdsts[i], false, bitmap_config);
//            env->CallBooleanMethod(list_obj, list_add, dstbmp);
//        }
//    }




    return _bitmap;
}