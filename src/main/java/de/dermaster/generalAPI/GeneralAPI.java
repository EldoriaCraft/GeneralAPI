package de.dermaster.generalAPI;

import de.dermaster.generalAPI.handler.InventoryHandler;
import fr.skytasul.glowingentities.GlowingBlocks;
import fr.skytasul.glowingentities.GlowingEntities;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GeneralAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new InventoryHandler(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public GlowingBlocks getGlowingBlocks(){
        return new GlowingBlocks(this);
    }

    public GlowingEntities getGlowingEntities(){
        return new GlowingEntities(this);
    }

}
