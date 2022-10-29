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
 * The EditSubcommand is used to edit existing tags and replace their values.
 *
 * @author Sugaku
 */
public class EditSubcommand implements Subcommand {

    /**
     * Executes this subcommand as the given player with the given arguments.
     *
     * @param sender The sender of this particular command.
     * @param args   A comprehensive list of arguments passed at command execution.
     */
    @Override
    public boolean execute (CommandSender sender, String[] args) {
        if (args.length < 3) {
            Lang.sendEditorHelp(sender, "edit");
            return false;
        }
        TagGroup ref = Tags.findTeam(args[1]);
        if (ref == null) {
            sender.sendMessage(Lang.prefix + ChatColor.RED + "We could not find that team.");
            return false;
        }
        StringBuilder input = new StringBuilder();
        input.append("name:").append(args[1]);
        for (int i = 2; i < args.length; i++) input.append(" ").append(args[i]);
        ref.sync(TagGroup.parseTag(input.toString()));
        Tags.save();
        NametagApplier.clean();
        NametagApplier.init();
        NametagApplier.refresh();
        sender.sendMessage(Lang.prefix + "Modified group " + ChatColor.GOLD + ref.name + ChatColor.GRAY + ".");
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
        else if (args.length >= 3) {
            String check = "";
            for (String u: args) check += u;
            list.addAll(Editor.addMissingOptions(check));
        }
        return list;
    }
}
