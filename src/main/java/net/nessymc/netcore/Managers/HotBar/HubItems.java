package net.nessymc.netcore.Managers.HotBar;

import net.nessymc.netcore.Managers.HotBar.HotBarAPI.ClickableItem;
import net.nessymc.netcore.Managers.HotBar.HotBarAPI.Hotbar;
import net.nessymc.netcore.Managers.HotBar.HotBarAPI.SuperHotBar;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HubItems extends Hotbar {

    private Player player;

    public HubItems(String id, SuperHotBar bulla, Player player) {
        super(id, bulla);
        initItems();
    }

    @Override
    public HashMap<Integer, ClickableItem> itemsToApply(Player player) {
        HashMap<Integer, ClickableItem> items = new HashMap<>();
        items.put(0, getCachedItem("modalidades"));
        items.put(8, getCachedItem("hubs"));
        return items;
    }
    
    private void initItems() {

        String modalidades = "/modalidades";
        addCachedItem("modalidades", new ClickableItem(
                player -> {
                    player.performCommand(modalidades);
                },
                new ItemBuilder(Material.COMPASS)
                        .title("&aModalidades &8| &7(Clic derecho)")
                        .lore(
                           "&7",
                           "&7 ¡Click para abrir el menú! ",
                           "&7"
                        )
                        .build(),
                false,
                false
            ));

        String profile = "/perfil";
        ItemStack playerHead = getPlayerHeadFromInventory(player);
        setHeadDisplayNameAndLore(playerHead, "&aModalidades &8| &7(Clic derecho)", Arrays.asList("&7 ¡Click para abrir el menú! "));
        addCachedItem("profile", new ClickableItem(
                player -> {
                    player.performCommand(profile);
                },
                playerHead,
                false,
                false
        ));

        String hubs = "/hubs";
        addCachedItem("hubs", new ClickableItem(
                player -> {
                    player.performCommand(hubs);
                },
                new ItemBuilder(Material.PAPER)
                        .title("&aVestibúlos &8| &7(Clic derecho)")
                        .lore(
                                "&7",
                                "&7 ¡Click para abrir el menú! ",
                                "&7"
                        )
                        .build(),
                false,
                false
        ));
        
    }

    private ItemStack getPlayerHeadFromInventory(Player player) {
        PlayerInventory inventory = player.getInventory();
        ItemStack[] contents = inventory.getContents();

        for (ItemStack item : contents) {
            if (item != null && item.getType() == Material.PLAYER_HEAD) {
                SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
                if (skullMeta.hasOwner()) {
                    return item;
                }
            }
        }

        ItemStack defaultHead = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        setHeadDisplayNameAndLore(defaultHead, "&aCabeza de Steve", Arrays.asList("&7Una cabeza de Steve en la hotbar"));
        return defaultHead;
    }

    private void setHeadDisplayNameAndLore(ItemStack itemStack, String displayName, List<String> lore) {
        if (itemStack.getItemMeta() instanceof SkullMeta) {
            SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
            skullMeta.setDisplayName(displayName);
            skullMeta.setLore(lore);
            itemStack.setItemMeta(skullMeta);
        }
    }
}