package io.github.cristiaaaaan.doorplus.api.hook;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/** Adapter to an NPC plugin. NPC ids must be stable across restarts. */
public interface NpcProvider {

    /** Provider id stored in triggers: {@code citizens}, {@code fancynpcs}, … */
    @NotNull String id();

    boolean exists(@NotNull String npcId);

    @NotNull Optional<Location> location(@NotNull String npcId);

    /** Binds the single Door+ listener; the provider forwards clicks and removals. */
    void bind(@NotNull NpcListener listener);
}
