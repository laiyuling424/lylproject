#include <jni.h>
#include <string>

extern "C" {
extern int bsPatch_main(int argc, const char *argv[]);
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