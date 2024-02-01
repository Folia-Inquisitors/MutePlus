package me.hsgamer.muteplus.hook;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.muteplus.MutePlus;
import me.hsgamer.yatpa.event.TeleportRequestEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class YATPAHook implements Listener {
    private final MutePlus plugin;

    public YATPAHook(MutePlus plugin) {
        this.plugin = plugin;
    }

    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("YATPA") != null;
    }

    @EventHandler
    public void onRequest(TeleportRequestEvent event) {
        UUID requester = event.getRequestEntry().requester;
        UUID target = event.getRequestEntry().target;

        if (plugin.getGlobalMuteManager().isMuted(requester) || plugin.getPlayerMuteManager().isMuted(requester, target)) {
            event.setCancelled(true);
            MessageUtils.sendMessage(requester, plugin.getMessageConfig().yatpaCancel());
        }
    }
}
