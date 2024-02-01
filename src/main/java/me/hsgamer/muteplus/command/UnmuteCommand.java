package me.hsgamer.muteplus.command;

import me.hsgamer.muteplus.MutePlus;
import me.hsgamer.muteplus.Permissions;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Collections;

public class UnmuteCommand extends PlayerTargetCommand {
    public UnmuteCommand(MutePlus plugin) {
        super(plugin, "unmute", "Unmute a player", Collections.emptyList());
        setPermission(Permissions.UNMUTE.getName());
    }

    @Override
    protected void execute(Player player, OfflinePlayer target) {
        plugin.getPlayerMuteManager().setMute(target.getUniqueId(), player.getUniqueId(), false);
    }
}
