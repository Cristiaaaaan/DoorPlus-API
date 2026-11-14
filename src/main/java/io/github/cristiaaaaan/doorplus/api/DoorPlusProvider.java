package io.github.cristiaaaaan.doorplus.api;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Static access to the running {@link DoorPlusApi}. */
public final class DoorPlusProvider {

    private static volatile @Nullable DoorPlusApi instance;

    private DoorPlusProvider() {
    }

    /**
     * The running API.
     *
     * @throws IllegalStateException if Door+ is not enabled
     */
    public static @NotNull DoorPlusApi get() {
        DoorPlusApi api = instance;
        if (api == null) throw new IllegalStateException("Door+ is not enabled");
        return api;
    }

    /** Whether Door+ is enabled. */
    public static boolean isAvailable() {
        return instance != null;
    }

    /** Version of these API classes, read from the jar. */
    public static @NotNull String version() {
        try (InputStream in = DoorPlusProvider.class.getResourceAsStream("/doorplus-api.properties")) {
            if (in == null) return "unknown";
            Properties props = new Properties();
            props.load(in);
            return props.getProperty("version", "unknown");
        } catch (IOException e) {
            return "unknown";
        }
    }

    @ApiStatus.Internal
    public static void register(@NotNull DoorPlusApi api) {
        instance = api;
    }

    @ApiStatus.Internal
    public static void unregister() {
        instance = null;
    }
}
