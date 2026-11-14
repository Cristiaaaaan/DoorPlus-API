package io.github.cristiaaaaan.doorplus.api.door;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/** Lookup, movement and lifecycle of doors. */
public interface DoorManager {

    /** Door with that id. Safe to call from any thread. */
    @NotNull Optional<Door> byId(long id);

    /** Door with that name (case-insensitive) owned by {@code owner}. */
    @NotNull Optional<Door> byName(@NotNull UUID owner, @NotNull String name);

    @NotNull Collection<Door> ownedBy(@NotNull UUID owner);

    @NotNull Collection<Door> all();

    /** Door whose current occupancy contains the block. */
    @NotNull Optional<Door> at(@NotNull Location blockLocation);

    /** Doors whose current occupancy touches the chunk. */
    @NotNull Collection<Door> inChunk(@NotNull World world, int chunkX, int chunkZ);

    /**
     * Requests a movement with cause {@link ToggleCause#API}. Completed on the main thread when the movement
     * starts or is refused. When the access is checked the actor must be online, otherwise the result is
     * {@link ToggleResult#DENIED}; a deleted door gives {@link ToggleResult#DOOR_NOT_FOUND}.
     *
     * @throws IllegalArgumentException for a {@code Door} not obtained from Door+
     * @throws IllegalStateException    off the main thread
     */
    @NotNull CompletableFuture<ToggleOutcome> toggle(@NotNull Door door, @NotNull ToggleOptions options);

    /**
     * Creates a door programmatically with the same validations as the guided creation (shape, blocks,
     * overlaps, protection hooks, {@code DoorCreateEvent}). Fails exceptionally with
     * {@link IllegalArgumentException} carrying the reason.
     */
    @NotNull CompletableFuture<Door> create(@NotNull DoorSpec spec);

    /**
     * Deletes a door; blocks stay where they are. Fires {@code DoorDeleteEvent}. Fails exceptionally with
     * {@link IllegalArgumentException} for a door that no longer exists and with {@link IllegalStateException}
     * (message = reason) if the deletion was cancelled or refused (e.g. an obstructed rolling shutter).
     */
    @NotNull CompletableFuture<Void> delete(@NotNull Door door);
}
