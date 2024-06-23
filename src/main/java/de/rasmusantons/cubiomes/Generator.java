package de.rasmusantons.cubiomes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidParameterException;
import java.util.EnumSet;

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

    public native void applySeed(Dimension dimension, long seed);

    public native BiomeID getBiomeAt(int scale, int x, int y, int z);

    public native BiomeID[][][] genBiomes(Range r);

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
