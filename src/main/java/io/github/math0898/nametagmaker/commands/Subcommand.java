package io.github.math0898.nametagmaker.commands;

import org.bukkit.command.CommandSender;

import java.util.Collection;

/**
 * Subcommands are subsections of commands which are selected using the first argument. They can be just as complex as a
 * full command but aren't independent.
 *
 * @author Sugaku
 */
public interface Subcommand {

    /**
     * Executes this subcommand as the given player with the given arguments.
     *
     * @param sender The sender of this particular command.
     * @param args   A comprehensive list of arguments passed at command execution.
     */
    boolean execute (CommandSender sender, String[] args);

    /**
     * Returns a list of subcommand options when ran by the given player with the given arguments.
     *
     * @param sender The sender of this particular command.
     * @param args   A comprehensive list of arguments which are pending.
     */
    Collection<String> tabOptions (CommandSender sender, String[] args);
}
