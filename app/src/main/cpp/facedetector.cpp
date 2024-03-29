//
// Created by 36574 on 2019-07-11.
//

#include "facedetector.h"

CascadeClassifier cascadeClassifier;

void facedetector::loadcascade(const char *filepath) {
    cascadeClassifier.load(filepath);
}

//人脸检测
int facedetector::detectorface(Mat &src) {
    vector<Rect> faces;
    Mat gray;

    //灰度图
    cvtColor(src, gray, COLOR_BGRA2GRAY);
    //直方图均衡化
    equalizeHist(gray, gray);

    //多尺度人脸检测
    cascadeClassifier.detectMultiScale(gray, faces, 2, 3, 0);
    //在源图上画出人脸
    for (int i = 0; i < faces.size(); i++) {
        rectangle(src, faces[i], Scalar(255, 0, 255), 2);
    }

    return 1;
}