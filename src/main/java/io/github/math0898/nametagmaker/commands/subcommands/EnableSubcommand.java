package io.github.math0898.nametagmaker.commands.subcommands;

import io.github.math0898.nametagmaker.Config;
import io.github.math0898.nametagmaker.NametagApplier;
import io.github.math0898.nametagmaker.commands.Subcommand;
import io.github.math0898.nametagmaker.lang.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The EnableSubcommand is the counterpart to the DisableSubcommand and is used to re-enable the plugin after it has
 * been disabled.
 *
 * @author Sugaku
 */
public class EnableSubcommand implements Subcommand {

    /**
     * Executes this subcommand as the given player with the given arguments.
     *
     * @param sender The sender of this particular command.
     * @param args   A comprehensive list of arguments passed at command execution.
     */
    @Override
    public boolean execute (CommandSender sender, String[] args) {
        if (!Config.enabled) {
            Config.enabled = true;
            NametagApplier.init();
            NametagApplier.refresh();
            sender.sendMessage(Lang.prefix + Lang.enable);
            sender.sendMessage(Lang.prefix + Lang.reDisable);
            Bukkit.getConsoleSender().sendMessage(Lang.prefix + sender.getName() + Lang.enabled);
        } else sender.sendMessage(Lang.prefix + Lang.alreadyEnabled);
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
