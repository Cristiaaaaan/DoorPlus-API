package io.github.cristiaaaaan.doorplus.api.trigger;

/** Kind of trigger source. */
public enum TriggerType {
    /** Right click on a block, or pressure plate / tripwire. */
    BLOCK(true),
    /** A block receiving or losing redstone power. */
    REDSTONE(false),
    /** Click on a custom block of a content provider. */
    CUSTOM_BLOCK(true),
    /** Click on a furniture of a content provider. */
    FURNITURE(true),
    /** Right click on a vanilla entity. */
    ENTITY(true),
    /** Right click on an NPC of an NPC provider. */
    NPC(true),
    /** An authorised player entering a zone. */
    PROXIMITY(true);

    private final boolean hasActor;

    TriggerType(boolean hasActor) {
        this.hasActor = hasActor;
    }

    /** Whether activations of this type have a player actor. */
    public boolean hasActor() {
        return hasActor;
    }

    /** Lower-case id used in permissions ({@code doorplus.trigger.<id>}) and commands. */
    public String id() {
        return name().toLowerCase(java.util.Locale.ROOT);
    }
}
