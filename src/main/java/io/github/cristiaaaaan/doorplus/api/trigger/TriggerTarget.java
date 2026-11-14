package io.github.cristiaaaaan.doorplus.api.trigger;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * What a trigger operates: a door or a door group.
 *
 * @param kind target kind
 * @param id   door id or group id
 */
public record TriggerTarget(@NotNull Kind kind, long id) {

    /** Target kind. */
    public enum Kind { DOOR, GROUP }

    public TriggerTarget {
        Objects.requireNonNull(kind, "kind");
    }

    public static @NotNull TriggerTarget door(long doorId) {
        return new TriggerTarget(Kind.DOOR, doorId);
    }

    public static @NotNull TriggerTarget group(long groupId) {
        return new TriggerTarget(Kind.GROUP, groupId);
    }

    public boolean isDoor() {
        return kind == Kind.DOOR;
    }

    public boolean isGroup() {
        return kind == Kind.GROUP;
    }

    @Override
    public @NotNull String toString() {
        return kind + ":" + id;
    }
}
