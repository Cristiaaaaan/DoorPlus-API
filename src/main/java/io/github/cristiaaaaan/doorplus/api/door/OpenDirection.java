package io.github.cristiaaaaan.doorplus.api.door;

import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.Nullable;

/** Direction in which a door opens. {@code CW}/{@code CCW} are seen from above. */
public enum OpenDirection {
    NONE(null),
    CW(null),
    CCW(null),
    UP(BlockFace.UP),
    DOWN(BlockFace.DOWN),
    NORTH(BlockFace.NORTH),
    EAST(BlockFace.EAST),
    SOUTH(BlockFace.SOUTH),
    WEST(BlockFace.WEST);

    private final @Nullable BlockFace face;

    OpenDirection(@Nullable BlockFace face) {
        this.face = face;
    }

    /** Block face for the six linear directions, {@code null} for {@code NONE}, {@code CW}, {@code CCW}. */
    public @Nullable BlockFace face() {
        return face;
    }

    /** The opposite direction ({@code CW} ↔ {@code CCW}, {@code UP} ↔ {@code DOWN}, …). */
    public OpenDirection opposite() {
        return switch (this) {
            case NONE -> NONE;
            case CW -> CCW;
            case CCW -> CW;
            case UP -> DOWN;
            case DOWN -> UP;
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case EAST -> WEST;
            case WEST -> EAST;
        };
    }

    /** Direction matching a block face, or {@code NONE}. */
    public static OpenDirection of(BlockFace face) {
        return switch (face) {
            case UP -> UP;
            case DOWN -> DOWN;
            case NORTH -> NORTH;
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            default -> NONE;
        };
    }
}
