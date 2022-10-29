package io.github.math0898.nametagmaker.logger;

/**
 * Levels for messages that are sent to loggers.
 *
 * @author Sugaku
 */
public enum Level {

    /**
     * Fatal includes errors that are fatal and could result in a hard crash.
     */
    FATAL("\u001B[0;31m", 4),

    /**
     * An error is a more extreme version of an exception that could cause problems in other areas.
     */
    ERROR("\u001B[0;31m", 3),

    /**
     * An exception is simply an issue a single system ran into. It may result in reduced functionality for that system.
     */
    EXCEPTION("\u001B[0;31m", 2),

    /**
     * Warning occurs when an issue arises when trying to use some additional functionality of a system. Core systems
     * will work as intended however extra bells and whistles might not.
     */
    WARNING("\u001B[0;33m", 1),

    /**
     * A general message reporting general information.
     */
    INFO("\u001B[0;0m", 0),

    /**
     * A message that can be used for debugging processes and games.
     */
    DEBUG("\u001B[0;36m", -1),

    /**
     * Messages that can sometimes be useful for debugging but often can simply clutter a log.
     */
    VERBOSE("\u001B[1;90m", -2);

    /**
     * The ANSI code that could be used to color any messages sent at the specific level.
     */
    private final String colorCode;

    /**
     * The number version of the level. Used to compare levels to each other in terms of urgency.
     */
    private final int urgency;

    /**
     * The color of brackets to bound the level.
     */
    private final String BRACKET_COLOR = "\u001B[1;90m";

    /**
     * Creates a new Level with the given color code.
     *
     * @param color The ANSI color code associated with this logging level.
     * @param urgency The urgency of the level. Higher numbers mean higher urgency.
     */
    Level (String color, int urgency) {
        colorCode = color;
        this.urgency = urgency;
    }

    /**
     * Converts this level to a string with ANSI color codes included.
     *
     * @return The string version of this logging level with ANSI color codes.
     */
    @Override
    public String toString () {
        return BRACKET_COLOR + "[" + colorCode + super.toString() + BRACKET_COLOR + "] " + colorCode;
    }

    /**
     * Converts this level to a string with or without ANSI color codes included.
     *
     * @param ansi Whether to include ANSI color codes in the returned string.
     * @return The string version of this logging level with ANSI color codes or not.
     */
    public String toString (boolean ansi) {
        if (ansi) return BRACKET_COLOR + "[" + colorCode + super.toString() + BRACKET_COLOR + "] " + colorCode;
        else return "[" + super.toString() + "] ";
    }

    /**
     * Accessor method for the ANSI color code being used by this level.
     *
     * @return The ANSI color code attached to this log level.
     */
    public String getColorCode () {
        return colorCode;
    }

    /**
     * Accessor method for the urgency level.
     *
     * @return The urgency level of the logging level.
     */
    public int getUrgency () {
        return urgency;
    }
}
