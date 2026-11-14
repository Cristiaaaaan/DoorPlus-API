package io.github.cristiaaaaan.doorplus.api.event;

import io.github.cristiaaaaan.doorplus.api.trigger.Trigger;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Fired when a trigger source fires, before the door is operated. */
public class TriggerActivateEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Trigger trigger;
    private final @Nullable Player actor;
    private final boolean active;
    private boolean cancelled;

    public TriggerActivateEvent(@NotNull Trigger trigger, @Nullable Player actor, boolean active) {
        this.trigger = trigger;
        this.actor = actor;
        this.active = active;
    }

    public @NotNull Trigger getTrigger() {
        return trigger;
    }

    /** Player that fired the source, absent for redstone. */
    public @Nullable Player getActor() {
        return actor;
    }

    /** Source state: true when activated (powered, entered), false when deactivated (FOLLOW only). */
    public boolean isActive() {
        return active;
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
