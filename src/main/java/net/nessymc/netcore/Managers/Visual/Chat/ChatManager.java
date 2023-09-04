package net.nessymc.netcore.Managers.Visual.Chat;

import net.nessymc.netcore.NetCore;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ChatManager {

    private final NetCore plugin;

    public ChatManager(NetCore plugin) {
        this.plugin = plugin;
    }

    public String getChatFormat() {
        FileConfiguration config = plugin.getConfig();
        return config.getString("ChatFormat.format");
    }

}