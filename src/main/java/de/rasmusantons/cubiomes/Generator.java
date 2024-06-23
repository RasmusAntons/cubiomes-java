package de.rasmusantons.cubiomes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Generator {
    static {
        System.loadLibrary("cubij");
    }

    protected ByteBuffer state;

    public Generator(int mc, int flags) {
        state = ByteBuffer.allocateDirect(getStateSize());
        state.order(ByteOrder.nativeOrder());
        setupGenerator(mc, flags);
    }

    /**
     * {@return the size of the Generator struct in bytes}
     */
    private native int getStateSize();

    private native void setupGenerator(int mc, int flags);

    public native void applySeed(int dimension, long seed);

    public native int getBiomeAt(int scale, int x, int y, int z);
}
