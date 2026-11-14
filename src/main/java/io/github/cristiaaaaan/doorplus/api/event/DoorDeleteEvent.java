package io.github.cristiaaaaan.doorplus.api.event;

import io.github.cristiaaaaan.doorplus.api.door.Door;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/** Fired before a door is deleted. */
public class DoorDeleteEvent extends DoorEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final @Nullable UUID actor;
    private boolean cancelled;

    public DoorDeleteEvent(@NotNull Door door, @Nullable UUID actor) {
        super(door);
        this.actor = actor;
    }

    /** Who deletes the door, absent for console and API. */
    public @Nullable UUID getActor() {
        return actor;
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
