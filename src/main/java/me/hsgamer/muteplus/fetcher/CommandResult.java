package me.hsgamer.muteplus.fetcher;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Optional;

public class CommandResult {
    private final String playerName;

    public CommandResult(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Optional<OfflinePlayer> getPlayer() {
        if (playerName.isEmpty()) {
            return Optional.empty();
        }

        //noinspection deprecation
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
        if (player.hasPlayedBefore()) {
            return Optional.of(player);
        } else {
            return Optional.empty();
        }
    }
}
