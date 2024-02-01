package me.hsgamer.muteplus.command;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.muteplus.MutePlus;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class TargetCommand extends Command {
    protected final MutePlus plugin;

    public TargetCommand(MutePlus plugin, String name, String description, List<String> aliases) {
        super(name, description, "/" + name + " <player>", aliases);
        this.plugin = plugin;
    }

    protected abstract boolean execute(CommandSender sender, OfflinePlayer target);

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return false;
        }

        if (args.length == 0) {
            MessageUtils.sendMessage(sender, "&cUsage: " + getUsage());
            return false;
        }

        //noinspection deprecation
        OfflinePlayer target = plugin.getServer().getOfflinePlayer(args[0]);
        if (!target.hasPlayedBefore()) {
            MessageUtils.sendMessage(sender, plugin.getMessageConfig().playerNotFound());
            return false;
        }

        if (execute(sender, target)) {
            MessageUtils.sendMessage(sender, plugin.getMessageConfig().success());
        }
        return true;
    }
}
