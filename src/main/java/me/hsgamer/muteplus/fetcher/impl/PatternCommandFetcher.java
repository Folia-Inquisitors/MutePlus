package me.hsgamer.muteplus.fetcher.impl;

import me.hsgamer.muteplus.fetcher.CommandFetcher;
import me.hsgamer.muteplus.fetcher.CommandResult;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternCommandFetcher implements CommandFetcher {
    private final Pattern pattern;
    private final int targetGroup;

    public PatternCommandFetcher(Pattern pattern, int targetGroup) {
        this.pattern = pattern;
        this.targetGroup = targetGroup;
    }

    @Override
    public Optional<CommandResult> getPlayer(String command) {
        Matcher matcher = pattern.matcher(command);
        if (!matcher.matches()) {
            return Optional.empty();
        }

        String player = "";
        try {
            player = matcher.group(targetGroup);
            player = player == null ? "" : player;
        } catch (Exception ignored) {
            // IGNORED
        }

        return Optional.of(new CommandResult(player));
    }

    public Pattern getPattern() {
        return pattern;
    }

    public String getPatternString() {
        return pattern.pattern();
    }

    public int getTargetGroup() {
        return targetGroup;
    }
}
