package io.github.cristiaaaaan.doorplus.api.door;

/** Who may use (open and close) a door. Editing always depends on the role. */
public enum Privacy {
    /** Owner and admins. */
    PRIVATE,
    /** Owner, admins and every access entry. */
    RESTRICTED,
    /** Everyone. */
    PUBLIC;

    /** Next value in the privacy button cycle: PRIVATE → RESTRICTED → PUBLIC → PRIVATE. */
    public Privacy next() {
        return values()[(ordinal() + 1) % values().length];
    }
}
