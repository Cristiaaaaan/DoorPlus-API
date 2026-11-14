package io.github.cristiaaaaan.doorplus.api.event;

import io.github.cristiaaaaan.doorplus.api.door.Door;
import io.github.cristiaaaaan.doorplus.api.door.ToggleCause;
import io.github.cristiaaaaan.doorplus.api.trigger.Trigger;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/** Fired when a movement ends and the blocks are placed at the destination. */
public class DoorToggleEndEvent extends DoorToggleEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public DoorToggleEndEvent(@NotNull Door door, int fromState, int toState, @NotNull ToggleCause cause,
                              @Nullable UUID actor, @Nullable Trigger trigger, boolean instant) {
        super(door, fromState, toState, cause, actor, trigger, instant);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
