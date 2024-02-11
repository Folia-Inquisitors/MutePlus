package me.hsgamer.muteplus.listener;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.muteplus.MutePlus;
import me.hsgamer.muteplus.fetcher.CommandResult;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Locale;
import java.util.Optional;

public class CommandListener implements Listener {
    private final MutePlus plugin;

    public CommandListener(MutePlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage().toLowerCase(Locale.ROOT);

        if (!plugin.getMainConfig().isFilterCommandEnabled()) {
            return;
        }

        if (plugin.getGlobalMuteManager().isMuted(player.getUniqueId()) && plugin.getMainConfig().matchGlobalCommand(command)) {
            MessageUtils.sendMessage(player.getUniqueId(), plugin.getMessageConfig().commandDenied());
            event.setCancelled(true);
            return;
        }

        Optional<CommandResult> optionalCommandResult = plugin.getMainConfig().getPlayerCommandFetcher().stream()
                .map(fetcher -> fetcher.getPlayer(command))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
        if (!optionalCommandResult.isPresent()) {
            return;
        }
        CommandResult result = optionalCommandResult.get();

        Optional<OfflinePlayer> optionalPlayer = result.getPlayer();
        if (!optionalPlayer.isPresent()) {
            return;
        }

        OfflinePlayer target = optionalPlayer.get();
        if (plugin.getGlobalMuteManager().isMuted(target.getUniqueId()) || plugin.getPlayerMuteManager().isMuted(player.getUniqueId(), target.getUniqueId())) {
            MessageUtils.sendMessage(player.getUniqueId(), plugin.getMessageConfig().commandDenied());
            event.setCancelled(true);
        }
    }
}
