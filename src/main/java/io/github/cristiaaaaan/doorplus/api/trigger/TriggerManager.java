package io.github.cristiaaaaan.doorplus.api.trigger;

import io.github.cristiaaaaan.doorplus.api.door.Door;
import io.github.cristiaaaaan.doorplus.api.door.ToggleOutcome;
import io.github.cristiaaaaan.doorplus.api.group.DoorGroup;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/** Lookup and management of triggers. */
public interface TriggerManager {

    /** Trigger with that id. Safe to call from any thread. */
    @NotNull Optional<Trigger> byId(long id);

    @NotNull Collection<Trigger> of(@NotNull Door door);

    @NotNull Collection<Trigger> of(@NotNull DoorGroup group);

    /** Block, redstone and custom-block triggers at the block (furniture is reported by {@link #at(Entity)}). */
    @NotNull Collection<Trigger> at(@NotNull Block block);

    /** Entity and furniture triggers bound to the entity. */
    @NotNull Collection<Trigger> at(@NotNull Entity entity);

    /**
     * Adds a trigger. Validates the world, the triggers-per-door limit of the config and duplicates, and fires
     * {@code TriggerAddEvent}. As a server-side action it skips permissions, roles, the distance limit and
     * protection hooks. Fails exceptionally with {@link IllegalArgumentException} carrying the reason.
     */
    @NotNull CompletableFuture<Trigger> add(@NotNull TriggerSpec spec);

    /** Removes a trigger with cause {@code API}; completes immediately if it was already removed. */
    @NotNull CompletableFuture<Void> remove(@NotNull Trigger trigger);

    /**
     * Activates a trigger as if its source fired (active = true). Completes with the outcome of the first
     * door for group targets.
     */
    @NotNull CompletableFuture<ToggleOutcome> activate(@NotNull Trigger trigger, @Nullable Player actor);
}
