package io.github.cristiaaaaan.doorplus.api.event;

import io.github.cristiaaaaan.doorplus.api.door.DoorSpec;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Fired before a new door is saved. */
public class DoorCreateEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final DoorSpec draft;
    private final @Nullable Player player;
    private boolean cancelled;

    public DoorCreateEvent(@NotNull DoorSpec draft, @Nullable Player player) {
        this.draft = draft;
        this.player = player;
    }

    /** The door about to be created. */
    public @NotNull DoorSpec getDraft() {
        return draft;
    }

    /** Creator, absent for API creation. */
    public @Nullable Player getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
