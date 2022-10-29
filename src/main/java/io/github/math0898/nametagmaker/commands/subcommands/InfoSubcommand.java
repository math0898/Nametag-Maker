package io.github.math0898.nametagmaker.commands.subcommands;

import io.github.math0898.nametagmaker.Config;
import io.github.math0898.nametagmaker.NametagMaker;
import io.github.math0898.nametagmaker.TagGroup;
import io.github.math0898.nametagmaker.Tags;
import io.github.math0898.nametagmaker.commands.Subcommand;
import io.github.math0898.nametagmaker.lang.Lang;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * The InfoSubcommand prints out the entire information of a registered tag.
 *
 * @author Sugaku
 */
public class InfoSubcommand implements Subcommand {

    /**
     * Executes this subcommand as the given player with the given arguments.
     *
     * @param sender The sender of this particular command.
     * @param args   A comprehensive list of arguments passed at command execution.
     */
    @Override
    public boolean execute (CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(Lang.prefix + Lang.noTag);
            return false;
        }
        TagGroup g = null;
        for (TagGroup t: Tags.groups) if (args[1].equalsIgnoreCase(t.name)) {
            g = t;
            break;
        }
        if (g == null) {
            sender.sendMessage(Lang.prefix + Lang.tagNotFound);
            return false;
        }
        String e;
        if (Config.enabled) e = ChatColor.GREEN + "Enabled";
        else e = ChatColor.RED + "Disabled";
        sender.sendMessage(Lang.prefix + ChatColor.GOLD + "Nametag " + ChatColor.AQUA + "Maker " + ChatColor.GRAY + NametagMaker.version + " - " + e);
        sender.sendMessage(ChatColor.GRAY + "> name: " + ChatColor.GOLD + g.name);
        sender.sendMessage(ChatColor.GRAY + "> prefix: \"" + g.prefix + ChatColor.GRAY + "\"");
        sender.sendMessage(ChatColor.GRAY + "> suffix: \"" + g.suffix + ChatColor.GRAY + "\"");
        sender.sendMessage(ChatColor.GRAY + "> color: " + g.color + "&" + g.color.getChar());
        sender.sendMessage(ChatColor.GRAY + "> permission: " + ChatColor.LIGHT_PURPLE + g.permission);
        if (g.visible) sender.sendMessage(ChatColor.GRAY + "> visible: " + ChatColor.GREEN + "true");
        else sender.sendMessage(ChatColor.GRAY + "> visible: " + ChatColor.RED + "false");
        sender.sendMessage(ChatColor.GRAY + "> weight: " + ChatColor.BLUE + g.weight);
        if (g.players.size() > 0) {
            sender.sendMessage(ChatColor.GRAY + "> players: ");
            for (String s: g.players) sender.sendMessage(ChatColor.GRAY + "  > " + ChatColor.AQUA + s);
        }
        sender.sendMessage(ChatColor.GRAY + "> preview: " + g.prefix + g.color + sender.getName() + g.suffix);
        return true;
    }
}
