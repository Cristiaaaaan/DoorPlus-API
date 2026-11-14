package io.github.cristiaaaaan.doorplus.api.door;

/** Origin of a movement request. */
public enum ToggleCause {
    PLAYER_TRIGGER,
    REDSTONE,
    PROXIMITY,
    COMMAND,
    GUI,
    API,
    AUTO_CLOSE
}
