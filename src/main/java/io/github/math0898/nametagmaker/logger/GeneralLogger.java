package io.github.math0898.nametagmaker.logger;

/**
 * The general logger prints all messages it receives to the console with ANSI color codes, a file located at
 * ./logs/latest.log, and another file located at ./logs/'date'.log.
 *
 * @author Sugaku
 */
public class GeneralLogger implements Logger {

    /**
     * The level to start printing messages at.
     */
    private Level printLevel = Level.DEBUG;

    /**
     * Initializes and creates the general logger object.
     */
    public GeneralLogger () {
        log("""
                \u001B[1;90m  _   _                      _                __  __       _
                \u001B[1;90m | \\ | | __ _ _ __ ___   ___| |_ __ _  __ _  |  \\/  | __ _| | _____ _ __
                \u001B[1;90m |  \\| |/ _` | '_ ` _ \\ / _ \\ __/ _` |/ _` | | |\\/| |/ _` | |/ / _ \\ '__|
                \u001B[1;90m | |\\  | (_| | | | | | |  __/ || (_| | (_| | | |  | | (_| |   <  __/ |
                \u001B[1;90m |_| \\_|\\__,_|_| |_| |_|\\___|\\__\\__,_|\\__, | |_|  |_|\\__,_|_|\\_\\___|_|
                \u001B[1;90m                                      |___/""", Level.INFO, false);
    }

    /**
     * Sets the level at which messages should be output. Messages at the given level will also be printed.
     *
     * @param level The level which to start printing messages at.
     */
    @Override
    public void setLevel (Level level) {
        printLevel = level;
    }

    /**
     * Sends the given exception to the logger.
     *
     * @param exception The exception to print to the console.
     */
    @Override
    public void log (Exception exception) {
        log(exception, Level.EXCEPTION);
    }

    /**
     * Sends a general message to the logger.
     *
     * @param message The message to send to the logger.
     */
    @Override
    public void log (String message) {
        log(message, Level.INFO);
    }

    /**
     * Sends the given exception to the logger at a different level.
     *
     * @param exception The exception to print to the console.
     * @param level     The level at which to send the exception to the console.
     */
    @Override
    public void log (Exception exception, Level level) {
        log(exception.getMessage(), level);
        int i = 0;
        for (StackTraceElement se : exception.getStackTrace()) {
            if (i >= 10) break;
            i++;
            log("        " + se.toString(), level, false);
        }
    }

    /**
     * Sends the given exception to the logger at a different level, with the given message.
     *
     * @param message   The message to send alongside the exception.
     * @param exception The exception to print to the console.
     * @param level     The level at which to send the exception to the console.
     */
    @Override
    public void log (String message, Exception exception, Level level) {
        log(message, level);
        log(exception, level);
    }

    /**
     * Sends a message to the logger at a specific level.
     *
     * @param message The message to send to the logger.
     * @param level   The level at which to send the message.
     */
    @Override
    public void log (String message, Level level) {
        log(message, level, true);
    }

    /**
     * Sends a message to the logger with, or without, the level prefix at the given level.
     *
     * @param message The message to send to the logger.
     * @param level   The level at which to send the message.
     * @param prefix  Whether to send the level prefix with this message or not.
     */
    @Override
    public void log (String message, Level level, boolean prefix) {
        if (level.getUrgency() < printLevel.getUrgency()) return;
        String output = "";
        if (prefix) output += level.toString();
        else output += level.getColorCode();
        output += message;
        System.out.println(output);
    }
}
