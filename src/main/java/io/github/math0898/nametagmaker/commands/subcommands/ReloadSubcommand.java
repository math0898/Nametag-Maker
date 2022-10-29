package io.github.math0898.nametagmaker.commands.subcommands;

import io.github.math0898.nametagmaker.Config;
import io.github.math0898.nametagmaker.NametagApplier;
import io.github.math0898.nametagmaker.commands.Subcommand;
import io.github.math0898.nametagmaker.lang.Lang;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The ReloadSubcommand reloads the information saved in the disk files.
 *
 * @author Sugaku
 */
public class ReloadSubcommand implements Subcommand {

    /**
     * Executes this subcommand as the given player with the given arguments.
     *
     * @param sender The sender of this particular command.
     * @param args   A comprehensive list of arguments passed at command execution.
     */
    @Override
    public boolean execute (CommandSender sender, String[] args) {
        sender.sendMessage(Lang.prefix + Lang.reloading);
        long start = System.currentTimeMillis();
        if (Config.read() && Lang.read()) {
            NametagApplier.clean();
            NametagApplier.init();
            NametagApplier.refresh();
            sender.sendMessage(Lang.prefix + Lang.reloaded);
            sender.sendMessage(Lang.prefix + "Took: " + (System.currentTimeMillis() - start) + "ms");
        } else sender.sendMessage(Lang.prefix + Lang.reloadFailed);
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
