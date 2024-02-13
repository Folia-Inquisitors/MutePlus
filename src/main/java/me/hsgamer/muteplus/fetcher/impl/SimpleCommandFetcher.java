package me.hsgamer.muteplus.fetcher.impl;

import me.hsgamer.muteplus.fetcher.CommandFetcher;
import me.hsgamer.muteplus.fetcher.CommandResult;

import java.util.Optional;

public class SimpleCommandFetcher implements CommandFetcher {
    private final String command;

    public SimpleCommandFetcher(String command) {
        this.command = command;
    }

    @Override
    public Optional<CommandResult> getPlayer(String command) {
        if (!command.startsWith(this.command)) return Optional.empty();

        String remaining = command.substring(this.command.length()).trim();
        int spaceIndex = remaining.indexOf(' ');
        String playerName = spaceIndex == -1 ? remaining : remaining.substring(0, spaceIndex);

        return Optional.of(new CommandResult(playerName));
    }

    public String getCommand() {
        return command;
    }
}
