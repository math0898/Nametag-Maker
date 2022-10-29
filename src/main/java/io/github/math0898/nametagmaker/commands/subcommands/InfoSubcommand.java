package io.github.math0898.nametagmaker.commands.subcommands;

import io.github.math0898.nametagmaker.TagGroup;
import io.github.math0898.nametagmaker.Tags;
import io.github.math0898.nametagmaker.commands.Subcommand;
import io.github.math0898.nametagmaker.lang.Lang;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collection;

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

    /**
     * Returns a list of subcommand options when ran by the given player with the given arguments.
     *
     * @param sender The sender of this particular command.
     * @param args   A comprehensive list of arguments which are pending.
     */
    @Override
    public Collection<String> tabOptions (CommandSender sender, String[] args) {
        Collection<String> list = new ArrayList<>();
        if (args.length == 2) list.addAll(Tags.getTagNames());
        return list;
    }
}
