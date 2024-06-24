package de.rasmusantons.cubiomes;

import java.util.EnumSet;

/**
 * All StructureTypes that cubiomes knows.
 * <p>
 * Note: Cubiomes defines some duplicate names. In C those are equal,
 * in Java they are not, e.g. {@link StructureType#Jungle_Temple} != {@link StructureType#Jungle_Pyramid}.
 * Because of that, duplicate (deprecated) values should probably not be used.
 */
public enum StructureType {
    /**
     * for locations of temple generation attempts pre 1.13
     */
    Feature(0),
    Desert_Pyramid(1),
    Jungle_Temple(2), @Deprecated Jungle_Pyramid(Jungle_Temple.getValue()),
    Swamp_Hut(3),
    Igloo(4),
    Village(5),
    Ocean_Ruin(6),
    Shipwreck(7),
    Monument(8),
    Mansion(9),
    Outpost(10),
    Ruined_Portal(11),
    Ruined_Portal_N(12),
    Ancient_City(13),
    Treasure(14),
    Mineshaft(15),
    Desert_Well(16),
    Geode(17),
    Fortress(18),
    Bastion(19),
    End_City(20),
    End_Gateway(21),
    End_Island(22),
    Trail_Ruins(23),
    Trial_Chambers(24),
    FEATURE_NUM(25);

    private final int value;

    StructureType(int value) {
        this.value = value;
    }

    /**
     * {@return cubiomes' internal id of this structure type}
     */
    public int getValue() {
        return value;
    }

    /**
     * {@return the structure type for a given internal id}
     */
    public static StructureType fromValue(int value) {
        for (StructureType v : EnumSet.allOf(StructureType.class)) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
