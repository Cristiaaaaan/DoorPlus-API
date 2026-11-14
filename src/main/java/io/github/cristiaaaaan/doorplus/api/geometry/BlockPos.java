package io.github.cristiaaaaan.doorplus.api.geometry;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable integer block position, without a world.
 *
 * @param x block X
 * @param y block Y
 * @param z block Z
 */
public record BlockPos(int x, int y, int z) implements Comparable<BlockPos> {

    /** Position of a block. */
    public static @NotNull BlockPos of(@NotNull Block block) {
        return new BlockPos(block.getX(), block.getY(), block.getZ());
    }

    /** Block position containing a location. */
    public static @NotNull BlockPos of(@NotNull Location location) {
        return new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    /** This position moved by the given deltas. */
    public @NotNull BlockPos offset(int dx, int dy, int dz) {
        return new BlockPos(x + dx, y + dy, z + dz);
    }

    /** This position moved {@code distance} blocks towards {@code face}. */
    public @NotNull BlockPos relative(@NotNull BlockFace face, int distance) {
        return new BlockPos(x + face.getModX() * distance, y + face.getModY() * distance, z + face.getModZ() * distance);
    }

    /** This position moved one block towards {@code face}. */
    public @NotNull BlockPos relative(@NotNull BlockFace face) {
        return relative(face, 1);
    }

    /** Chunk X coordinate. */
    public int chunkX() {
        return x >> 4;
    }

    /** Chunk Z coordinate. */
    public int chunkZ() {
        return z >> 4;
    }

    /** Squared euclidean distance between the two positions. */
    public long distanceSquared(@NotNull BlockPos other) {
        long dx = (long) x - other.x;
        long dy = (long) y - other.y;
        long dz = (long) z - other.z;
        return dx * dx + dy * dy + dz * dz;
    }

    /** Corner location of this block in {@code world}. */
    public @NotNull Location toLocation(@NotNull World world) {
        return new Location(world, x, y, z);
    }

    /** Center location of this block in {@code world}. */
    public @NotNull Location toCenterLocation(@NotNull World world) {
        return new Location(world, x + 0.5, y + 0.5, z + 0.5);
    }

    /** The block at this position in {@code world}. */
    public @NotNull Block block(@NotNull World world) {
        return world.getBlockAt(x, y, z);
    }

    @Override
    public int compareTo(@NotNull BlockPos o) {
        int c = Integer.compare(y, o.y);
        if (c != 0) return c;
        c = Integer.compare(x, o.x);
        if (c != 0) return c;
        return Integer.compare(z, o.z);
    }

    @Override
    public @NotNull String toString() {
        return x + "," + y + "," + z;
    }
}
