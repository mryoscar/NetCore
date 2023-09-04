package net.nessymc.netcore.Listener;

import net.nessymc.netcore.NetCore;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SpawnListener implements Listener {
    private final Set<UUID> playerFirstJoin = new HashSet<>();
    private final NetCore plugin;
    public SpawnListener(NetCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent listener){
        Player player = listener.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (playerFirstJoin.contains(playerUUID)) {
            if(player.hasPlayedBefore()){
                Location location = plugin.getConfig().getLocation("Spawn");
                if(location != null){
                    player.teleport(location);
                }
            }

        } else {

            playerFirstJoin.add(playerUUID);
            if (playerFirstJoin.contains(playerUUID)) {
                if(player.hasPlayedBefore()){
                    Location location = plugin.getConfig().getLocation("Spawn");
                    if(location != null){
                        player.teleport(location);
                    }
                }
            }
        }
    }
}
