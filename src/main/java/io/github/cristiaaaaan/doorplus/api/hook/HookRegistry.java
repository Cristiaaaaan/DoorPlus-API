package io.github.cristiaaaaan.doorplus.api.hook;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/** Extension points. Registrations are removed automatically when the owning plugin is disabled. */
public interface HookRegistry {

    void registerProtectionHook(@NotNull Plugin owner, @NotNull ProtectionHook hook);

    /** @throws IllegalArgumentException if a provider with the same id is registered */
    void registerContentProvider(@NotNull Plugin owner, @NotNull CustomContentProvider provider);

    /** @throws IllegalArgumentException if a provider with the same id is registered */
    void registerNpcProvider(@NotNull Plugin owner, @NotNull NpcProvider provider);
}
