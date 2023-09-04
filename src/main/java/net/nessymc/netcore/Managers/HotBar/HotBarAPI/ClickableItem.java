package net.nessymc.netcore.Managers.HotBar.HotBarAPI;

import lombok.Data;
import org.bukkit.inventory.ItemStack;

@Data
public class ClickableItem {

    private ClickHandler clickHandler;
    private ItemStack itemStack;
    private boolean droppable;
    private boolean moveable;
    private boolean placeable;

    public ClickableItem(ClickHandler clickHandler, ItemStack itemStack, boolean droppable, boolean moveable) {
        this.clickHandler = clickHandler;
        this.itemStack = itemStack;
        this.droppable = droppable;
        this.moveable = moveable;
        this.placeable = false; // Valor predeterminado para 'placeable'
    }
}
