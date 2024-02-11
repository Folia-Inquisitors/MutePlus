package me.hsgamer.muteplus.config;

import me.hsgamer.hscore.config.annotation.ConfigPath;

public interface MessageConfig {
    @ConfigPath("prefix")
    default String prefix() {
        return "&8[&cMutePlus&8] &r";
    }

    @ConfigPath("global-mute")
    default String globalMute() {
        return "&cYou are muted globally";
    }

    @ConfigPath("success")
    default String success() {
        return "&aSuccess";
    }

    @ConfigPath("player-not-found")
    default String playerNotFound() {
        return "&cPlayer not found";
    }

    @ConfigPath("player-only")
    default String playerOnly() {
        return "&cYou must be a player to do this";
    }

    @ConfigPath("command-denied")
    default String commandDenied() {
        return "&cYou cannot use this command while muted";
    }

    @ConfigPath("yatpa.cancel")
    default String yatpaCancel() {
        return "&cYou cannot request teleport while muted";
    }
}
