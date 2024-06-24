#ifndef CUBIOMES_JAVA_CUBIJ_UTILS_H
#define CUBIOMES_JAVA_CUBIJ_UTILS_H

#include <jni.h>
#include <assert.h>
#include <generator.h>

static inline Generator *wrapGenerator(JNIEnv *env, jobject thisObj) {
    jclass thisClass = (*env)->GetObjectClass(env, thisObj);
    jfieldID fidState = (*env)->GetFieldID(env, thisClass, "generator", "Ljava/nio/ByteBuffer;");
    assert(fidState != NULL);
    jobject state = (*env)->GetObjectField(env, thisObj, fidState);
    return (*env)->GetDirectBufferAddress(env, state);
}

static inline int getEnumValue(JNIEnv *env, jobject jEnum) {
    jclass classMCVersion = (*env)->GetObjectClass(env, jEnum);
    jmethodID midValue = (*env)->GetMethodID(env, classMCVersion, "getValue", "()I");
    return (*env)->CallIntMethod(env, jEnum, midValue);
}

static inline jobject toEnumValue(JNIEnv *env, char *enumName, char *enumSignature, int value) {
    jclass clsEnum = (*env)->FindClass(env, enumName);
    assert(clsEnum != NULL);
    jmethodID midFromValue = (*env)->GetStaticMethodID(env, clsEnum, "fromValue", enumSignature);
    assert(midFromValue != NULL);
    return (*env)->CallStaticObjectMethod(env, clsEnum, midFromValue, value);
}

static inline Range wrapRange(JNIEnv *env, jobject r) {
    Range range;
    jclass clsRange = (*env)->GetObjectClass(env, r);
    range.scale = (*env)->GetIntField(env, r, (*env)->GetFieldID(env, clsRange, "scale", "I"));
    range.x = (*env)->GetIntField(env, r, (*env)->GetFieldID(env, clsRange, "x", "I"));
    range.z = (*env)->GetIntField(env, r, (*env)->GetFieldID(env, clsRange, "z", "I"));
    range.sx = (*env)->GetIntField(env, r, (*env)->GetFieldID(env, clsRange, "sx", "I"));
    range.sz = (*env)->GetIntField(env, r, (*env)->GetFieldID(env, clsRange, "sz", "I"));
    range.y = (*env)->GetIntField(env, r, (*env)->GetFieldID(env, clsRange, "y", "I"));
    range.sy = (*env)->GetIntField(env, r, (*env)->GetFieldID(env, clsRange, "sy", "I"));
    return range;
}

#endif //CUBIOMES_JAVA_CUBIJ_UTILS_H
