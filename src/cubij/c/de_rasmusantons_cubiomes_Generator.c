#include <jni.h>
#include <stdio.h>
#include <generator.h>
#include "de_rasmusantons_cubiomes_Generator.h"

JNIEXPORT jint JNICALL Java_de_rasmusantons_cubiomes_Generator_getStateSize(JNIEnv *env, jobject thisObj) {
    return sizeof(Generator);
}

JNIEXPORT void JNICALL Java_de_rasmusantons_cubiomes_Generator_setupGenerator(JNIEnv *env, jobject thisObj, jint mc, jint flags) {
    jclass thisClass = (*env)->GetObjectClass(env, thisObj);
    jfieldID fidState = (*env)->GetFieldID(env, thisClass, "state", "Ljava/nio/ByteBuffer;");
    jobject state = (*env)->GetObjectField(env, thisObj, fidState);
    Generator *g = (*env)->GetDirectBufferAddress(env, state);
    setupGenerator(g, mc, 0);
}

JNIEXPORT void JNICALL Java_de_rasmusantons_cubiomes_Generator_applySeed(JNIEnv *env, jobject thisObj, jint dimension, jlong seed) {
    jclass thisClass = (*env)->GetObjectClass(env, thisObj);
    jfieldID fidState = (*env)->GetFieldID(env, thisClass, "state", "Ljava/nio/ByteBuffer;");
    jobject state = (*env)->GetObjectField(env, thisObj, fidState);
    Generator *g = (*env)->GetDirectBufferAddress(env, state);
    applySeed(g, dimension, seed);
}

JNIEXPORT jint JNICALL Java_de_rasmusantons_cubiomes_Generator_getBiomeAt(JNIEnv *env, jobject thisObj, jint scale, jint x, jint y, jint z) {
    jclass thisClass = (*env)->GetObjectClass(env, thisObj);
    jfieldID fidState = (*env)->GetFieldID(env, thisClass, "state", "Ljava/nio/ByteBuffer;");
    jobject state = (*env)->GetObjectField(env, thisObj, fidState);
    Generator *g = (*env)->GetDirectBufferAddress(env, state);
    return getBiomeAt(g, scale, x, y, z);
}
