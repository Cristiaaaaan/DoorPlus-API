package io.github.cristiaaaaan.doorplus.api.trigger;

import io.github.cristiaaaaan.doorplus.api.geometry.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Description of a trigger to add, also the draft carried by {@code TriggerAddEvent}.
 *
 * @param target        door or group
 * @param type          source kind
 * @param provider      {@code vanilla} or provider id
 * @param worldId       world of the source
 * @param position      block position of the source (entity block position, zone center)
 * @param entityId      entity for {@code ENTITY} / {@code FURNITURE}
 * @param externalId    custom id or NPC id
 * @param action        action
 * @param actionArg     stop for {@code GOTO}
 * @param accessMode    access override
 * @param cooldownTicks cooldown, {@code null} = configured default
 * @param cancelVanilla whether to cancel the vanilla interaction, {@code null} = default for the source
 * @param radius        zone radius for {@code PROXIMITY}
 * @param createdBy     creator, if any
 */
public record TriggerSpec(@NotNull TriggerTarget target, @NotNull TriggerType type, @NotNull String provider,
                          @NotNull UUID worldId, @NotNull BlockPos position, @Nullable UUID entityId,
                          @Nullable String externalId, @NotNull TriggerAction action, int actionArg,
                          @NotNull TriggerAccessMode accessMode, @Nullable Integer cooldownTicks,
                          @Nullable Boolean cancelVanilla, int radius, @Nullable UUID createdBy) {

    public TriggerSpec {
        Objects.requireNonNull(target, "target");
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(provider, "provider");
        Objects.requireNonNull(worldId, "worldId");
        Objects.requireNonNull(position, "position");
        Objects.requireNonNull(action, "action");
        Objects.requireNonNull(accessMode, "accessMode");
    }

    /** Vanilla block or redstone trigger with defaults. */
    public static @NotNull TriggerSpec block(@NotNull TriggerTarget target, @NotNull TriggerType type,
                                             @NotNull UUID worldId, @NotNull BlockPos position,
                                             @NotNull TriggerAction action) {
        return new TriggerSpec(target, type, "vanilla", worldId, position, null, null, action, 0,
                TriggerAccessMode.INHERIT, null, null, 0, null);
    }

    public @NotNull TriggerSpec withAccessMode(@NotNull TriggerAccessMode mode) {
        return new TriggerSpec(target, type, provider, worldId, position, entityId, externalId, action, actionArg,
                mode, cooldownTicks, cancelVanilla, radius, createdBy);
    }

    public @NotNull TriggerSpec withCreator(@Nullable UUID creator) {
        return new TriggerSpec(target, type, provider, worldId, position, entityId, externalId, action, actionArg,
                accessMode, cooldownTicks, cancelVanilla, radius, creator);
    }
}
