package io.github.cristiaaaaan.doorplus.api.door;

/** How moving blocks interact with entities during the animation. */
public enum CollisionMode {
    /** Entities pass through moving blocks. */
    NONE,
    /** Moving blocks are solid (invisible shulker colliders). */
    SHULKER,
    /** Entities touched by moving blocks are pushed. */
    PUSH
}
