package io.github.cristiaaaaan.doorplus.api.access;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Access entry of a door: a subject with a role.
 *
 * @param subject the subject
 * @param role    {@link Role#USER} or {@link Role#ADMIN}
 * @param addedBy who added the entry, if known
 * @param addedAt epoch milliseconds
 */
public record AccessEntry(@NotNull AccessSubject subject, @NotNull Role role, @Nullable UUID addedBy, long addedAt) {
}
