package me.hsgamer.muteplus.command;

import me.hsgamer.muteplus.MutePlus;
import me.hsgamer.muteplus.Permissions;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class GlobalUnmuteCommand extends TargetCommand {
    public GlobalUnmuteCommand(MutePlus plugin) {
        super(plugin, "globalunmute", "Unmute a player globally", Arrays.asList("gunmute", "glunmute"));
        setPermission(Permissions.GLOBAL_UNMUTE.getName());
    }

    @Override
    protected boolean execute(CommandSender sender, OfflinePlayer target) {
        plugin.getGlobalMuteManager().setMute(target.getUniqueId(), false);
        return true;
    }
}
