#ifndef CUBIOMES_JAVA_CUBIJ_UTILS_H
#define CUBIOMES_JAVA_CUBIJ_UTILS_H

#include <jni.h>
#include <assert.h>
#include <generator.h>
#include <finders.h>

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

static inline int wrapEnumArray(JNIEnv *env, jobjectArray enumArray, int **target) {
    int length = (*env)->GetArrayLength(env, enumArray);
    *target = malloc(length);
    for (int i = 0; i < length; ++i) {
        jobject elem = (*env)->GetObjectArrayElement(env, enumArray, i);
        (*target)[i] = getEnumValue(env, elem);
    }
    return length;
}

static inline BiomeFilter wrapBiomeFilter(JNIEnv *env, jobject filter, int mc) {
    BiomeFilter biomeFilter;
    jclass clsBiomeFilter = (*env)->GetObjectClass(env, filter);
    assert(clsBiomeFilter != NULL);
    jfieldID fidRequired = (*env)->GetFieldID(env, clsBiomeFilter, "required", "[Lde/rasmusantons/cubiomes/BiomeID;");
    assert(fidRequired != NULL);
    jobjectArray arrRequired = (*env)->GetObjectField(env, filter, fidRequired);
    assert(arrRequired != NULL);
    jfieldID fidExcluded = (*env)->GetFieldID(env, clsBiomeFilter, "excluded", "[Lde/rasmusantons/cubiomes/BiomeID;");
    assert(fidExcluded != NULL);
    jobjectArray arrExcluded = (*env)->GetObjectField(env, filter, fidExcluded);
    assert(arrExcluded != NULL);
    jfieldID fidMatchany = (*env)->GetFieldID(env, clsBiomeFilter, "matchany", "[Lde/rasmusantons/cubiomes/BiomeID;");
    assert(fidMatchany != NULL);
    jobjectArray arrMatchany = (*env)->GetObjectField(env, filter, fidMatchany);
    assert(arrMatchany != NULL);
    int *required, *excluded, *matchany;
    int nRequired = wrapEnumArray(env, arrRequired, &required);
    int nExcluded = wrapEnumArray(env, arrExcluded, &excluded);
    int nMatchany = wrapEnumArray(env, arrMatchany, &matchany);
    setupBiomeFilter(&biomeFilter, mc, 0, required, nRequired, excluded, nExcluded, matchany, nMatchany);
    free(required);
    free(excluded);
    free(matchany);
    return biomeFilter;
}

#endif //CUBIOMES_JAVA_CUBIJ_UTILS_H
