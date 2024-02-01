package me.hsgamer.muteplus.listener;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.muteplus.MutePlus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class ChatListener implements Listener {
    private final MutePlus plugin;

    public ChatListener(MutePlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (plugin.getGlobalMuteManager().isMuted(player.getUniqueId())) {
            event.setCancelled(true);
            MessageUtils.sendMessage(player, plugin.getMessageConfig().globalMute());
            return;
        }

        event.getRecipients().removeIf(toPlayer -> plugin.getPlayerMuteManager().isMuted(uuid, toPlayer.getUniqueId()));
    }
}
