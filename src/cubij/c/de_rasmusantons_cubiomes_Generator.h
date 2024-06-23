/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class de_rasmusantons_cubiomes_Generator */

#ifndef _Included_de_rasmusantons_cubiomes_Generator
#define _Included_de_rasmusantons_cubiomes_Generator
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     de_rasmusantons_cubiomes_Generator
 * Method:    getStateSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_de_rasmusantons_cubiomes_Generator_getStateSize
  (JNIEnv *, jobject);

/*
 * Class:     de_rasmusantons_cubiomes_Generator
 * Method:    setupGenerator
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_de_rasmusantons_cubiomes_Generator_setupGenerator
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     de_rasmusantons_cubiomes_Generator
 * Method:    applySeed
 * Signature: (IJ)V
 */
JNIEXPORT void JNICALL Java_de_rasmusantons_cubiomes_Generator_applySeed
  (JNIEnv *, jobject, jint, jlong);

/*
 * Class:     de_rasmusantons_cubiomes_Generator
 * Method:    getBiomeAt
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_de_rasmusantons_cubiomes_Generator_getBiomeAt
  (JNIEnv *, jobject, jint, jint, jint, jint);

#ifdef __cplusplus
}
#endif
#endif
