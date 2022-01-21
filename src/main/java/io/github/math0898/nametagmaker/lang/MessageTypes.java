package io.github.math0898.nametagmaker.lang;

import java.util.ArrayList;
import java.util.List;

/**
 * The type of messages that are able to be sent to players.
 *
 * @author Sugaku
 */
public enum MessageTypes {

    /**
     * The main prefix used for any messages sent during runtime.
     */
    PREFIX,

    /**
     * The message sent when attempting to disable the plugin and its already disabled.
     */
     ALREADY_DISABLED,

    /**
     * The message sent when disabling the plugins is successful.
     */
     DISABLE,

    /**
     * The message sent on how to re-enable the plugin.
     */
     RE_ENABLE,

    /**
     * The message sent on how to re-disable the plugin.
     */
     RE_DISABLE,

    /**
     * The message sent when a tag name is not specified.
     */
     NO_TAG,

    /**
     * The message sent when a tag is not found.
     */
     TAG_NOT_FOUND,

    /**
     * The message sent to console when the plugin is disabled.
     */
     DISABLED,

    /**
     * The message sent when the plugin is enabled.
     */
     ENABLE,

    /**
     * The message sent to console when the plugin is enabled.
     */
     ENABLED,

    /**
     * The message sent when attempting to enable the plugin and its already enabled.
     */
     ALREADY_ENABLED,

    /**
     * The message sent when starting to refresh nametags.
     */
     REFRESHING,

    /**
     * The message sent when finished refreshing nametags.
     */
     REFRESHED,

    /**
     * The message sent when attempting to refresh whilst the plugin is disabled.
     */
     REFRESH_DISABLED,

    /**
     * The message sent when reloading the language and configuration files.
     */
     RELOADING,

    /**
     * The message sent when reload is complete.
     */
     RELOADED,

    /**
     * The message sent when reload fails.
     */
     RELOAD_FAILED,

    /**
     * The message sent when a command is not found.
     */
     UNRECOGNIZED,

    /**
     * The message sent when mentioning /<MainCommand> help exists.
     */
     USE_HELP;

    /**
     * Accessor method for the string version of messages.
     *
     * @return A list of strings which each represent one of the enum values.
     */
    public static List<String> getStrings () {
        List<String> strings = new ArrayList<>();
        for (MessageTypes t : MessageTypes.values()) strings.add(t.toString().replace("_", "-"));
        return strings;
    }
}
