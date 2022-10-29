package io.github.math0898.nametagmaker.commands;

import io.github.math0898.nametagmaker.commands.subcommands.*;
import io.github.math0898.nametagmaker.lang.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * This is the main command for interacting with the plugin as a whole.
 *
 * @author Sugaku
 */
public class MainCommand implements CommandExecutor, TabCompleter {

    /**
     * A map of subcommands indexed by the first argument to invoke them.
     */
    private final Map<String, Subcommand> subcommands = new HashMap<>();

    /**
     * Creates the main nametagmaker command.
     */
    public MainCommand () { // TODO: Could a design pattern be used here?
        subcommands.put("info", new InfoSubcommand());
        subcommands.put("create", new CreateSubcommand());
        subcommands.put("help", new HelpSubcommand());
        subcommands.put("status", new StatusSubcommand());
        subcommands.put("disable", new DisableSubcommand());
        subcommands.put("edit", new EditSubcommand());
        subcommands.put("enable", new EnableSubcommand());
        subcommands.put("refresh", new RefreshSubcommand());
        subcommands.put("reload", new ReloadSubcommand());
    }

    /**
     * Executes the main command behind the Nametag Maker plugin.
     *
     * @param sender  Source of the command.
     * @param command Command which was executed.
     * @param label   Alias of the command which was used.
     * @param args    Passed command arguments.
     * @return true if a valid command, otherwise false.
     */
    @Override
    public boolean onCommand (@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) subcommands.get("status").execute(sender, args);
        else {
            Subcommand s = subcommands.get(args[0].toLowerCase());
            if (s != null) return s.execute(sender, args);
            else sender.sendMessage(Lang.prefix + Lang.unrecognized);
        }
        return true;
    }

    /**
     * A helper method which removes all completions which can not be reached by adding letters to the current
     * arguments. Does this purge in place and also returns the list.
     *
     * @param currentOptions The comprehensive list of potential completions.
     * @param args           The arguments that have been written so far.
     * @return The curated list of completions.
     */
    private List<String> removeBadCompletions (List<String> currentOptions, String[] args) {
        if (!args[args.length - 1].equals("")) currentOptions.removeIf(o -> !o.toLowerCase().startsWith(args[args.length - 1].toLowerCase()));
        return currentOptions;
    }

    /**
     * Requests a list of possible completions for a command argument.
     *
     * @param sender  Source of the command.  For players tab-completing a
     *                command inside a command block, this will be the player, not
     *                the command block.
     * @param command Command which was executed.
     * @param alias   The alias used.
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed and command label.
     * @return A List of possible completions for the final argument, or null
     * to default to the command executor.
     */
    @Override
    public List<String> onTabComplete (@NotNull CommandSender sender, Command command, @NotNull String alias, String[] args) {
        if (!command.getName().equalsIgnoreCase("nametag")) return null;
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 1) list.addAll(subcommands.keySet());
        else {
            Subcommand s = subcommands.get(args[0]);
            if (s != null) list.addAll(s.tabOptions(sender, args));
        }
        list.sort(String::compareTo);
        return removeBadCompletions(list, args);
    }
}
