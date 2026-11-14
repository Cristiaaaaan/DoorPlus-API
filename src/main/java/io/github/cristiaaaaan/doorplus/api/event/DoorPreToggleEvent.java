package io.github.cristiaaaaan.doorplus.api.event;

import io.github.cristiaaaaan.doorplus.api.door.Door;
import io.github.cristiaaaaan.doorplus.api.door.ToggleCause;
import io.github.cristiaaaaan.doorplus.api.trigger.Trigger;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/** Fired after every check passed, just before a movement starts. Cancelling yields {@code CANCELLED}. */
public class DoorPreToggleEvent extends DoorToggleEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean cancelled;

    public DoorPreToggleEvent(@NotNull Door door, int fromState, int toState, @NotNull ToggleCause cause,
                              @Nullable UUID actor, @Nullable Trigger trigger, boolean instant) {
        super(door, fromState, toState, cause, actor, trigger, instant);
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
