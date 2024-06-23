# cubiomes-java
This is a Java wrapper for [cubiomes](https://github.com/Cubitect/cubiomes/) using JNI.
The goal is to be able to use this from within minecraft mods.
This project builds a native library that is included in the jar and extracted at runtime.

> Work in progress: Currently only `getBiomeAt` and `genBiomes` can be used, and it only runs on linux.

[javadoc](https://3po.ch/cubiomes-java) (potentially outdated)

## Gradle setup
I created a temporary maven repository that can be used for testing.
Add the repository and dependency (with include to nest it into the mod's jar).
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
Generator generator = new Generator(MCVersion.MC_1_21);
generator.applySeed(Dimension.DIM_OVERWORLD, 123456L);  // specify the dimension and the seed
BiomeID biome = generator.getBiomeAt(1, 0, 63, 0);  // get the biome at (0, 63, 0) with a scale of 1
```

For more examples see [GeneratorTest.java](https://github.com/RasmusAntons/cubiomes-java/blob/main/src/test/java/de/rasmusantons/cubiomes/GeneratorTest.java)
and the original [cubiomes](https://github.com/Cubitect/cubiomes/) documentation.
