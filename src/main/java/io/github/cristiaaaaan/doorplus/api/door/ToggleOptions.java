package io.github.cristiaaaaan.doorplus.api.door;

import io.github.cristiaaaaan.doorplus.api.trigger.TriggerAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Immutable options of an API movement request. Every modifier returns a new instance.
 * <p>
 * Without an actor the access is not checked; with an actor it is, unless {@link #checkAccess(boolean)} is
 * set to {@code false}. The lock flag is always honoured.
 */
public final class ToggleOptions {

    private final TriggerAction action;
    private final int argument;
    private final @Nullable UUID actor;
    private final boolean instant;
    private final @Nullable Boolean checkAccess;

    private ToggleOptions(TriggerAction action, int argument, @Nullable UUID actor, boolean instant,
                          @Nullable Boolean checkAccess) {
        this.action = action;
        this.argument = argument;
        this.actor = actor;
        this.instant = instant;
        this.checkAccess = checkAccess;
    }

    /** Invert the state. */
    public static @NotNull ToggleOptions toggle() {
        return new ToggleOptions(TriggerAction.TOGGLE, 0, null, false, null);
    }

    /** Move towards "open". */
    public static @NotNull ToggleOptions open() {
        return new ToggleOptions(TriggerAction.OPEN, 0, null, false, null);
    }

    /** Move towards "closed". */
    public static @NotNull ToggleOptions close() {
        return new ToggleOptions(TriggerAction.CLOSE, 0, null, false, null);
    }

    /** Go to the given stop of a platform. */
    public static @NotNull ToggleOptions gotoStop(int stop) {
        if (stop < 0) throw new IllegalArgumentException("stop must be >= 0");
        return new ToggleOptions(TriggerAction.GOTO, stop, null, false, null);
    }

    /** Player responsible for the movement. */
    public @NotNull ToggleOptions actor(@Nullable UUID actor) {
        return new ToggleOptions(action, argument, actor, instant, checkAccess);
    }

    /** Move without animation. */
    public @NotNull ToggleOptions instant() {
        return new ToggleOptions(action, argument, actor, true, checkAccess);
    }

    /** Whether to check the actor's access. */
    public @NotNull ToggleOptions checkAccess(boolean check) {
        return new ToggleOptions(action, argument, actor, instant, check);
    }

    public @NotNull TriggerAction action() {
        return action;
    }

    public int argument() {
        return argument;
    }

    public @Nullable UUID actor() {
        return actor;
    }

    public boolean isInstant() {
        return instant;
    }

    /** Effective access check flag: explicit value, otherwise true only with an actor. */
    public boolean isAccessChecked() {
        return checkAccess != null ? checkAccess && actor != null : actor != null;
    }
}
