package io.github.cristiaaaaan.doorplus.api.access;

import org.jetbrains.annotations.Nullable;

/** Role of a player on a door, in increasing order of power. */
public enum Role {
    /** Opens and closes the door, sees information. */
    USER,
    /** Also locks, edits settings and triggers, breaks protected blocks. */
    ADMIN,
    /** Also changes privacy and access, transfers and deletes. */
    OWNER;

    /** True if this role is {@code other} or higher. */
    public boolean atLeast(Role other) {
        return compareTo(other) >= 0;
    }

    /** True if {@code role} is non-null and at least {@code min}. */
    public static boolean atLeast(@Nullable Role role, Role min) {
        return role != null && role.atLeast(min);
    }

    /** Highest of two nullable roles. */
    public static @Nullable Role max(@Nullable Role a, @Nullable Role b) {
        if (a == null) return b;
        if (b == null) return a;
        return a.compareTo(b) >= 0 ? a : b;
    }
}
