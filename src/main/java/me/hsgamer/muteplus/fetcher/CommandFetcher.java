package me.hsgamer.muteplus.fetcher;

import java.util.Optional;

public interface CommandFetcher {
    Optional<CommandResult> getPlayer(String command);
}
