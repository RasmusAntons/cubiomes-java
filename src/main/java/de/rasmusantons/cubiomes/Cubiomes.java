package de.rasmusantons.cubiomes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidParameterException;
import java.util.EnumSet;

/**
 * Biome Generation
 */
public class Cubiomes {
    protected ByteBuffer generator;

    public Cubiomes(MCVersion mc) {
        NativeLibLoader.ensureLoaded();
        generator = ByteBuffer.allocateDirect(getGeneratorSize());
        generator.order(ByteOrder.nativeOrder());
        setupGenerator(mc, 0);
    }

    public Cubiomes(MCVersion mc, EnumSet<GeneratorFlag> flags) {
        NativeLibLoader.ensureLoaded();
        generator = ByteBuffer.allocateDirect(getGeneratorSize());
        generator.order(ByteOrder.nativeOrder());
        setupGenerator(mc, flags.stream().mapToInt(GeneratorFlag::getValue).reduce(0, (mask, flag) -> mask | flag));
    }

    /**
     * {@return the size of the Generator struct in bytes}
     */
    private native int getGeneratorSize();

    private native void setupGenerator(MCVersion mc, int flags);

    /**
     * Initializes the generator for a given dimension and seed.
     */
    public native void applySeed(Dimension dimension, long seed);

    /**
     * Gets the biome for a specified scaled position. Note that the scale should
     * be either 1 or 4, for block or biome coordinates respectively.
     * <p>
     * Before calling, {@link Cubiomes#applySeed(Dimension, long)} should be used to set dimension and seed.
     *
     * @return {@link BiomeID#none} upon failure
     *
     */
    public native BiomeID getBiomeAt(int scale, int x, int y, int z);

    /**
     * Generates the biomes for a cuboidal scaled range given by 'r'.
     *
     * @return A 3-dimensional array of {@link BiomeID}s or null upon failure.
     * The output can be indexed as output[y][z][x]
     * where (x,y,z) is a relative position inside the range cuboid.
     */
    public native BiomeID[][][] genBiomes(Range r);

    /**
     * Same as {@link #genBiomes(Range)}, except that the y-size needs to be 1.
     *
     * @return A 2-dimensional array of {@link BiomeID}s or null upon failure.
     * The output can be indexed as output[z][x]
     * where (x,0,z) is a relative position inside the range cuboid.
     *
     * @throws InvalidParameterException if {@literal r.sy} is not 1.
     */
    public BiomeID[][] genBiomes2D(Range r) {
        if (r.sy() != 1)
            throw new InvalidParameterException("r.dy must be 1 for genBiomes2D");
        return genBiomes(r)[0];
    }

    /**
     * Finds the block position of the structure generation attempt in a given
     * region. You can use {@link Cubiomes#isViableStructurePos(StructureType, int, int)}
     * to test if the necessary biome requirements are met for the structure to
     * actually generate at that position. Some structure types may fail to produce
     * a valid position in the given region regardless of biomes, in which case the
     * function returns null.
     *
     * @param structureType structure type
     * @param seed          world seed (only the lower 48-bits are relevant)
     * @param regX          x coordinate of the region (the region size depends on type)
     * @param regZ          z coordinate of the region (the region size depends on type)
     * @return the block position of the structure
     */
    public native Pos getStructurePos(StructureType structureType, long seed, int regX, int regZ);

    /**
     * Performs a biome check near the specified block coordinates to determine
     * whether a structure of the given type could spawn there. You can get the
     * block positions using {@link Cubiomes#getStructurePos(StructureType, long, int, int)}.
     * <p>
     * Before calling, {@link Cubiomes#applySeed(Dimension, long)} should be used to set dimension and seed.
     */
    public native boolean isViableStructurePos(StructureType structType, int blockX, int blockZ);

    /**
     * Starts to generate the specified range and checks if the biomes meet the
     * requirements of the biome filter.
     * <p>
     * The generator should be set up for the correct version, however the
     * dimension and seed will be applied internally. This will modify the
     * generator into a partially initialized state that is not valid to use
     * outside this function without re-applying a seed.
     */
    public native boolean checkForBiomes(Range r, Dimension dim, long seed, BiomeFilter filter);

    public enum GeneratorFlag {
        LARGE_BIOMES(0x1),
        NO_BETA_OCEAN(0x2),
        FORCE_OCEAN_VARIANTS(0x4);

        private final int value;

        GeneratorFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
