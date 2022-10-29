package io.github.math0898.nametagmaker.commands.subcommands;

import io.github.math0898.nametagmaker.commands.Subcommand;
import io.github.math0898.nametagmaker.lang.Lang;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The StatusSubcommand shows the general status of the plugin as a whole as well has how to get more usage help.
 *
 * @author Sugaku
 */
public class StatusSubcommand implements Subcommand {

    /**
     * Executes this subcommand as the given player with the given arguments.
     *
     * @param sender The sender of this particular command.
     * @param args   A comprehensive list of arguments passed at command execution.
     */
    @Override
    public boolean execute (CommandSender sender, String[] args) {
        sender.sendMessage(Lang.generateHeader());
        sender.sendMessage(Lang.prefix + Lang.useHelp);
        return true;
    }

    /**
     * Returns a list of subcommand options when ran by the given player with the given arguments.
     *
     * @param sender The sender of this particular command.
     * @param args   A comprehensive list of arguments which are pending.
     */
    @Override
    public Collection<String> tabOptions (CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
