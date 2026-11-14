package io.github.cristiaaaaan.doorplus.api.group;

import io.github.cristiaaaaan.doorplus.api.door.ToggleOptions;
import io.github.cristiaaaaan.doorplus.api.door.ToggleOutcome;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/** Lookup and operation of door groups. */
public interface GroupManager {

    /** Group with that id. Safe to call from any thread. */
    @NotNull Optional<DoorGroup> byId(long id);

    @NotNull Optional<DoorGroup> byName(@NotNull UUID owner, @NotNull String name);

    @NotNull Collection<DoorGroup> ownedBy(@NotNull UUID owner);

    /**
     * Operates every door of the group. {@code TOGGLE} opens all if at least one binary door is closed,
     * otherwise closes all. Completes on the main thread with one outcome per door. When the access is
     * checked the actor must be online, otherwise every door is {@code DENIED}.
     */
    @NotNull CompletableFuture<List<ToggleOutcome>> toggle(@NotNull DoorGroup group, @NotNull ToggleOptions options);
}
