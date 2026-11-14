package io.github.cristiaaaaan.doorplus.api.event;

import io.github.cristiaaaaan.doorplus.api.access.AccessSubject;
import io.github.cristiaaaaan.doorplus.api.access.Role;
import io.github.cristiaaaaan.doorplus.api.door.Door;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Fired before an access entry is added ({@code oldRole == null}), removed ({@code newRole == null}) or its
 * role changed.
 */
public class DoorAccessChangeEvent extends DoorEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final AccessSubject subject;
    private final @Nullable Role oldRole;
    private final @Nullable Role newRole;
    private final @Nullable UUID actor;
    private boolean cancelled;

    public DoorAccessChangeEvent(@NotNull Door door, @NotNull AccessSubject subject, @Nullable Role oldRole,
                                 @Nullable Role newRole, @Nullable UUID actor) {
        super(door);
        this.subject = subject;
        this.oldRole = oldRole;
        this.newRole = newRole;
        this.actor = actor;
    }

    public @NotNull AccessSubject getSubject() {
        return subject;
    }

    public @Nullable Role getOldRole() {
        return oldRole;
    }

    public @Nullable Role getNewRole() {
        return newRole;
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
