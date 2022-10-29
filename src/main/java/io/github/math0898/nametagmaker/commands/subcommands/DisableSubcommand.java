package io.github.math0898.nametagmaker.commands.subcommands;

import io.github.math0898.nametagmaker.Config;
import io.github.math0898.nametagmaker.NametagApplier;
import io.github.math0898.nametagmaker.commands.Subcommand;
import io.github.math0898.nametagmaker.lang.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * The DisableSubcommand is used to disable and prevent the plugin from assigning scoreboard teams to players.
 *
 * @author Sugaku
 */
public class DisableSubcommand implements Subcommand {

    /**
     * Executes this subcommand as the given player with the given arguments.
     *
     * @param sender The sender of this particular command.
     * @param args   A comprehensive list of arguments passed at command execution.
     */
    @Override
    public boolean execute (CommandSender sender, String[] args) {
        if (Config.enabled) {
            NametagApplier.clean();
            Config.enabled = false;
            sender.sendMessage(Lang.prefix + Lang.disable);
            sender.sendMessage(Lang.prefix + Lang.reEnable);
            Bukkit.getConsoleSender().sendMessage(Lang.prefix + sender.getName() + Lang.disabled);
        } else sender.sendMessage(Lang.prefix + Lang.alreadyDisabled);
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
