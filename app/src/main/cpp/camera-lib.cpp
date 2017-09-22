//
// Created by Administrator on 2017/9/22.
//
#include <jni.h>
#include <string>
#include <opencv2/core.hpp>

using namespace std;

extern "C"{
    JNIEXPORT void JNICALL
    Java_com_narkang_opencvjni_CameraActivity_grayProc(JNIEnv *env, jobject instance, jlong imageGray);
}

using namespace cv;

JNIEXPORT void JNICALL
Java_com_narkang_opencvjni_CameraActivity_grayProc(JNIEnv *env, jobject instance, jlong imageGray) {
    int i;
    int width,height;

    Mat mat = Mat(*((Mat*)imageGray));
    width = mat.rows;
    height = mat.cols;
    uchar* ptr = mat.ptr(0);
    uchar* ptr_tmp;
    cv::Mat edges;

    for(int i = 0; i < width*height; i++){
        //计算公式：Y(亮度) = 0.299*R + 0.587*G + 0.114*B
        //对于一个int四字节，其彩色值存储方式为：BGRA
        int grayScale = (int)(ptr[4*i+2]*0.299 + ptr[4*i+1]*0.587 + ptr[4*i+0]*0.114);
        ptr[4*i+1] = grayScale;
        ptr[4*i+2] = grayScale;
        ptr[4*i+0] = grayScale;
    }

}