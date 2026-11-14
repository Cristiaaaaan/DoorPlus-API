package io.github.cristiaaaaan.doorplus.api.event;

import io.github.cristiaaaaan.doorplus.api.trigger.TriggerSpec;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Fired before a trigger is linked. */
public class TriggerAddEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final TriggerSpec draft;
    private final @Nullable Player player;
    private boolean cancelled;

    public TriggerAddEvent(@NotNull TriggerSpec draft, @Nullable Player player) {
        this.draft = draft;
        this.player = player;
    }

    public @NotNull TriggerSpec getDraft() {
        return draft;
    }

    /** Player linking the trigger, absent for API and import. */
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
