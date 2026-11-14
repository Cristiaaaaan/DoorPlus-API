package io.github.cristiaaaaan.doorplus.api.hook;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Door+ side of a {@link CustomContentProvider}. Every method returns true if the provider event must be cancelled. */
public interface ContentListener {

    boolean onBlockInteract(@NotNull Player player, @NotNull Block block, @NotNull String id);

    boolean onFurnitureInteract(@NotNull Player player, @NotNull Entity baseEntity, @NotNull String id);

    boolean onBlockBreak(@Nullable Player player, @NotNull Block block, @NotNull String id);

    boolean onFurnitureBreak(@Nullable Player player, @NotNull Entity baseEntity, @NotNull String id);
}
