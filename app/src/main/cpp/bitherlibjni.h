//
// Created by wzq on 16-12-26.
//
#include <jni.h>
#ifndef COMPRESSIMG_BITHERLIBJNI_H
#define COMPRESSIMG_BITHERLIBJNI_H
#include <stdint.h>
#endif //COMPRESSIMG_BITHERLIBJNI_H
#ifdef __cplusplus
extern "C" {
#endif
int generateJPEG(uint8_t* data, int w, int h, int quality,
                 const char* outfilename, jboolean optimize);


char* jstrinTostring(JNIEnv* env, jbyteArray barr);



jbyteArray stoJstring(JNIEnv* env, const char* pat,int len);



jstring Java_net_bither_util_NativeUtil_compressBitmap(JNIEnv* env,
                                                       jclass thiz, jobject bitmapcolor, int w, int h, int quality,
                                                       jbyteArray fileNameStr, jboolean optimize);


void jstringTostring(JNIEnv* env, jstring jstr, char * output, int * de_len);
#ifdef __cplusplus
}
#endif
