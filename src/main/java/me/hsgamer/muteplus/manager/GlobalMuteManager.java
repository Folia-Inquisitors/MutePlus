package me.hsgamer.muteplus.manager;

import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.common.CollectionUtils;
import me.hsgamer.hscore.config.PathString;
import me.hsgamer.muteplus.MutePlus;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class GlobalMuteManager {
    private static final PathString GLOBAL_MUTE_PATH = new PathString("global-mute");
    private final BukkitConfig config;
    private final Map<UUID, Boolean> muteMap = new ConcurrentHashMap<>();

    public GlobalMuteManager(MutePlus plugin) {
        this.config = new BukkitConfig(plugin, "global.yml");
    }

    public void setMute(UUID target, boolean mute) {
        muteMap.put(target, mute);
    }

    public boolean isMuted(UUID target) {
        return muteMap.getOrDefault(target, false);
    }

    public void setup() {
        config.setup();
        muteMap.clear();

        List<String> list = CollectionUtils.createStringListFromObject(config.getNormalized(GLOBAL_MUTE_PATH), true);
        list.forEach(s -> muteMap.put(UUID.fromString(s), true));
    }

    public void save() {
        config.clear();

        List<String> list = muteMap.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(entry -> entry.getKey().toString())
                .collect(Collectors.toList());
        config.set(GLOBAL_MUTE_PATH, list);

        config.save();
    }
}
