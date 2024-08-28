# cubiomes-java
A Java wrapper for [cubiomes](https://github.com/Cubitect/cubiomes/) using JNI.
The goal is to be able to use it from within minecraft mods.

This project builds a native library that is included in the jar and extracted at runtime.
The library is compiled for x86_64 Linux and Windows (see [Cross Compiling for Windows](#cross-compiling-for-windows)).

So far, `getBiomeAt`, `genBiomes`, `getStructurePos`, `isViableStructurePos` and `checkForBiomes` can be used.

[Javadoc](https://3po.ch/cubiomes-java)

## Gradle Setup for Mod Development
Add the repository and dependency (with `include` to copy it into the mod's jar).
```groovy
repositories {
    // ...
    maven {
        name = "3po"
        url = "https://mvn.3po.ch/maven"
    }
}
dependencies {
    // ...
    include implementation("de.rasmusantons.cubiomes:cubiomes-java:0.0.5")
}
```

## Usage
To get the biome at a specific location on a given seed:
```java
Cubiomes cubiomes = new Cubiomes(MCVersion.MC_1_21);
cubiomes.applySeed(Dimension.DIM_OVERWORLD, 1234567890L);  // specify the dimension and the seed
BiomeID biome = cubiomes.getBiomeAt(1, 0, 63, 0);  // get the biome at (0, 63, 0) with a scale of 1
```

For more examples see [CubiomesTest.java](https://github.com/RasmusAntons/cubiomes-java/blob/main/src/test/java/de/rasmusantons/cubiomes/CubiomesTest.java)
and the original [cubiomes](https://github.com/Cubitect/cubiomes/) documentation.
The [BiomeID javadoc](https://3po.ch/cubiomes-java/de/rasmusantons/cubiomes/BiomeID.html) has code examples for converting between Minecraft's internal classes and Cubiomes biomes. 

## Cross Compiling for Windows
On Arch linux, install the group `mingw-w64-toolchain` and then download a missing header file (if using jdk21-openjdk):
```
sudo curl --create-dirs --output /usr/lib/jvm/java-21-openjdk/include/win32/jni_md.h https://raw.githubusercontent.com/openjdk/jdk/master/src/java.base/windows/native/include/jni_md.h
sudo chmod 755 /usr/lib/jvm/java-21-openjdk/include/win32
```

## Build Setup on Windows
To be able to compile on windows, follow https://code.visualstudio.com/docs/cpp/config-mingw#_installing-the-mingww64-toolchain
to install the MinGW toolchain, then add `C:\msys64\usr\bin` and `C:\msys64\ucrt64\bin` to the system path.

