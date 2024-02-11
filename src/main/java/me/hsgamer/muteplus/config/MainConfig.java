package me.hsgamer.muteplus.config;

import me.hsgamer.hscore.config.annotation.ConfigPath;
import me.hsgamer.muteplus.config.converter.CommandFetcherConverter;
import me.hsgamer.muteplus.config.converter.StringListConverter;
import me.hsgamer.muteplus.fetcher.CommandFetcher;
import me.hsgamer.muteplus.fetcher.impl.SimpleCommandFetcher;

import java.util.Arrays;
import java.util.List;

public interface MainConfig {
    @ConfigPath({"filter-command", "enabled"})
    default boolean isFilterCommandEnabled() {
        return true;
    }

    @ConfigPath(value = {"filter-command", "player"}, converter = CommandFetcherConverter.class)
    default List<CommandFetcher> getPlayerCommandFetcher() {
        return Arrays.asList(
                new SimpleCommandFetcher("/tpa"),
                new SimpleCommandFetcher("/tpahere"),
                new SimpleCommandFetcher("/w"),
                new SimpleCommandFetcher("/whisper"),
                new SimpleCommandFetcher("/msg"),
                new SimpleCommandFetcher("/tell")
        );
    }

    @ConfigPath(value = {"filter-command", "global"}, converter = StringListConverter.class)
    default List<String> getGlobalCommand() {
        return Arrays.asList(
                "/r",
                "/reply"
        );
    }

    default boolean matchGlobalCommand(String command) {
        String[] split = command.split(" ");
        return getGlobalCommand().stream().anyMatch(s -> s.equalsIgnoreCase(split[0]));
    }
}
