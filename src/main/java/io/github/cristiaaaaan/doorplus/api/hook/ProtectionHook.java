package io.github.cristiaaaaan.doorplus.api.hook;

import io.github.cristiaaaaan.doorplus.api.door.Door;
import io.github.cristiaaaaan.doorplus.api.geometry.Cuboid;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Protection plugin hook. Every registered hook must allow an operation. Methods are called on the main
 * thread and must be fast; an exception counts as a denial.
 */
public interface ProtectionHook {

    /** Name shown to players when this hook denies an operation. */
    @NotNull String name();

    /** Creation and region redefinition: base region and destination occupancy. */
    boolean canCreate(@NotNull Player player, @NotNull World world, @NotNull Collection<Cuboid> regions);

    /** Every movement: destination occupancy. */
    boolean canMove(@NotNull Door door, @NotNull Collection<Cuboid> target);

    /** Linking a trigger source. */
    boolean canLinkTrigger(@NotNull Player player, @NotNull Location source);
}
