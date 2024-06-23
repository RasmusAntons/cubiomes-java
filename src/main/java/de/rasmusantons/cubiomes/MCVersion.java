package de.rasmusantons.cubiomes;

import java.util.EnumSet;

/**
 * Minecraft versions
 * <p>
 * MC_1_X refers to the latest patch of the respective 1.X release.
 * NOTE: Development effort focuses on just the newest patch for each major
 * release. Minor releases and major versions &lt;= 1.0 are experimental.
 */
public enum MCVersion {
    MC_UNDEF(0),
    MC_B1_7(1),
    MC_B1_8(2),
    MC_1_0_0(3), MC_1_0(MC_1_0_0.getValue()),
    MC_1_1_0(4), MC_1_1(MC_1_1_0.getValue()),
    MC_1_2_5(5), MC_1_2(MC_1_2_5.getValue()),
    MC_1_3_2(6), MC_1_3(MC_1_3_2.getValue()),
    MC_1_4_7(7), MC_1_4(MC_1_4_7.getValue()),
    MC_1_5_2(8), MC_1_5(MC_1_5_2.getValue()),
    MC_1_6_4(9), MC_1_6(MC_1_6_4.getValue()),
    MC_1_7_10(10), MC_1_7(MC_1_7_10.getValue()),
    MC_1_8_9(11), MC_1_8(MC_1_8_9.getValue()),
    MC_1_9_4(12), MC_1_9(MC_1_9_4.getValue()) ,
    MC_1_10_2(13), MC_1_10(MC_1_10_2.getValue()),
    MC_1_11_2(14), MC_1_11(MC_1_11_2.getValue()),
    MC_1_12_2(15), MC_1_12(MC_1_12_2.getValue()),
    MC_1_13_2(16), MC_1_13(MC_1_13_2.getValue()),
    MC_1_14_4(17), MC_1_14(MC_1_14_4.getValue()),
    MC_1_15_2(18), MC_1_15(MC_1_15_2.getValue()),
    MC_1_16_1(19),
    MC_1_16_5(20), MC_1_16(MC_1_16_5.getValue()),
    MC_1_17_1(21), MC_1_17(MC_1_17_1.getValue()),
    MC_1_18_2(22), MC_1_18(MC_1_18_2.getValue()),
    MC_1_19_2(23),
    MC_1_19(24),  // 1.19.3 - 1.19.4
    MC_1_20(25),
    MC_1_21(26),
    MC_NEWEST(MC_1_21.getValue());

    private final int value;

    MCVersion(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MCVersion fromValue(int value) {
        for (MCVersion v : EnumSet.allOf(MCVersion.class)) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
