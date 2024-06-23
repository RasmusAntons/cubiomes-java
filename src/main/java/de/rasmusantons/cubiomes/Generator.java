package de.rasmusantons.cubiomes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidParameterException;
import java.util.EnumSet;

/**
 * Biome Generation
 */
public class Generator {
    protected ByteBuffer state;

    public Generator(MCVersion mc) {
        NativeLibLoader.ensureLoaded();
        state = ByteBuffer.allocateDirect(getStateSize());
        state.order(ByteOrder.nativeOrder());
        setupGenerator(mc, 0);
    }

    public Generator(MCVersion mc, EnumSet<Flags> flags) {
        state = ByteBuffer.allocateDirect(getStateSize());
        state.order(ByteOrder.nativeOrder());
        setupGenerator(mc, flags.stream().mapToInt(Flags::getValue).reduce(0, (mask, flag) -> mask | flag));
    }

    /**
     * {@return the size of the Generator struct in bytes}
     */
    private native int getStateSize();

    private native void setupGenerator(MCVersion mc, int flags);

    /**
     * Initializes the generator for a given dimension and seed.
     */
    public native void applySeed(Dimension dimension, long seed);

    /**
     * Gets the biome for a specified scaled position. Note that the scale should
     * be either 1 or 4, for block or biome coordinates respectively.
     * Returns {@link BiomeID#none} upon failure.
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

    public enum Flags {
        LARGE_BIOMES(0x1),
        NO_BETA_OCEAN(0x2),
        FORCE_OCEAN_VARIANTS(0x4);

        private final int value;

        Flags(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
