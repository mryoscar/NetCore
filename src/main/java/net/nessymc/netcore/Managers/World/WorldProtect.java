package net.nessymc.netcore.Managers.World;

import net.nessymc.netcore.NetCore;
import net.nessymc.netcore.Utils.MessageFormatter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.List;

public class WorldProtect implements Listener {

    private final NetCore plugin;

    public WorldProtect(NetCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void BreakBlockEvent(BlockBreakEvent event){
        FileConfiguration config = plugin.getConfig();
        FileConfiguration messages = plugin.getMessages();
        Player player = event.getPlayer();
        List<String> worlds = config.getStringList("ServerProtect.block-break");
        String world = player.getWorld().getName();
        if(!player.hasPermission("netcore.protect.bypass")){
            if(!config.getString("ServerProtect.enable").equals(true)){
                for(int i=0; i<worlds.size();i++){
                    if(worlds.get(i).equals(world)){
                        event.setCancelled(true);
                        player.sendMessage(MessageFormatter.format(messages.getString("Messages.deny-message")));
                        return;
                    }
                }
            }
        }
    }

    @EventHandler
    public void PlaceBlockEvent(BlockPlaceEvent event){
        FileConfiguration messages = plugin.getMessages();
        FileConfiguration config = plugin.getConfig();
        Player player = event.getPlayer();
        List<String> worlds = config.getStringList("ServerProtect.block-place");
        String world = player.getWorld().getName();
        if(!player.hasPermission("netcore.protect.bypass")){
            if(!config.getString("ServerProtect.enable").equals(true)){
                for(int i=0; i<worlds.size();i++){
                    if(worlds.get(i).equals(world)){
                        event.setCancelled(true);
                        player.sendMessage(MessageFormatter.format(messages.getString("Messages.deny-message")));
                        return;
                    }
                }
            }
        }
    }

    @EventHandler
    public void ItemDrop(PlayerDropItemEvent event){
        FileConfiguration config = plugin.getConfig();
        Player player = event.getPlayer();
        List<String> worlds = config.getStringList("ServerProtect.drop-item");
        String world = player.getWorld().getName();
        if(!player.hasPermission("netcore.protect.bypass")){
            if(!config.getString("ServerProtect.enable").equals(true)){
                for(int i=0; i<worlds.size();i++){
                    if(worlds.get(i).equals(world)){
                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }

    @EventHandler
    public void Invincible(EntityDamageEvent event){
        FileConfiguration config = plugin.getConfig();
        List<String> worlds = config.getStringList("ServerProtect.pvp");
        String world = event.getEntity().getWorld().getName();
        if(!config.getString("ServerProtect.enable").equals(true)){
            for(int i=0; i<worlds.size();i++){
                if(worlds.get(i).equals(world)){
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void HungerEvent(FoodLevelChangeEvent event){
        FileConfiguration config = plugin.getConfig();
        Player player = (Player) event.getEntity();
        List<String> worlds = config.getStringList("ServerProtect.hunger");
        String world = player.getWorld().getName();
        if(!config.getString("ServerProtect.enable").equals(true)){
            for(int i=0; i<worlds.size();i++){
                if(worlds.get(i).equals(world)){
                    player.setFoodLevel(20);
                    player.setSaturation(20);
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void MobSpawning(CreatureSpawnEvent event){
        EntityType entityType = event.getEntityType();
        FileConfiguration config = plugin.getConfig();
        List<String> worlds = config.getStringList("ServerProtect.mob-spawning");
        String world = event.getEntity().getWorld().getName();
        if(!config.getString("ServerProtect.enable").equals(true)){
            for(int i=0; i<worlds.size();i++){
                if(worlds.get(i).equals(world)){
                    if (entityType.isSpawnable()) {
                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }

    @EventHandler
    public void Weather(WeatherChangeEvent event){
        FileConfiguration config = plugin.getConfig();
        List<String> worlds = config.getStringList("ServerProtect.weather");
        String world = event.getWorld().getName();
        if(!config.getString("ServerProtect.enable").equals(true)){
            for(int i=0; i<worlds.size();i++){
                if(worlds.get(i).equals(world)){
                    if (event.toWeatherState()) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void LeafDecay(LeavesDecayEvent event){
        FileConfiguration config = plugin.getConfig();
        List<String> worlds = config.getStringList("ServerProtect.leaf-decay");
        String world = event.getBlock().getWorld().getName();
        if(!config.getString("ServerProtect.enable").equals(true)){
            for(int i=0; i<worlds.size();i++){
                if(worlds.get(i).equals(world)){
                    event.setCancelled(true);
                }
            }
        }
    }
}
