package net.nessymc.netcore.Listener;

import net.nessymc.netcore.Managers.HotBar.HotBarAPI.SuperHotBar;
import net.nessymc.netcore.Managers.HotBar.HubItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class HotBarListener implements Listener {

    private SuperHotBar superHotBar;

    public HotBarListener(SuperHotBar superHotBar) {
        this.superHotBar = superHotBar;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        HubItems hubItems = new HubItems("Hotbar", superHotBar, player);
        superHotBar.addHotbar(hubItems);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        HubItems hubItems = new HubItems("Hotbar", superHotBar, player);
        superHotBar.removeHotbar(hubItems);
    }
}
