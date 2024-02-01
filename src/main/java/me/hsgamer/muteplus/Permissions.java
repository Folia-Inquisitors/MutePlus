package me.hsgamer.muteplus;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class Permissions {
    public static final Permission MUTE = new Permission("muteplus.mute", PermissionDefault.TRUE);
    public static final Permission UNMUTE = new Permission("muteplus.unmute", PermissionDefault.TRUE);
    public static final Permission GLOBAL_MUTE = new Permission("muteplus.globalmute", PermissionDefault.OP);
    public static final Permission GLOBAL_UNMUTE = new Permission("muteplus.globalunmute", PermissionDefault.OP);
}
