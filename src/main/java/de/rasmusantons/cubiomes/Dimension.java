package de.rasmusantons.cubiomes;

import java.util.EnumSet;

public enum Dimension {
    DIM_NETHER(-1),
    DIM_OVERWORLD(0),
    DIM_END(1),
    DIM_UNDEF(1000);

    private final int value;

    Dimension(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Dimension fromValue(int value) {
        for (Dimension v : EnumSet.allOf(Dimension.class)) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
