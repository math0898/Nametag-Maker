package io.github.math0898.nametagmaker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is the main command for interacting with the plugin as a whole.
 *
 * @author Sugaku
 */
public class MainCommand implements CommandExecutor {

    /**
     * The tab completer which fills in options for the command as the player types.
     */
    public static TabCompleter autocomplete = new AutocompleteMainCommand();

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
    public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) usage(sender);
        else if (args[0].equalsIgnoreCase("disable")) disableSubcommand(sender);
        else if (args[0].equalsIgnoreCase("enable")) enableSubcommand(sender);
        else if (args[0].equalsIgnoreCase("help")) helpSubcommand(sender);
        else if (args[0].equalsIgnoreCase("refresh")) refreshSubcommand(sender);
        else if (args[0].equalsIgnoreCase("reload")) reloadSubcommand(sender);
        else sender.sendMessage(Lang.prefix + Lang.unrecognized);
        return true;
    }

    /**
     * Sends usage information to the given command sender.
     *
     * @param sender Source of the command.
     */
    private void usage (CommandSender sender) {
        String e;
        if (Config.enabled) e = ChatColor.GREEN + "Enabled";
        else e = ChatColor.RED + "Disabled";
        sender.sendMessage(Lang.prefix + ChatColor.GOLD + "Nametag " + ChatColor.AQUA + "Maker " + ChatColor.GRAY + main.version + " - " + e);
        sender.sendMessage(Lang.prefix + Lang.useHelp);
    }

    /**
     * Disables the plugin so that none of the functionality works other than this command. This only applies for to
     * runtime and will be overridden with config.yml on reload or restart.
     *
     * @param sender Source of the command.
     */
    private void disableSubcommand (CommandSender sender) {
        if (Config.enabled) {
            NametagApplier.clean();
            Config.enabled = false;
            sender.sendMessage(Lang.prefix + Lang.disable);
            sender.sendMessage(Lang.prefix + Lang.reEnable);
            Bukkit.getConsoleSender().sendMessage(Lang.prefix + sender.getName() + Lang.disabled);
        } else sender.sendMessage(Lang.prefix + Lang.alreadyDisabled);
    }

    /**
     * Enables the plugin so that the functionality of it will work again. This only applies for runtime and will be
     * overridden with config.yml on reload or restart.
     *
     * @param sender Source of the command.
     */
    private void enableSubcommand (CommandSender sender) {
        if (!Config.enabled) {
            Config.enabled = true;
            NametagApplier.init();
            NametagApplier.refresh();
            sender.sendMessage(Lang.prefix + Lang.enable);
            sender.sendMessage(Lang.prefix + Lang.reDisable);
            Bukkit.getConsoleSender().sendMessage(Lang.prefix + sender.getName() + Lang.enabled);
        } else sender.sendMessage(Lang.prefix + Lang.alreadyEnabled);
    }

    /**
     * Sends help information to the given command sender.
     *
     * @param sender Source of the command.
     */
    private void helpSubcommand (CommandSender sender) {
        String e;
        if (Config.enabled) e = ChatColor.GREEN + "Enabled";
        else e = ChatColor.RED + "Disabled";
        sender.sendMessage(Lang.prefix + ChatColor.GOLD + "Nametag " + ChatColor.AQUA + "Maker " + ChatColor.GRAY + main.version + " - " + e);
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/nametag disable");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/nametag enable");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/nametag help");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/nametag reload");
    }

    /**
     * Refreshes the nametags of all players currently on the server.
     *
     * @param sender Sender of the command.
     */
    private void refreshSubcommand (CommandSender sender) {
        sender.sendMessage(ChatColor.GRAY + "refresh"); //TODO: Lang
        NametagApplier.refresh();
    }

    /**
     * Reloads the configuration and lang files for the server.
     *
     * @param sender Source of the command.
     */
    private void reloadSubcommand (CommandSender sender) {
        sender.sendMessage(Lang.prefix + Lang.reloading);
        long start = System.currentTimeMillis();
        if (Config.read() && Lang.read()) {
            NametagApplier.clean();
            sender.sendMessage(Lang.prefix + Lang.reloaded);
            NametagApplier.init();
            NametagApplier.refresh();
            sender.sendMessage(Lang.prefix + "Took: " + (System.currentTimeMillis() - start) + "ms");
        } else sender.sendMessage(Lang.prefix + Lang.reloadFailed);
    }
}

/**
 * This class handles the tab completion of the command described above.
 *
 * @author Sugaku
 */
class AutocompleteMainCommand implements TabCompleter {

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
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("nametag")) {
            ArrayList<String> list = new ArrayList<>();
            String[] options = null;
            if (args.length == 1) options = new String[]{ "disable", "enable", "help", "refresh", "reload" };
            if (options == null) return null;
            if (!args[args.length - 1].equals("")) {
                for (String o : options) if (o.toLowerCase().startsWith(args[args.length - 1].toLowerCase())) list.add(o);
            } else list.addAll(Arrays.asList(options));
            return list;
        }
        return null;
    }
}