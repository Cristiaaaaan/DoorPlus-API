package io.github.cristiaaaaan.doorplus.api.door;

import io.github.cristiaaaaan.doorplus.api.access.AccessEntry;
import io.github.cristiaaaaan.doorplus.api.geometry.Cuboid;
import io.github.cristiaaaaan.doorplus.api.trigger.Trigger;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Live view of a door. Getters reflect the current plugin state. Setters apply the change, persist it and
 * fire the corresponding event; they must be called from the main thread.
 */
public interface Door {

    /** Stable numeric id. */
    long getId();

    @NotNull String getName();

    @NotNull DoorType getType();

    @NotNull UUID getOwner();

    @NotNull UUID getWorldId();

    /** Region occupied in state 0. */
    @NotNull Cuboid getBaseRegion();

    /** Cuboids occupied in the current state. */
    @NotNull List<Cuboid> getOccupancy();

    /** Current state: binary types 0 closed / 1 open; platforms the stop index; perpetual 0 stopped / 1 running. */
    int getState();

    /** True for binary types in state 1 and perpetual types running; platforms away from stop 0. */
    boolean isOpen();

    /** True while an animation is running. */
    boolean isMoving();

    @NotNull OpenDirection getOpenDirection();

    @NotNull Privacy getPrivacy();

    void setPrivacy(@NotNull Privacy privacy);

    boolean isLocked();

    void setLocked(boolean locked);

    boolean isRedstoneAllowed();

    void setRedstoneAllowed(boolean allowed);

    boolean areBlocksProtected();

    void setBlocksProtected(boolean protect);

    /** Auto-close delay in seconds, 0 = disabled. */
    int getAutoCloseSeconds();

    /** @throws IllegalArgumentException outside 0..max configured */
    void setAutoCloseSeconds(int seconds);

    double getSpeedMultiplier();

    /** @throws IllegalArgumentException outside the configured limits */
    void setSpeedMultiplier(double multiplier);

    /** Access entries (owner excluded). */
    @NotNull Collection<AccessEntry> getAccessEntries();

    /** Triggers whose target is this door. */
    @NotNull Collection<Trigger> getTriggers();
}
