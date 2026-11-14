package io.github.cristiaaaaan.doorplus.api.hook;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/** Adapter to a plugin providing custom blocks, furniture and items. */
public interface CustomContentProvider {

    /** Provider id stored in triggers and keys: {@code itemsadder}, {@code nexo}, … */
    @NotNull String id();

    /** False until the plugin has loaded its data. */
    boolean isReady();

    /** Custom block id of an already placed block. */
    @NotNull Optional<String> customBlockId(@NotNull Block block);

    /** Furniture id of an already spawned furniture base entity. */
    @NotNull Optional<String> furnitureId(@NotNull Entity baseEntity);

    /** Custom item id, for {@code CUSTOM:} keys. */
    @NotNull Optional<String> itemId(@NotNull ItemStack item);

    /**
     * Binds the single Door+ listener. The provider listens to its plugin's events and forwards them,
     * always passing the furniture base entity, deduplicating main and off hand.
     */
    void bind(@NotNull ContentListener listener);
}
