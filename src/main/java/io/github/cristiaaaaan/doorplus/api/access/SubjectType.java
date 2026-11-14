package io.github.cristiaaaaan.doorplus.api.access;

/** Kind of subject of an access entry. */
public enum SubjectType {
    /** A player, by UUID. */
    PLAYER,
    /** A LuckPerms group, lower-case; checked through {@code group.<name>}. */
    GROUP,
    /** A permission node. */
    PERMISSION
}
