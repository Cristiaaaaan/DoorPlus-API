package io.github.cristiaaaaan.doorplus.api.group;

import io.github.cristiaaaaan.doorplus.api.door.Door;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.UUID;

/** Live view of a named set of doors operated together. */
public interface DoorGroup {

    long getId();

    @NotNull String getName();

    @NotNull UUID getOwner();

    /** Member doors. */
    @NotNull Collection<Door> getDoors();

    long getCreatedAt();
}
