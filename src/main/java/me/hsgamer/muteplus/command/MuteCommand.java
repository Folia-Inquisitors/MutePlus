package me.hsgamer.muteplus.command;

import me.hsgamer.muteplus.MutePlus;
import me.hsgamer.muteplus.Permissions;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Collections;

public class MuteCommand extends PlayerTargetCommand {
    public MuteCommand(MutePlus plugin) {
        super(plugin, "mute", "Mute a player", Collections.emptyList());
        setPermission(Permissions.MUTE.getName());
    }

    @Override
    protected void execute(Player player, OfflinePlayer target) {
        plugin.getPlayerMuteManager().setMute(target.getUniqueId(), player.getUniqueId(), true);
    }
}
