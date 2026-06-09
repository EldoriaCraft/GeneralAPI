package de.dermaster.generalAPI.handler;

import de.dermaster.generalAPI.service.ServiceProvider;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryHandler implements Listener {

    GUIManager guiManager = ServiceProvider.getService(GUIManager.class);

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        if (guiManager.hasOpenGUI(player)) guiManager.removeGUI(player);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (guiManager.hasOpenGUI(player) && event.getCurrentItem() != null && event.getCurrentItem().getItemMeta() != null) guiManager.getOpenGUI(player).handleClick(event);
    }

}
