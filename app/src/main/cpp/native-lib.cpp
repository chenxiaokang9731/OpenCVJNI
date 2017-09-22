#include <jni.h>
#include <string>
#include <opencv2/core.hpp>

extern "C"{
    JNIEXPORT jstring JNICALL
    Java_com_narkang_opencvjni_MainActivity_stringFrom(JNIEnv *env, jobject instance);
    JNIEXPORT jintArray JNICALL
    Java_com_narkang_opencvjni_GrayActivity_getGrayImage(JNIEnv *env, jobject instance,
                                                         jintArray pixels_, jint w, jint h);
}



JNIEXPORT jstring JNICALL
Java_com_narkang_opencvjni_MainActivity_stringFrom(JNIEnv *env, jobject instance) {

    // TODO
    std::string hello = "Hello from C++";

    return env->NewStringUTF(hello.c_str());
}

JNIEXPORT jintArray JNICALL
Java_com_narkang_opencvjni_GrayActivity_getGrayImage(JNIEnv *env, jobject instance,
                                                     jintArray pixels_, jint w, jint h) {
    jint *pixels = env->GetIntArrayElements(pixels_, NULL);
    // TODO
    if(pixels==NULL){
        return NULL;
    }
    cv::Mat imgData(h, w, CV_8UC4, pixels);
    uchar *ptr = imgData.ptr(0);
    for (int i = 0; i < w * h; i++) {
//        int grayScale = (int) (ptr[4 * i + 2] * 0.299 + ptr[4 * i + 1] * 0.587
//                               + ptr[4 * i + 0] * 0.114);
        int grayScale = ptr[4*i+2] * 0 + ptr[4*i+1] * 1 + ptr[4*i+0]*1;
        ptr[4 * i + 1] = (uchar) grayScale;
        ptr[4 * i + 2] = (uchar) grayScale;
        ptr[4 * i + 0] = (uchar) grayScale;
    }

    int size = w * h;
    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, pixels);
    env->ReleaseIntArrayElements(pixels_, pixels, 0);
    return result;
}