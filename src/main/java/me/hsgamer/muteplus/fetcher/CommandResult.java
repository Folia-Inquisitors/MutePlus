package me.hsgamer.muteplus.fetcher;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Optional;

public class CommandResult {
    private final boolean present;
    private final String playerName;

    public CommandResult(String playerName) {
        this.present = !playerName.isEmpty();
        this.playerName = playerName;
    }

    public boolean isPresent() {
        return present;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Optional<OfflinePlayer> getPlayer() {
        //noinspection deprecation
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
        if (player.hasPlayedBefore()) {
            return Optional.of(player);
        } else {
            return Optional.empty();
        }
    }
}
