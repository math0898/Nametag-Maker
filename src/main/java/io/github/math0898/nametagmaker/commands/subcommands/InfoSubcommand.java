package io.github.math0898.nametagmaker.commands.subcommands;

import io.github.math0898.nametagmaker.TagGroup;
import io.github.math0898.nametagmaker.Tags;
import io.github.math0898.nametagmaker.commands.Subcommand;
import io.github.math0898.nametagmaker.lang.Lang;
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
        sender.sendMessage(Lang.generateHeader());
        g.fancyPrint(sender);
        return true;
    }
}
