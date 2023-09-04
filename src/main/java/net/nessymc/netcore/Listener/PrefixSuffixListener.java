package net.nessymc.netcore.Listener;

import net.nessymc.netcore.Managers.Visual.TabList.PrefixSuffixManager;
import net.nessymc.netcore.NetCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PrefixSuffixListener implements Listener {

    private final NetCore plugin;

    public PrefixSuffixListener(NetCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FileConfiguration config = plugin.getConfig();

        Player player = event.getPlayer();
        PrefixSuffixManager.updateTab(player, config);
    }
}
