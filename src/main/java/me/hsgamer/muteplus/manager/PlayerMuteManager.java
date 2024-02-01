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

public class PlayerMuteManager {
    private final BukkitConfig config;
    private final Map<UUID, Map<UUID, Boolean>> targetMuteMap = new ConcurrentHashMap<>();

    public PlayerMuteManager(MutePlus plugin) {
        this.config = new BukkitConfig(plugin, "player.yml");
    }

    public void setMute(UUID target, UUID muter, boolean mute) {
        targetMuteMap.computeIfAbsent(target, uuid -> new ConcurrentHashMap<>()).put(muter, mute);
    }

    public boolean isMuted(UUID target, UUID muter) {
        return targetMuteMap.getOrDefault(target, new ConcurrentHashMap<>()).getOrDefault(muter, false);
    }

    public void setup() {
        config.setup();
        targetMuteMap.clear();

        config.getNormalizedValues(false).forEach((k, v) -> {
            List<String> list = CollectionUtils.createStringListFromObject(v, true);
            UUID target = UUID.fromString(k.getLastPath());
            Map<UUID, Boolean> map = new ConcurrentHashMap<>();
            list.forEach(s -> map.put(UUID.fromString(s), true));
            targetMuteMap.put(target, map);
        });
    }

    public void save() {
        config.clear();

        targetMuteMap.forEach((target, muteMap) -> {
            List<String> list = muteMap.entrySet().stream()
                    .filter(Map.Entry::getValue)
                    .map(entry -> entry.getKey().toString())
                    .collect(Collectors.toList());
            config.set(new PathString(target.toString()), list);
        });

        config.save();
    }
}
