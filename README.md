# DoorPlus-API

Public API of **Door+**, the animated doors plugin for Paper. It contains only interfaces, enums, records and
events (package `io.github.cristiaaaaan.doorplus.api`). The Door+ plugin jar ships the same classes at the same
version, so your plugin compiles against this artifact and finds the implementation at runtime.

- Java 21, Paper 1.21.4 or newer.
- Semantic versioning: the Git tag is the version; an incompatible change raises the major version.

## Dependency

Replace `<version>` with a released tag (for example `1.0.0`).

**Gradle (Kotlin DSL)**

```kotlin
repositories {
    maven("https://jitpack.io")
}
dependencies {
    compileOnly("com.github.Cristiaaaaan:DoorPlus-API:<version>")
}
```

**Gradle (Groovy)**

```groovy
repositories { maven { url 'https://jitpack.io' } }
dependencies { compileOnly 'com.github.Cristiaaaaan:DoorPlus-API:<version>' }
```

**Maven**

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
<dependencies>
  <dependency>
    <groupId>com.github.Cristiaaaaan</groupId>
    <artifactId>DoorPlus-API</artifactId>
    <version><!-- version --></version>
    <scope>provided</scope>
  </dependency>
</dependencies>
```

Never shade or relocate the API into your plugin.

## plugin.yml

```yaml
depend: [DoorPlus]      # Door+ required
# or
softdepend: [DoorPlus]  # Door+ optional: check DoorPlusProvider.isAvailable() before using the API
```

## Example

```java
DoorPlusApi api = DoorPlusProvider.get();          // IllegalStateException if Door+ is not enabled
// also available from Bukkit.getServicesManager().load(DoorPlusApi.class)

api.doors().byId(42).ifPresent(door ->
    api.doors().toggle(door, ToggleOptions.open().actor(player.getUniqueId()))
       .thenAccept(outcome -> player.sendMessage("Result: " + outcome.result())));

@EventHandler
public void onPreToggle(DoorPreToggleEvent event) {
    if (event.getDoor().getName().startsWith("vault_") && !isHeistActive()) {
        event.setCancelled(true);
    }
}
```

## Entry points

| Accessor | Purpose |
|---|---|
| `api.doors()` | `DoorManager`: lookup (`byId`, `byName`, `ownedBy`, `all`, `at`, `inChunk`), `toggle`, `create`, `delete` |
| `api.access()` | `AccessManager`: `roleOf`, `canUse`, `grant`, `revoke` |
| `api.triggers()` | `TriggerManager`: lookup, `add`, `remove`, `activate` |
| `api.groups()` | `GroupManager`: lookup, `toggle` of every door of a group |
| `api.hooks()` | `HookRegistry`: `ProtectionHook`, `CustomContentProvider`, `NpcProvider` (removed automatically when your plugin disables) |
| `api.apiVersion()` | API version implemented by the running plugin |

- `ToggleOptions`: factories `toggle()`, `open()`, `close()`, `gotoStop(int)`; modifiers `actor(UUID)`,
  `instant()`, `checkAccess(boolean)`. Without an actor the access is not checked; with an actor it is, unless
  `checkAccess(false)`. When the access is checked the actor must be online, otherwise the result is `DENIED`.
  The lock flag is always honoured.
- Returned objects (`Door`, `Trigger`, `DoorGroup`) are live views of the plugin state, not copies. Pass back
  only objects obtained from the API: other implementations are rejected with `IllegalArgumentException`.
- `Door` setters apply the change, persist it and fire the corresponding event.
- Failures of `create`, `delete` and `TriggerManager.add` complete the future exceptionally with
  `IllegalArgumentException` (invalid input; the message is the reason) or `IllegalStateException`.

## Events

Package `io.github.cristiaaaaan.doorplus.api.event`. All synchronous, fired on the main thread.

| Event | Cancellable | When | Data |
|---|:-:|---|---|
| `DoorCreateEvent` | yes | Before a new door is saved | draft door, player |
| `DoorDeleteEvent` | yes | Before deletion | door, who deletes |
| `DoorPreToggleEvent` | yes | After every check, before a movement starts | door, from/to state, cause, actor, trigger, instant |
| `DoorToggleStartEvent` | no | Movement started | as above |
| `DoorToggleEndEvent` | no | Movement finished | as above |
| `DoorAccessCheckEvent` | no | End of the use check | player, door, trigger; `isAllowed()` / `setAllowed(boolean)` |
| `DoorPrivacyChangeEvent` | yes | Privacy change | old and new privacy |
| `DoorAccessChangeEvent` | yes | Access entry added, removed or role changed | subject, old and new role |
| `DoorOwnerChangeEvent` | yes | Ownership transfer | old and new owner |
| `TriggerAddEvent` | yes | Before a trigger is linked | draft trigger, player |
| `TriggerRemoveEvent` | no | After a trigger was removed | trigger, cause |
| `TriggerActivateEvent` | yes | Before a trigger operates its target | trigger, actor, active |

## Threads

- Call every method from the **main thread** unless its Javadoc says otherwise. Methods that change state
  throw `IllegalStateException` when called from another thread.
- Lookups (`byId`, `byName`, `ownedBy`, `all`, `at`, `inChunk`, `of`) read in-memory caches and may be called
  from any thread.
- Methods that may wait return a `CompletableFuture`, always completed on the main thread: it is safe to use
  the Bukkit API inside `thenAccept`.
- Hooks (`ProtectionHook`, content and NPC providers) are called on the main thread and must be fast:
  `canMove` runs for every movement.
