package me.hsgamer.muteplus.command;

import me.hsgamer.muteplus.MutePlus;
import me.hsgamer.muteplus.Permissions;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class GlobalMuteCommand extends TargetCommand {
    public GlobalMuteCommand(MutePlus plugin) {
        super(plugin, "globalmute", "Mute a player globally", Arrays.asList("gmute", "glmute"));
        setPermission(Permissions.GLOBAL_MUTE.getName());
    }

    @Override
    protected boolean execute(CommandSender sender, OfflinePlayer target) {
        plugin.getGlobalMuteManager().setMute(target.getUniqueId(), true);
        return true;
    }
}
