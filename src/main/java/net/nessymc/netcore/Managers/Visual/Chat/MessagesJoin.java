package net.nessymc.netcore.Managers.Visual.Chat;

import me.clip.placeholderapi.PlaceholderAPI;
import net.nessymc.netcore.NetCore;
import net.nessymc.netcore.Utils.MessageFormatter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MessagesJoin implements Listener {

    private final NetCore plugin;

    public MessagesJoin(NetCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinVip(PlayerJoinEvent listener){
        FileConfiguration messages = plugin.getMessages();
        Player player = listener.getPlayer();
        if(player.hasPermission("netcore.vip")){
            String joinVip = MessageFormatter.format(messages.getString("Join.vip"));
            joinVip = PlaceholderAPI.setPlaceholders(player, joinVip);
            listener.setJoinMessage(joinVip);
        }else{
            listener.setJoinMessage(null);
        }
    }

    @EventHandler
    public void onJoinNessy(PlayerJoinEvent listener){
        FileConfiguration messages = plugin.getMessages();
        Player player = listener.getPlayer();
        if(player.hasPermission("netcore.nessy")){
            String joinNessy = MessageFormatter.format(messages.getString("Join.nessy"));
            joinNessy = PlaceholderAPI.setPlaceholders(player, joinNessy);
            listener.setJoinMessage(joinNessy);
        }else{
            listener.setJoinMessage(null);
        }
    }

    @EventHandler
    public void onJoinPoseidon(PlayerJoinEvent listener){
        FileConfiguration messages = plugin.getMessages();
        Player player = listener.getPlayer();
        if(player.hasPermission("netcore.poseidon")){
            String joinPoseidon = MessageFormatter.format(messages.getString("Join.poseidon"));
            joinPoseidon = PlaceholderAPI.setPlaceholders(player, joinPoseidon);
            listener.setJoinMessage(joinPoseidon);
        }else{
            listener.setJoinMessage(null);
        }
    }

    @EventHandler
    public void onJoinMedia(PlayerJoinEvent listener){
        FileConfiguration messages = plugin.getMessages();
        Player player = listener.getPlayer();
        if(player.hasPermission("netcore.media")){
            String joinMedia = MessageFormatter.format(messages.getString("Join.media"));
            joinMedia = PlaceholderAPI.setPlaceholders(player, joinMedia);
            listener.setJoinMessage(joinMedia);
        }else{
            listener.setJoinMessage(null);
        }
    }

    @EventHandler
    public void onJoinStaff(PlayerJoinEvent listener){
        FileConfiguration messages = plugin.getMessages();
        Player player = listener.getPlayer();
        if(player.hasPermission("netcore.staff")){
            String joinStaff = MessageFormatter.format(messages.getString("Join.staff"));
            joinStaff = PlaceholderAPI.setPlaceholders(player, joinStaff);
            listener.setJoinMessage(joinStaff);
        }else{
            listener.setJoinMessage(null);
        }
    }
}
