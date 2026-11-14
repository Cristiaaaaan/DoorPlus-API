package io.github.cristiaaaaan.doorplus.api.event;

import io.github.cristiaaaaan.doorplus.api.door.Door;
import io.github.cristiaaaaan.doorplus.api.trigger.Trigger;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Fired with the computed result of an access check; listeners may change it. */
public class DoorAccessCheckEvent extends DoorEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final @Nullable Trigger trigger;
    private boolean allowed;

    public DoorAccessCheckEvent(@NotNull Player player, @NotNull Door door, @Nullable Trigger trigger, boolean allowed) {
        super(door);
        this.player = player;
        this.trigger = trigger;
        this.allowed = allowed;
    }

    public @NotNull Player getPlayer() {
        return player;
    }

    /** Trigger being used, {@code null} for commands and menus. */
    public @Nullable Trigger getTrigger() {
        return trigger;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLERS;
    }
}
