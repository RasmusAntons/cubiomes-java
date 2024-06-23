#include <jni.h>
#include <stdio.h>
#include <assert.h>
#include <generator.h>
#include "de_rasmusantons_cubiomes_Generator.h"

static inline Generator *wrapGenerator(JNIEnv *env, jobject thisObj) {
    jclass thisClass = (*env)->GetObjectClass(env, thisObj);
    jfieldID fidState = (*env)->GetFieldID(env, thisClass, "state", "Ljava/nio/ByteBuffer;");
    jobject state = (*env)->GetObjectField(env, thisObj, fidState);
    return (*env)->GetDirectBufferAddress(env, state);
}

static inline int getEnumValue(JNIEnv *env, jobject jEnum) {
    jclass classMCVersion = (*env)->GetObjectClass(env, jEnum);
    jmethodID midValue = (*env)->GetMethodID(env, classMCVersion, "getValue", "()I");
    return (*env)->CallIntMethod(env, jEnum, midValue);
}

static inline jobject toEnumValue(JNIEnv *env, char *enumName, char *enumSignature, int value) {
    jclass classEnum = (*env)->FindClass(env, enumName);
    assert(classEnum != NULL);
    jmethodID midFromValue = (*env)->GetStaticMethodID(env, classEnum, "fromValue", enumSignature);
    assert(midFromValue != NULL);
    return (*env)->CallStaticObjectMethod(env, classEnum, midFromValue, value);
}

static inline Range wrapRange(JNIEnv *env, jobject r) {
    Range range;
    jclass classRange = (*env)->GetObjectClass(env, r);
    range.scale = (*env)->GetIntField(env, r, (*env)->GetFieldID(env, classRange, "scale", "I"));
    range.x = (*env)->GetIntField(env, r, (*env)->GetFieldID(env, classRange, "x", "I"));
    range.z = (*env)->GetIntField(env, r, (*env)->GetFieldID(env, classRange, "z", "I"));
    range.sx = (*env)->GetIntField(env, r, (*env)->GetFieldID(env, classRange, "sx", "I"));
    range.sz = (*env)->GetIntField(env, r, (*env)->GetFieldID(env, classRange, "sz", "I"));
    range.y = (*env)->GetIntField(env, r, (*env)->GetFieldID(env, classRange, "y", "I"));
    range.sy = (*env)->GetIntField(env, r, (*env)->GetFieldID(env, classRange, "sy", "I"));
    return range;
}

JNIEXPORT jint JNICALL Java_de_rasmusantons_cubiomes_Generator_getStateSize(JNIEnv *env, jobject thisObj) {
    return sizeof(Generator);
}

JNIEXPORT void JNICALL Java_de_rasmusantons_cubiomes_Generator_setupGenerator(JNIEnv *env, jobject thisObj, jobject mc, jint flags) {
    Generator *g = wrapGenerator(env, thisObj);
    setupGenerator(g, getEnumValue(env, mc), 0);
}

JNIEXPORT void JNICALL Java_de_rasmusantons_cubiomes_Generator_applySeed(JNIEnv *env, jobject thisObj, jobject dimension, jlong seed) {
    Generator *g = wrapGenerator(env, thisObj);
    applySeed(g, getEnumValue(env, dimension), seed);
}

JNIEXPORT jobject JNICALL Java_de_rasmusantons_cubiomes_Generator_getBiomeAt(JNIEnv *env, jobject thisObj, jint scale, jint x, jint y, jint z) {
    Generator *g = wrapGenerator(env, thisObj);
    enum BiomeID id = getBiomeAt(g, scale, x, y, z);
    return toEnumValue(env, "de/rasmusantons/cubiomes/BiomeID", "(I)Lde/rasmusantons/cubiomes/BiomeID;", id);
}

JNIEXPORT jobjectArray JNICALL Java_de_rasmusantons_cubiomes_Generator_genBiomes(JNIEnv *env, jobject thisObj, jobject r) {
    Generator *g = wrapGenerator(env, thisObj);
    Range range = wrapRange(env, r);
    int *biomeIDs = allocCache(g, range);
    genBiomes(g, biomeIDs, range);

    jclass classBiomeID = (*env)->FindClass(env, "de/rasmusantons/cubiomes/BiomeID");
    assert(classBiomeID != NULL);
    jclass classBiomeIDArrayX = (*env)->FindClass(env, "[Lde/rasmusantons/cubiomes/BiomeID;");
    assert(classBiomeIDArrayX != NULL);
    jclass classBiomeIDArrayZ = (*env)->FindClass(env, "[[Lde/rasmusantons/cubiomes/BiomeID;");
    assert(classBiomeIDArrayZ != NULL);

    jobjectArray biomeIDArrayY = (*env)->NewObjectArray(env, range.sy, classBiomeIDArrayZ, NULL);
    for (int dy = 0; dy < range.sy; ++dy) {
        jobjectArray biomeIDArrayZ = (*env)->NewObjectArray(env, range.sz, classBiomeIDArrayX, NULL);
        for (int dz = 0; dz < range.sz; ++dz) {
            jobjectArray biomeIDArrayX = (*env)->NewObjectArray(env, range.sx, classBiomeID, NULL);
            for (int dx = 0; dx < range.sx; ++dx) {
                jobject biomeIDObj = toEnumValue(
                        env,
                        "de/rasmusantons/cubiomes/BiomeID",
                        "(I)Lde/rasmusantons/cubiomes/BiomeID;",
                        biomeIDs[dy * range.sz * range.sx + dz * range.sx + dx]
                );
                (*env)->SetObjectArrayElement(env, biomeIDArrayX, dx, biomeIDObj);
            }
            (*env)->SetObjectArrayElement(env, biomeIDArrayZ, dz, biomeIDArrayX);
        }
        (*env)->SetObjectArrayElement(env, biomeIDArrayY, dy, biomeIDArrayZ);
    }

    free(biomeIDs);
    return biomeIDArrayY;
}
