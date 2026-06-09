package de.dermaster.generalAPI.handler;

import de.dermaster.generalAPI.service.ServiceProvider;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class BaseGUI {
    protected Inventory inventory;
    protected Player player;
    GUIManager guiManager = ServiceProvider.getService(GUIManager.class);

    public BaseGUI(Player player, String title, int size) {
        this.player = player;
        this.inventory = Bukkit.createInventory(null, size, Component.text(title));
    }

    public abstract void setContents();

    public void open() {
        setContents();
        if (guiManager.hasOpenGUI(player)) player.closeInventory(InventoryCloseEvent.Reason.OPEN_NEW);
        guiManager.registerGUI(player, this);
        player.openInventory(inventory);
    }

    public abstract void handleClick(InventoryClickEvent event);

    protected ItemStack createItem(Material material, String name) {
        return createItem(material, name, null, 1);
    }

    protected ItemStack createItem(Material material, String name, int amount) {
        return createItem(material, name, null, amount);
    }

    protected ItemStack createItem(Material material, String name, List<String> lore) {
        return createItem(material, name, lore, 1);
    }

    protected ItemStack createItem(Material material, String name, List<String> lore, int amount) {
        ItemStack item = new ItemStack(material);
        item.setAmount(amount);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.displayName(Component.text(name));
            if (lore != null) {
                meta.lore(lore.stream().map(Component::text).toList());
            }
            item.setItemMeta(meta);
        }
        return item;
    }

    protected void fillEmpty(Material material) {
        ItemStack filler = createItem(material, " ");
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, filler);
            }
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
    protected void setInventory(Inventory inventory){
        this.inventory = inventory;
    }
}