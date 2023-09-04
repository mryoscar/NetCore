package net.nessymc.netcore.Listener;

import net.nessymc.netcore.NetCore;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;

public class VoidListener implements Listener {


    private final NetCore plugin;

    public VoidListener(NetCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerVoid(EntityDamageEvent listener){
        FileConfiguration config = plugin.getConfig();
        Player player = (Player) listener.getEntity();
        List<String> worlds = config.getStringList("ServerProtect.void-death");
        String world = player.getWorld().getName();
        if(!config.getBoolean("ServerProtect.enable")){
            for (String s : worlds) {
                if (s.equals(world)) {
                    if (listener.getEntity() instanceof Player && listener.getCause() == EntityDamageEvent.DamageCause.VOID) {
                        Location location = plugin.getConfig().getLocation("Spawn");
                        listener.setCancelled(true);
                        if(location != null){
                            player.teleport(location);
                            return;
                        }
                    }
                }
            }
        }
    }
}
