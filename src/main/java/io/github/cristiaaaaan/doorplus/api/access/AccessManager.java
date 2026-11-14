package io.github.cristiaaaaan.doorplus.api.access;

import io.github.cristiaaaaan.doorplus.api.door.Door;
import io.github.cristiaaaaan.doorplus.api.trigger.Trigger;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/** Roles and access checks. */
public interface AccessManager {

    /** Role of an online player on the door, {@code null} if none. Bypass permissions are not considered. */
    @Nullable Role roleOf(@NotNull Player player, @NotNull Door door);

    /**
     * Role of a possibly offline player. {@code GROUP} and {@code PERMISSION} entries are resolved through
     * LuckPerms when installed; otherwise only {@code PLAYER} entries count. Completes on the main thread
     * with {@code null} if the player has no role.
     */
    @NotNull CompletableFuture<@Nullable Role> roleOf(@NotNull UUID playerId, @NotNull Door door);

    /** Whether the player may use the door (privacy, role, key), without a trigger override. */
    boolean canUse(@NotNull Player player, @NotNull Door door);

    /** Whether the player may use the door through the given trigger (trigger access override applies). */
    boolean canUse(@NotNull Player player, @NotNull Door door, @NotNull Trigger trigger);

    /**
     * Adds or updates an access entry. Fires {@code DoorAccessChangeEvent}.
     *
     * @throws IllegalArgumentException for {@link Role#OWNER}, the owner as subject, or a full access list
     */
    void grant(@NotNull Door door, @NotNull AccessSubject subject, @NotNull Role role);

    /** Removes an access entry if present. Fires {@code DoorAccessChangeEvent}. */
    void revoke(@NotNull Door door, @NotNull AccessSubject subject);
}
