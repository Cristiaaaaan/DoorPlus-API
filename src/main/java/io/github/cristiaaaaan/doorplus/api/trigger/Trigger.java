package io.github.cristiaaaaan.doorplus.api.trigger;

import io.github.cristiaaaaan.doorplus.api.access.AccessSubject;
import io.github.cristiaaaaan.doorplus.api.geometry.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

/** Live read-only view of a trigger. */
public interface Trigger {

    long getId();

    @NotNull TriggerTarget getTarget();

    @NotNull TriggerType getType();

    /** {@code vanilla}, or the id of a content or NPC provider. */
    @NotNull String getProvider();

    @NotNull UUID getWorldId();

    /** Block position of the source block, entity or zone center. */
    @NotNull BlockPos getPosition();

    /** Base entity, for {@link TriggerType#ENTITY} and {@link TriggerType#FURNITURE}. */
    @Nullable UUID getEntityId();

    /** Custom block / furniture id, or NPC id. */
    @Nullable String getExternalId();

    @NotNull TriggerAction getAction();

    /** Stop index for {@link TriggerAction#GOTO}. */
    int getActionArg();

    @NotNull TriggerAccessMode getAccessMode();

    /** Own subjects, used by {@link TriggerAccessMode#RESTRICTED}. */
    @NotNull Collection<AccessSubject> getAccessSubjects();

    int getCooldownTicks();

    boolean isCancelVanilla();

    boolean isEnabled();

    /** Zone radius for {@link TriggerType#PROXIMITY}, 0 otherwise. */
    int getRadius();

    @Nullable UUID getCreatedBy();

    long getCreatedAt();
}
