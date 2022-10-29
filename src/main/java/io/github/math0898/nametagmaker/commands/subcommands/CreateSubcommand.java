package io.github.math0898.nametagmaker.commands.subcommands;

import io.github.math0898.nametagmaker.Editor;
import io.github.math0898.nametagmaker.NametagApplier;
import io.github.math0898.nametagmaker.TagGroup;
import io.github.math0898.nametagmaker.Tags;
import io.github.math0898.nametagmaker.commands.Subcommand;
import io.github.math0898.nametagmaker.lang.Lang;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The CreateSubcommand is used to create new tags with certain values.
 *
 * @author Sugaku
 */
public class CreateSubcommand implements Subcommand {

    /**
     * Executes this subcommand as the given player with the given arguments.
     *
     * @param sender The sender of this particular command.
     * @param args   A comprehensive list of arguments passed at command execution.
     */
    @Override
    public boolean execute (CommandSender sender, String[] args) {
        if (args.length < 2) {
            Lang.sendEditorHelp(sender, "create");
            return false;
        }
        StringBuilder input = new StringBuilder();
        for (int i = 1; i < args.length; i++) input.append(" ").append(args[i]);
        TagGroup tag = TagGroup.parseTag(input.toString());
        if (tag == null) {
            sender.sendMessage(Lang.prefix + ChatColor.RED + "You " + ChatColor.UNDERLINE + "MUST" + ChatColor.RESET + ChatColor.RED + " specify a name with name:<name>");
            return false;
        }
        Tags.groups.add(tag);
        Tags.save();
        NametagApplier.clean();
        NametagApplier.init();
        NametagApplier.refresh();
        sender.sendMessage(Lang.prefix + "Created new group " + ChatColor.GOLD + tag.name + ChatColor.GRAY + ".");
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
        if (args.length == 2) list.add("name:");
        else if (args.length >= 3) list.addAll(Editor.addMissingOptions(args));
        return list;
    }
}
