package me.hsgamer.muteplus.config.converter;

import me.hsgamer.hscore.common.CollectionUtils;
import me.hsgamer.hscore.config.annotation.converter.Converter;
import me.hsgamer.muteplus.fetcher.impl.PatternCommandFetcher;
import me.hsgamer.muteplus.fetcher.impl.SimpleCommandFetcher;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandFetcherConverter implements Converter {
    private final Pattern regexFetcherPattern = Pattern.compile("pattern(\\(\\d+\\))?\\s*:\\s*(.+)\\s*");

    @Override
    public Object convert(Object raw) {
        if (raw == null) return null;
        return CollectionUtils.createStringListFromObject(raw)
                .stream()
                .flatMap(string -> {
                    Matcher matcher = regexFetcherPattern.matcher(string);
                    if (matcher.matches()) {
                        String patternString = matcher.group(2);
                        String targetGroup = matcher.group(1);
                        if (targetGroup == null || targetGroup.isEmpty()) {
                            targetGroup = "1";
                        }

                        Pattern pattern;
                        try {
                            pattern = Pattern.compile(patternString);
                        } catch (Exception e) {
                            return Stream.empty();
                        }

                        try {
                            int parsedTargetGroup = Integer.parseInt(targetGroup);
                            return Stream.of(new PatternCommandFetcher(pattern, parsedTargetGroup));
                        } catch (Exception e) {
                            return Stream.empty();
                        }
                    } else {
                        return Stream.of(new SimpleCommandFetcher(string));
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public Object convertToRaw(Object value) {
        if (value instanceof List) {
            List<?> list = (List<?>) value;
            return list.stream()
                    .flatMap(fetcher -> {
                        if (fetcher instanceof PatternCommandFetcher) {
                            PatternCommandFetcher patternCommandFetcher = (PatternCommandFetcher) fetcher;
                            return Stream.of("pattern(" + patternCommandFetcher.getTargetGroup() + "): " + patternCommandFetcher.getPatternString());
                        } else if (fetcher instanceof SimpleCommandFetcher) {
                            SimpleCommandFetcher simpleCommandFetcher = (SimpleCommandFetcher) fetcher;
                            return Stream.of(simpleCommandFetcher.getCommand());
                        }
                        return Stream.empty();
                    })
                    .collect(Collectors.toList());
        }
        return null;
    }
}
