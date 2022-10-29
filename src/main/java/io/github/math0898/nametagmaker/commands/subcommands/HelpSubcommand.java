package io.github.math0898.nametagmaker.commands.subcommands;

import io.github.math0898.nametagmaker.Config;
import io.github.math0898.nametagmaker.NametagMaker;
import io.github.math0898.nametagmaker.commands.Subcommand;
import io.github.math0898.nametagmaker.lang.Lang;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * The HelpSubcommand sends usage help to the command executor.
 *
 * @author Sugaku
 */
public class HelpSubcommand implements Subcommand {

    /**
     * Executes this subcommand as the given player with the given arguments.
     *
     * @param sender The sender of this particular command.
     * @param args   A comprehensive list of arguments passed at command execution.
     */
    @Override
    public boolean execute (CommandSender sender, String[] args) {
        String e;
        if (Config.enabled) e = ChatColor.GREEN + "Enabled";
        else e = ChatColor.RED + "Disabled";
        sender.sendMessage(Lang.prefix + ChatColor.GOLD + "Nametag " + ChatColor.AQUA + "Maker " + ChatColor.GRAY + NametagMaker.version + " - " + e);
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm create <name> <args>");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm disable");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm edit <tag> <args>");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm enable");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm help");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm info <tag>");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm refresh");
        sender.sendMessage(ChatColor.GRAY + "> " + ChatColor.GREEN + "/ntm reload");
        return true;
    }
}
