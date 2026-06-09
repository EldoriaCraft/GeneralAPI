package de.dermaster.generalAPI.handler;

import de.dermaster.generalAPI.service.IServiceProvider;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GUIManager implements IServiceProvider {
    private final Map<UUID, BaseGUI> openGUIs = new HashMap<>();

    public void registerGUI(Player player, BaseGUI gui) {
        openGUIs.put(player.getUniqueId(), gui);
    }

    public BaseGUI getOpenGUI(Player player) {
        return openGUIs.get(player.getUniqueId());
    }

    public void removeGUI(Player player) {
        openGUIs.remove(player.getUniqueId());
    }

    public boolean hasOpenGUI(Player player) {
        return openGUIs.containsKey(player.getUniqueId());
    }
}