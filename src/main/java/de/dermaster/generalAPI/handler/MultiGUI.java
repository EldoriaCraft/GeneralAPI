package de.dermaster.generalAPI.handler;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public abstract class MultiGUI extends BaseGUI {

    ArrayList<ItemStack> itemStacks;
    int start;
    int end;
    int page;
    int max_pages;
    String title;
    int size;

    public MultiGUI(Player player, String title, int size, int start, int end) {
        super(player, title+" "+(1+1)+"/"+(1), size);
        itemStacks = new ArrayList<>();
        this.start = start;
        this.end = end;
        this.page = 0;
        this.max_pages = 0;
        this.title = title;
        this.size = size;
    }


    public void setPage() {
        if (getPage() > 0) getInventory().setItem(0, createItem(Material.ARROW, "§7Zurück"));
        if (getPage() != getMax_pages() && getMax_pages() != -1) getInventory().setItem(8, createItem(Material.ARROW, "§7Nächste"));

        for (int i = start; i < end; i++){
            if ((i-start)+page*(end-start)==itemStacks.size()) return;
            getInventory().setItem(i, itemStacks.get((i-start)+page*(end-start)));
        }
    }

    public void updateItems(ArrayList<ItemStack> itemStacks){
        this.itemStacks = itemStacks;
        this.max_pages = Math.ceilDiv(itemStacks.size(), (end-start))-1;
        updateInventory();
    }

    public void nextPage() {
        this.page++;
        updateInventory();
        setContents();
    }

    public void previousPage() {
        this.page--;
        updateInventory();
        setContents();
    }

    public void checkPage(InventoryClickEvent event){
        String displayname = PlainTextComponentSerializer.plainText().serialize(event.getCurrentItem().getItemMeta().displayName());
        if (displayname.equalsIgnoreCase("§7Nächste")) nextPage();
        else if (displayname.equalsIgnoreCase("§7Zurück")) previousPage();
    }

    private void updateInventory(){
        setInventory(Bukkit.createInventory(null, size, Component.text(title+" "+(page+1)+"/"+(max_pages+1))));
        open();
    }

    public int getPage() {
        return page;
    }

    public int getMax_pages() {
        return max_pages;
    }

    public ArrayList<ItemStack> getItemStacks() {
        return itemStacks;
    }
}
