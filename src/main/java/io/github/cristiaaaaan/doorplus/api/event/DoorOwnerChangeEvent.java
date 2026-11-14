package io.github.cristiaaaaan.doorplus.api.event;

import io.github.cristiaaaaan.doorplus.api.door.Door;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/** Fired before the ownership of a door is transferred. */
public class DoorOwnerChangeEvent extends DoorEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final UUID oldOwner;
    private final UUID newOwner;
    private final @Nullable UUID actor;
    private boolean cancelled;

    public DoorOwnerChangeEvent(@NotNull Door door, @NotNull UUID oldOwner, @NotNull UUID newOwner,
                                @Nullable UUID actor) {
        super(door);
        this.oldOwner = oldOwner;
        this.newOwner = newOwner;
        this.actor = actor;
    }

    public @NotNull UUID getOldOwner() {
        return oldOwner;
    }

    public @NotNull UUID getNewOwner() {
        return newOwner;
    }

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
