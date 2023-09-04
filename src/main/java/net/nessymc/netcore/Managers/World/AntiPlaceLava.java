package net.nessymc.netcore.Managers.World;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class AntiPlaceLava implements Listener {

    @EventHandler
    public void placeLava(BlockPlaceEvent event){
        if (event.getBlockPlaced().getType() == Material.LAVA || event.getBlockPlaced().getType() == Material.LAVA_CAULDRON){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        String command = event.getCommand().toLowerCase();
        if (command.contains("/fill") && (command.contains("lava") || command.contains("minecraft:lava"))) {
            event.setCancelled(true);
        }
    }
}
