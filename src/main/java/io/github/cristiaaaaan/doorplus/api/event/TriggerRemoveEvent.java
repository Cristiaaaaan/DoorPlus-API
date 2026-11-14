package io.github.cristiaaaaan.doorplus.api.event;

import io.github.cristiaaaaan.doorplus.api.trigger.Trigger;
import io.github.cristiaaaaan.doorplus.api.trigger.TriggerRemoveCause;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/** Fired after a trigger has been removed. */
public class TriggerRemoveEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Trigger trigger;
    private final TriggerRemoveCause cause;

    public TriggerRemoveEvent(@NotNull Trigger trigger, @NotNull TriggerRemoveCause cause) {
        this.trigger = trigger;
        this.cause = cause;
    }

    /** The removed trigger (detached view). */
    public @NotNull Trigger getTrigger() {
        return trigger;
    }

    public @NotNull TriggerRemoveCause getCause() {
        return cause;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
