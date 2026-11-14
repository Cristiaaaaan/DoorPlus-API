package io.github.cristiaaaaan.doorplus.api.event;

import io.github.cristiaaaaan.doorplus.api.door.Door;
import io.github.cristiaaaaan.doorplus.api.door.ToggleCause;
import io.github.cristiaaaaan.doorplus.api.trigger.Trigger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/** Common data of the movement events. */
public abstract class DoorToggleEvent extends DoorEvent {

    private final int fromState;
    private final int toState;
    private final ToggleCause cause;
    private final @Nullable UUID actor;
    private final @Nullable Trigger trigger;
    private final boolean instant;

    protected DoorToggleEvent(@NotNull Door door, int fromState, int toState, @NotNull ToggleCause cause,
                              @Nullable UUID actor, @Nullable Trigger trigger, boolean instant) {
        super(door);
        this.fromState = fromState;
        this.toState = toState;
        this.cause = cause;
        this.actor = actor;
        this.trigger = trigger;
        this.instant = instant;
    }

    public int getFromState() {
        return fromState;
    }

    public int getToState() {
        return toState;
    }

    public @NotNull ToggleCause getCause() {
        return cause;
    }

    /** Player responsible, absent for redstone, console and API without actor. */
    public @Nullable UUID getActor() {
        return actor;
    }

    /** Trigger that caused the movement, if any. */
    public @Nullable Trigger getTrigger() {
        return trigger;
    }

    /** True if the movement happens without animation. */
    public boolean isInstant() {
        return instant;
    }
}
