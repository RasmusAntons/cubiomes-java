# cubiomes-java
A Java wrapper for [cubiomes](https://github.com/Cubitect/cubiomes/) using JNI.
The goal is to be able to use it from within minecraft mods.

This project builds a native library that is included in the jar and extracted at runtime.

> Work in progress: Currently only `getBiomeAt`, `genBiomes`, `getStructurePos` and `isViableStructurePos` can be used, and it only runs on linux.

[javadoc](https://3po.ch/cubiomes-java)

## Gradle setup
I created a temporary maven repository that can be used for testing.

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
    include implementation("de.rasmusantons.cubiomes:cubiomes-java:0.0")
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


## Windows Build Setup
To be able to compile on windows, follow https://code.visualstudio.com/docs/cpp/config-mingw#_installing-the-mingww64-toolchain
to install the MinGW toolchain, then add `C:\msys64\usr\bin` and `C:\msys64\ucrt64\bin` to the system path.
