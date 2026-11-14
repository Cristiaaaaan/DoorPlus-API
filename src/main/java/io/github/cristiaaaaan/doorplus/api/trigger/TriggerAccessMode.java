package io.github.cristiaaaaan.doorplus.api.trigger;

/** Per-trigger override of the door privacy. */
public enum TriggerAccessMode {
    /** Follow the door privacy. */
    INHERIT,
    /** Anyone, regardless of privacy and key. */
    PUBLIC,
    /** Owner, admins and the trigger's own subjects. */
    RESTRICTED,
    /** Owner and admins. */
    OWNER_ONLY;

    /** Next value in the menu cycle. */
    public TriggerAccessMode next() {
        return values()[(ordinal() + 1) % values().length];
    }
}
