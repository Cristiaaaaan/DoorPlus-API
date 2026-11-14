package io.github.cristiaaaaan.doorplus.api.access;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Subject of an access entry.
 *
 * @param type  subject kind
 * @param value UUID string for players, lower-case group name, or permission node
 */
public record AccessSubject(@NotNull SubjectType type, @NotNull String value) {

    /** Valid permission node: {@code ^[a-z0-9._*-]{1,128}$}. */
    public static final Pattern PERMISSION_PATTERN = Pattern.compile("^[a-z0-9._*-]{1,128}$");
    /** Valid LuckPerms group name. */
    public static final Pattern GROUP_PATTERN = Pattern.compile("^[a-z0-9_.-]{1,64}$");

    public AccessSubject {
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(value, "value");
    }

    public static @NotNull AccessSubject player(@NotNull UUID playerId) {
        return new AccessSubject(SubjectType.PLAYER, playerId.toString());
    }

    /** @throws IllegalArgumentException if the name is not a valid group name */
    public static @NotNull AccessSubject group(@NotNull String groupName) {
        String name = groupName.toLowerCase(Locale.ROOT);
        if (!GROUP_PATTERN.matcher(name).matches()) throw new IllegalArgumentException("Invalid group: " + groupName);
        return new AccessSubject(SubjectType.GROUP, name);
    }

    /** @throws IllegalArgumentException if the node does not match {@link #PERMISSION_PATTERN} */
    public static @NotNull AccessSubject permission(@NotNull String node) {
        if (!PERMISSION_PATTERN.matcher(node).matches()) throw new IllegalArgumentException("Invalid node: " + node);
        return new AccessSubject(SubjectType.PERMISSION, node);
    }

    /** Player UUID; only for {@link SubjectType#PLAYER}. */
    public @NotNull UUID playerId() {
        if (type != SubjectType.PLAYER) throw new IllegalStateException("Not a player subject");
        return UUID.fromString(value);
    }
}
