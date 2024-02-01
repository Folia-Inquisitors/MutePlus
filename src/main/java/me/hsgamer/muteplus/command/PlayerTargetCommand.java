package me.hsgamer.muteplus.command;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.muteplus.MutePlus;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class PlayerTargetCommand extends TargetCommand {
    public PlayerTargetCommand(MutePlus plugin, String name, String description, List<String> aliases) {
        super(plugin, name, description, aliases);
    }

    protected abstract void execute(Player player, OfflinePlayer target);

    @Override
    protected boolean execute(CommandSender sender, OfflinePlayer target) {
        if (sender instanceof Player) {
            execute((Player) sender, target);
            return true;
        } else {
            MessageUtils.sendMessage(sender, plugin.getMessageConfig().playerOnly());
            return false;
        }
    }
}
