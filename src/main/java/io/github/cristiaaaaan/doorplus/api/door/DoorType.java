package io.github.cristiaaaaan.doorplus.api.door;

/** Geometry of a door's movement. */
public enum DoorType {
    BIG_DOOR(Category.BINARY),
    DRAWBRIDGE(Category.BINARY),
    PORTCULLIS(Category.BINARY),
    SLIDING_DOOR(Category.BINARY),
    GARAGE_DOOR(Category.BINARY),
    SPLIT_DOOR(Category.BINARY),
    FOLDING_DOOR(Category.BINARY),
    ROLLING_SHUTTER(Category.BINARY),
    PLATFORM(Category.PLATFORM),
    REVOLVING_DOOR(Category.REVOLVING),
    WINDMILL(Category.PERPETUAL),
    FLAG(Category.PERPETUAL),
    CLOCK(Category.PERPETUAL);

    /** How a type interprets actions and states. */
    public enum Category {
        /** State 0 closed, 1 open. */
        BINARY,
        /** State is the index of the current stop. */
        PLATFORM,
        /** Only state 0; each activation rotates. */
        REVOLVING,
        /** State 0 stopped, 1 running continuously. */
        PERPETUAL
    }

    private final Category category;

    DoorType(Category category) {
        this.category = category;
    }

    public Category category() {
        return category;
    }

    /** True for types whose blocks stay animated entities while active. */
    public boolean isPerpetual() {
        return category == Category.PERPETUAL;
    }

    /** Lower-case id used in permissions and config ({@code big_door}). */
    public String id() {
        return name().toLowerCase(java.util.Locale.ROOT);
    }
}
