package io.github.cristiaaaaan.doorplus.api.door;

/** Role of a key item in the access check. */
public enum KeyMode {
    /** No key. */
    NONE,
    /** Holding the key is enough, even without being in the access list. */
    ALTERNATIVE,
    /** Being in the access list and holding the key are both required. */
    REQUIRED
}
