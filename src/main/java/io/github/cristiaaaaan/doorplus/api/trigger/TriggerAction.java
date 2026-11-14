package io.github.cristiaaaaan.doorplus.api.trigger;

/** What a trigger (or a request) asks the door to do. */
public enum TriggerAction {
    /** Invert the state. */
    TOGGLE,
    /** Move towards "open". */
    OPEN,
    /** Move towards "closed". */
    CLOSE,
    /** Source active = open, inactive = closed. */
    FOLLOW,
    /** Go to the platform stop given by the argument. */
    GOTO
}
