package me.hsgamer.muteplus;

import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.hscore.config.proxy.ConfigGenerator;
import me.hsgamer.muteplus.command.GlobalMuteCommand;
import me.hsgamer.muteplus.command.GlobalUnmuteCommand;
import me.hsgamer.muteplus.command.MuteCommand;
import me.hsgamer.muteplus.command.UnmuteCommand;
import me.hsgamer.muteplus.config.MainConfig;
import me.hsgamer.muteplus.config.MessageConfig;
import me.hsgamer.muteplus.hook.YATPAHook;
import me.hsgamer.muteplus.listener.ChatListener;
import me.hsgamer.muteplus.listener.CommandListener;
import me.hsgamer.muteplus.manager.GlobalMuteManager;
import me.hsgamer.muteplus.manager.PlayerMuteManager;

import java.util.Collections;
import java.util.List;

public final class MutePlus extends BasePlugin {
    private final GlobalMuteManager globalMuteManager = new GlobalMuteManager(this);
    private final PlayerMuteManager playerMuteManager = new PlayerMuteManager(this);
    private final MainConfig mainConfig = ConfigGenerator.newInstance(MainConfig.class, new BukkitConfig(this), true, true);
    private final MessageConfig messageConfig = ConfigGenerator.newInstance(MessageConfig.class, new BukkitConfig(this, "messages.yml"));

    @Override
    public void load() {
        MessageUtils.setPrefix(messageConfig::prefix);
    }

    @Override
    public void enable() {
        globalMuteManager.setup();
        playerMuteManager.setup();

        registerCommand(new MuteCommand(this));
        registerCommand(new UnmuteCommand(this));
        registerCommand(new GlobalMuteCommand(this));
        registerCommand(new GlobalUnmuteCommand(this));

        registerListener(new ChatListener(this));
        registerListener(new CommandListener(this));

        if (YATPAHook.isAvailable()) {
            registerListener(new YATPAHook(this));
        }
    }

    @Override
    public void disable() {
        globalMuteManager.save();
        playerMuteManager.save();
    }

    @Override
    protected List<Class<?>> getPermissionClasses() {
        return Collections.singletonList(Permissions.class);
    }

    public GlobalMuteManager getGlobalMuteManager() {
        return globalMuteManager;
    }

    public PlayerMuteManager getPlayerMuteManager() {
        return playerMuteManager;
    }

    public MainConfig getMainConfig() {
        return mainConfig;
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }
}
