package io.github.cristiaaaaan.doorplus.api.geometry;

import org.bukkit.Axis;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Immutable axis-aligned box of blocks with inclusive integer corners.
 *
 * @param min lowest corner (inclusive)
 * @param max highest corner (inclusive)
 */
public record Cuboid(@NotNull BlockPos min, @NotNull BlockPos max) implements Iterable<BlockPos> {

    public Cuboid {
        if (min.x() > max.x() || min.y() > max.y() || min.z() > max.z()) {
            throw new IllegalArgumentException("min " + min + " is not <= max " + max);
        }
    }

    /** Cuboid spanning two arbitrary corners. */
    public static @NotNull Cuboid of(@NotNull BlockPos a, @NotNull BlockPos b) {
        return new Cuboid(
                new BlockPos(Math.min(a.x(), b.x()), Math.min(a.y(), b.y()), Math.min(a.z(), b.z())),
                new BlockPos(Math.max(a.x(), b.x()), Math.max(a.y(), b.y()), Math.max(a.z(), b.z())));
    }

    /** Single-block cuboid. */
    public static @NotNull Cuboid of(@NotNull BlockPos pos) {
        return new Cuboid(pos, pos);
    }

    public int sizeX() {
        return max.x() - min.x() + 1;
    }

    public int sizeY() {
        return max.y() - min.y() + 1;
    }

    public int sizeZ() {
        return max.z() - min.z() + 1;
    }

    /** Size along {@code axis}. */
    public int size(@NotNull Axis axis) {
        return switch (axis) {
            case X -> sizeX();
            case Y -> sizeY();
            case Z -> sizeZ();
        };
    }

    /** Number of block cells. */
    public long volume() {
        return (long) sizeX() * sizeY() * sizeZ();
    }

    public boolean contains(int x, int y, int z) {
        return x >= min.x() && x <= max.x() && y >= min.y() && y <= max.y() && z >= min.z() && z <= max.z();
    }

    public boolean contains(@NotNull BlockPos pos) {
        return contains(pos.x(), pos.y(), pos.z());
    }

    public boolean intersects(@NotNull Cuboid o) {
        return min.x() <= o.max.x() && max.x() >= o.min.x()
                && min.y() <= o.max.y() && max.y() >= o.min.y()
                && min.z() <= o.max.z() && max.z() >= o.min.z();
    }

    /** This cuboid moved by the given deltas. */
    public @NotNull Cuboid translate(int dx, int dy, int dz) {
        return new Cuboid(min.offset(dx, dy, dz), max.offset(dx, dy, dz));
    }

    /** Smallest cuboid containing both. */
    public @NotNull Cuboid union(@NotNull Cuboid o) {
        return new Cuboid(
                new BlockPos(Math.min(min.x(), o.min.x()), Math.min(min.y(), o.min.y()), Math.min(min.z(), o.min.z())),
                new BlockPos(Math.max(max.x(), o.max.x()), Math.max(max.y(), o.max.y()), Math.max(max.z(), o.max.z())));
    }

    /** Geometric center X of the occupied volume. */
    public double centerX() {
        return (min.x() + max.x() + 1) / 2.0;
    }

    /** Geometric center Y of the occupied volume. */
    public double centerY() {
        return (min.y() + max.y() + 1) / 2.0;
    }

    /** Geometric center Z of the occupied volume. */
    public double centerZ() {
        return (min.z() + max.z() + 1) / 2.0;
    }

    /** True if at least one dimension is 1. */
    public boolean isPlane() {
        return sizeX() == 1 || sizeY() == 1 || sizeZ() == 1;
    }

    /**
     * Axis along which the cuboid is one block thick, preferring horizontal axes for vertical planes.
     * {@code null} if no dimension is 1.
     */
    public @Nullable Axis thinAxis() {
        if (sizeX() == 1 && sizeZ() != 1) return Axis.X;
        if (sizeZ() == 1 && sizeX() != 1) return Axis.Z;
        if (sizeY() == 1) return Axis.Y;
        if (sizeX() == 1) return Axis.X;
        return null;
    }

    public int minChunkX() {
        return min.x() >> 4;
    }

    public int maxChunkX() {
        return max.x() >> 4;
    }

    public int minChunkZ() {
        return min.z() >> 4;
    }

    public int maxChunkZ() {
        return max.z() >> 4;
    }

    @Override
    public @NotNull Iterator<BlockPos> iterator() {
        return new Iterator<>() {
            private int x = min.x();
            private int y = min.y();
            private int z = min.z();
            private boolean done = false;

            @Override
            public boolean hasNext() {
                return !done;
            }

            @Override
            public BlockPos next() {
                if (done) throw new NoSuchElementException();
                BlockPos result = new BlockPos(x, y, z);
                if (++x > max.x()) {
                    x = min.x();
                    if (++z > max.z()) {
                        z = min.z();
                        if (++y > max.y()) done = true;
                    }
                }
                return result;
            }
        };
    }

    @Override
    public @NotNull String toString() {
        return "[" + min + " -> " + max + "]";
    }
}
