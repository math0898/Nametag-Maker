package io.github.math0898.nametagmaker.logger;

/**
 * Loggers receive messages from the running program at various levels. After which they're printed to an output device
 * of some sort.
 *
 * @author Sugaku
 */
public interface Logger {

    /**
     * Sets the level at which messages should be output. Messages at the given level will also be printed.
     *
     * @param level The level which to start printing messages at.
     */
    void setLevel (Level level);

    /**
     * Sends the given exception to the logger.
     *
     * @param exception The exception to print to the console.
     */
    void log (Exception exception);

    /**
     * Sends a general message to the logger.
     *
     * @param message The message to send to the logger.
     */
    void log (String message);

    /**
     * Sends the given exception to the logger at a different level.
     *
     * @param exception The exception to print to the console.
     * @param level     The level at which to send the exception to the console.
     */
    void log (Exception exception, Level level);

    /**
     * Sends the given exception to the logger at a different level, with the given message.
     *
     * @param message   The message to send alongside the exception.
     * @param exception The exception to print to the console.
     * @param level     The level at which to send the exception to the console.
     */
    void log (String message, Exception exception, Level level);

    /**
     * Sends a message to the logger at a specific level.
     *
     * @param message The message to send to the logger.
     * @param level   The level at which to send the message.
     */
    void log (String message, Level level);

    /**
     * Sends a message to the logger with, or without, the level prefix at the given level.
     *
     * @param message The message to send to the logger.
     * @param level   The level at which to send the message.
     * @param prefix  Whether to send the level prefix with this message or not.
     */
    void log (String message, Level level, boolean prefix);
}
