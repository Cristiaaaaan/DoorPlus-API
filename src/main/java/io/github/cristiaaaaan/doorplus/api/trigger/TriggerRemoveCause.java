package io.github.cristiaaaaan.doorplus.api.trigger;

/** Why a trigger was removed. */
public enum TriggerRemoveCause {
    /** Removed by a player from menu or command. */
    MANUAL,
    /** Removed through the API. */
    API,
    /** The source entity, custom block, furniture or NPC disappeared. */
    SOURCE_REMOVED,
    /** The custom object at the source position has a different id. */
    OBSOLETE,
    /** The target door or group was deleted. */
    TARGET_DELETED
}
