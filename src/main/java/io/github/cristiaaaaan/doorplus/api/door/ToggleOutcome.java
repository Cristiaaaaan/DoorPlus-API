package io.github.cristiaaaaan.doorplus.api.door;

import io.github.cristiaaaaan.doorplus.api.geometry.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Outcome of a movement request.
 *
 * @param result the result
 * @param where  the offending block for {@link ToggleResult#BLOCKED_SOURCE} and {@link ToggleResult#OBSTRUCTED}
 */
public record ToggleOutcome(@NotNull ToggleResult result, @Nullable BlockPos where) {

    public static @NotNull ToggleOutcome of(@NotNull ToggleResult result) {
        return new ToggleOutcome(result, null);
    }
}
