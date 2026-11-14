package io.github.cristiaaaaan.doorplus.api.door;

/** Result of a movement request. */
public enum ToggleResult {
    /** Movement started. */
    SUCCESS,
    /** The door is already in the requested state. */
    NO_CHANGE,
    DOOR_NOT_FOUND,
    WORLD_NOT_LOADED,
    /** Movements are paused by an administrator. */
    PAUSED,
    /** The door is locked. */
    LOCKED,
    /** Access denied. */
    DENIED,
    /** An animation is running. */
    BUSY,
    /** The door cooldown has not expired. */
    COOLDOWN,
    /** A block in the door cannot be animated; see {@link ToggleOutcome#where()}. */
    BLOCKED_SOURCE,
    /** The destination is obstructed; see {@link ToggleOutcome#where()}. */
    OBSTRUCTED,
    /** The destination is outside the world height limits. */
    OUT_OF_WORLD,
    /** A protection hook denied the movement. */
    PROTECTED,
    /** A plugin cancelled {@code DoorPreToggleEvent}. */
    CANCELLED,
    /** Unexpected exception; details in console. */
    ERROR;

    public boolean isSuccess() {
        return this == SUCCESS;
    }
}
