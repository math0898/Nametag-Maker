package io.github.math0898.nametagmaker.commands.subcommands;

import io.github.math0898.nametagmaker.Config;
import io.github.math0898.nametagmaker.NametagApplier;
import io.github.math0898.nametagmaker.commands.Subcommand;
import io.github.math0898.nametagmaker.lang.Lang;
import org.bukkit.command.CommandSender;

/**
 * The RefreshSubcommand forcibly recalculates the tag that each player currently logged in should have.
 *
 * @author Sugaku
 */
public class RefreshSubcommand implements Subcommand {

    /**
     * Executes this subcommand as the given player with the given arguments.
     *
     * @param sender The sender of this particular command.
     * @param args   A comprehensive list of arguments passed at command execution.
     */
    @Override
    public boolean execute (CommandSender sender, String[] args) {
        if (Config.enabled) {
            sender.sendMessage(Lang.prefix + Lang.refreshing);
            NametagApplier.refresh();
            sender.sendMessage(Lang.prefix + Lang.refreshed);
        } else {
            sender.sendMessage(Lang.prefix + Lang.refreshDisabled);
            sender.sendMessage(Lang.prefix + Lang.reEnable);
        }
        return true;
    }
}
