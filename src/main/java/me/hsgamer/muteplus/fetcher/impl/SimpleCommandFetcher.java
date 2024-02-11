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

        String playerName = "";
        String[] args = command.split(" ");
        if (args.length >= 2) {
            playerName = args[1];
        }

        return Optional.of(new CommandResult(playerName));
    }

    public String getCommand() {
        return command;
    }
}
