// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("lolstatus");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("lolstatus")
//      }
//    }
#include "jni.h"
#include "string"
#include <android/sensor.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_aweirdtrashcan_lolstatus_feature_1status_presentation_screen_1lol_1status_ScreenLoLStatusViewModel_getApiKey(
        JNIEnv *env, jobject thiz
        ) {
    std::string fdkjgkjfoigjdfoigj = "RGAPI-2025353f-5745-400e-9270-2caa2b66b025";
    return env->NewStringUTF(fdkjgkjfoigjdfoigj.c_str());
}