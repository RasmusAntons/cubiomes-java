#include <jni.h>
#include <assert.h>
#include <generator.h>
#include <finders.h>

#include "cubij_utils.h"
#include "de_rasmusantons_cubiomes_Cubiomes.h"

JNIEXPORT jint JNICALL Java_de_rasmusantons_cubiomes_Cubiomes_getGeneratorSize(JNIEnv *env, jobject thisObj) {
    return sizeof(Generator);
}

JNIEXPORT void JNICALL Java_de_rasmusantons_cubiomes_Cubiomes_setupGenerator(JNIEnv *env, jobject thisObj, jobject mc, jint flags) {
    Generator *g = wrapGenerator(env, thisObj);
    setupGenerator(g, getEnumValue(env, mc), 0);
}

JNIEXPORT void JNICALL Java_de_rasmusantons_cubiomes_Cubiomes_applySeed(JNIEnv *env, jobject thisObj, jobject dimension, jlong seed) {
    Generator *g = wrapGenerator(env, thisObj);
    applySeed(g, getEnumValue(env, dimension), seed);
}

JNIEXPORT jobject JNICALL Java_de_rasmusantons_cubiomes_Cubiomes_getBiomeAt(JNIEnv *env, jobject thisObj, jint scale, jint x, jint y, jint z) {
    Generator *g = wrapGenerator(env, thisObj);
    enum BiomeID id = getBiomeAt(g, scale, x, y, z);
    return toEnumValue(env, "de/rasmusantons/cubiomes/BiomeID", "(I)Lde/rasmusantons/cubiomes/BiomeID;", id);
}

JNIEXPORT jobjectArray JNICALL Java_de_rasmusantons_cubiomes_Cubiomes_genBiomes(JNIEnv *env, jobject thisObj, jobject r) {
    Generator *g = wrapGenerator(env, thisObj);
    Range range = wrapRange(env, r);
    int *biomeIDs = allocCache(g, range);
    genBiomes(g, biomeIDs, range);

    jclass clsBiomeID = (*env)->FindClass(env, "de/rasmusantons/cubiomes/BiomeID");
    assert(clsBiomeID != NULL);
    jclass clsBiomeIDArrayX = (*env)->FindClass(env, "[Lde/rasmusantons/cubiomes/BiomeID;");
    assert(clsBiomeIDArrayX != NULL);
    jclass clsBiomeIDArrayZ = (*env)->FindClass(env, "[[Lde/rasmusantons/cubiomes/BiomeID;");
    assert(clsBiomeIDArrayZ != NULL);

    jobjectArray biomeIDArrayY = (*env)->NewObjectArray(env, range.sy, clsBiomeIDArrayZ, NULL);
    for (int dy = 0; dy < range.sy; ++dy) {
        jobjectArray biomeIDArrayZ = (*env)->NewObjectArray(env, range.sz, clsBiomeIDArrayX, NULL);
        for (int dz = 0; dz < range.sz; ++dz) {
            jobjectArray biomeIDArrayX = (*env)->NewObjectArray(env, range.sx, clsBiomeID, NULL);
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

jobject Java_de_rasmusantons_cubiomes_Cubiomes_getStructurePos(JNIEnv *env, jobject thisObj, jobject structureType, jlong seed, jint regX, jint regZ) {
    Generator *g = wrapGenerator(env, thisObj);
    Pos pos;
    int res = getStructurePos(getEnumValue(env, structureType), g->mc, seed, regX, regZ, &pos);
    if (!res)
        return NULL;
    jclass clsPos = (*env)->FindClass(env, "de/rasmusantons/cubiomes/Pos");
    assert(clsPos != NULL);
    jmethodID midPosInit = (*env)->GetMethodID(env, clsPos, "<init>", "(II)V");
    assert(midPosInit != NULL);
    return (*env)->NewObject(env, clsPos, midPosInit, pos.x, pos.z);
}

jboolean Java_de_rasmusantons_cubiomes_Cubiomes_isViableStructurePos(JNIEnv *env, jobject thisObj, jobject structType, jint blockX, jint blockZ) {
    Generator *g = wrapGenerator(env, thisObj);
    return isViableStructurePos(getEnumValue(env, structType), g, blockX, blockZ, 0);
}
