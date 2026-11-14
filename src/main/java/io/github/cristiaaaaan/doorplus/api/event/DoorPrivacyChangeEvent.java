package io.github.cristiaaaaan.doorplus.api.event;

import io.github.cristiaaaaan.doorplus.api.door.Door;
import io.github.cristiaaaaan.doorplus.api.door.Privacy;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/** Fired before the privacy of a door changes. */
public class DoorPrivacyChangeEvent extends DoorEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Privacy oldPrivacy;
    private final Privacy newPrivacy;
    private final @Nullable UUID actor;
    private boolean cancelled;

    public DoorPrivacyChangeEvent(@NotNull Door door, @NotNull Privacy oldPrivacy, @NotNull Privacy newPrivacy,
                                  @Nullable UUID actor) {
        super(door);
        this.oldPrivacy = oldPrivacy;
        this.newPrivacy = newPrivacy;
        this.actor = actor;
    }

    public @NotNull Privacy getOldPrivacy() {
        return oldPrivacy;
    }

    public @NotNull Privacy getNewPrivacy() {
        return newPrivacy;
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
