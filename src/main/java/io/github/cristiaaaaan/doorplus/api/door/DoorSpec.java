package io.github.cristiaaaaan.doorplus.api.door;

import io.github.cristiaaaaan.doorplus.api.geometry.BlockPos;
import io.github.cristiaaaaan.doorplus.api.geometry.Cuboid;
import org.bukkit.Axis;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Description of a door to create, also used as the draft carried by {@code DoorCreateEvent}.
 *
 * @param type          door type
 * @param name          name, {@code ^[A-Za-z0-9_-]{1,32}$}, unique per owner
 * @param owner         owner
 * @param worldId       world
 * @param region        base region (state 0)
 * @param pivot         rotation pivot block, for types that need one
 * @param pivotAxis     pivot axis, where not implicit
 * @param direction     opening direction; {@code null} = type default
 * @param blocksToMove  blocks to move, 0 = type default
 * @param typeData      type-specific parameters (e.g. {@code mode}, {@code stops}); unknown keys ignored
 */
public record DoorSpec(@NotNull DoorType type, @NotNull String name, @NotNull UUID owner, @NotNull UUID worldId,
                       @NotNull Cuboid region, @Nullable BlockPos pivot, @Nullable Axis pivotAxis,
                       @Nullable OpenDirection direction, int blocksToMove, @NotNull Map<String, Object> typeData) {

    public DoorSpec {
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(owner, "owner");
        Objects.requireNonNull(worldId, "worldId");
        Objects.requireNonNull(region, "region");
        typeData = Map.copyOf(typeData);
    }

    /** Spec with only the mandatory fields. */
    public static @NotNull DoorSpec of(@NotNull DoorType type, @NotNull String name, @NotNull UUID owner,
                                       @NotNull UUID worldId, @NotNull Cuboid region) {
        return new DoorSpec(type, name, owner, worldId, region, null, null, null, 0, Map.of());
    }

    public @NotNull DoorSpec withPivot(@Nullable BlockPos pivot, @Nullable Axis axis) {
        return new DoorSpec(type, name, owner, worldId, region, pivot, axis, direction, blocksToMove, typeData);
    }

    public @NotNull DoorSpec withDirection(@Nullable OpenDirection direction) {
        return new DoorSpec(type, name, owner, worldId, region, pivot, pivotAxis, direction, blocksToMove, typeData);
    }

    public @NotNull DoorSpec withBlocksToMove(int blocks) {
        return new DoorSpec(type, name, owner, worldId, region, pivot, pivotAxis, direction, blocks, typeData);
    }

    public @NotNull DoorSpec withTypeData(@NotNull Map<String, Object> data) {
        return new DoorSpec(type, name, owner, worldId, region, pivot, pivotAxis, direction, blocksToMove, data);
    }
}
