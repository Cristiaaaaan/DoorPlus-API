package io.github.cristiaaaaan.doorplus.api.event;

import io.github.cristiaaaaan.doorplus.api.door.Door;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

/** Base class of events about an existing door. Fired synchronously on the main thread. */
public abstract class DoorEvent extends Event {

    private final Door door;

    protected DoorEvent(@NotNull Door door) {
        this.door = door;
    }

    public @NotNull Door getDoor() {
        return door;
    }
}
