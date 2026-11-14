package io.github.cristiaaaaan.doorplus.api;

import io.github.cristiaaaaan.doorplus.api.access.AccessManager;
import io.github.cristiaaaaan.doorplus.api.door.DoorManager;
import io.github.cristiaaaaan.doorplus.api.group.GroupManager;
import io.github.cristiaaaaan.doorplus.api.hook.HookRegistry;
import io.github.cristiaaaaan.doorplus.api.trigger.TriggerManager;
import org.jetbrains.annotations.NotNull;

/**
 * Entry point of the Door+ API. Obtain it with {@link DoorPlusProvider#get()} or from Bukkit's
 * {@code ServicesManager}. Unless stated otherwise, methods must be called from the main thread.
 */
public interface DoorPlusApi {

    /** Version of the API implemented by the running plugin. */
    @NotNull String apiVersion();

    @NotNull DoorManager doors();

    @NotNull AccessManager access();

    @NotNull TriggerManager triggers();

    @NotNull GroupManager groups();

    @NotNull HookRegistry hooks();
}
