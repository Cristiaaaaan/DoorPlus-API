package io.github.cristiaaaaan.doorplus.api.hook;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/** Door+ side of an {@link NpcProvider}. */
public interface NpcListener {

    /** Right click on an NPC; returns true if the provider event must be cancelled. */
    boolean onInteract(@NotNull Player player, @NotNull String npcId);

    /** The NPC was permanently removed. */
    void onRemove(@NotNull String npcId);
}
