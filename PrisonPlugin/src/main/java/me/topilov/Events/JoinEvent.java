package me.topilov.Events;

import me.topilov.App;
import me.topilov.DataBase.SQLGetter;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JoinEvent implements Listener {

    SQLGetter data = App.getInstance().data;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        createTables(player);
        addBoosters(player);

        if (!player.hasPlayedBefore()) {
            addMainItems(player);
        }

    }

    private void createTables(Player player) {
        data.createPlayer(player);
        data.createTools(player);
        data.createLocalBoosters(player);
        data.createThanks(player);
    }

    private void addBoosters(Player player) {
        double globalMoneyBooster = data.getGlobalMoneyBooster(App.getInstance().getName());
        double globalBlocksBooster = data.getGlobalBlocksBooster(App.getInstance().getName());

        data.addMoneyBooster(player.getUniqueId(), globalMoneyBooster);
        data.addBlocksBooster(player.getUniqueId(), globalBlocksBooster);
    }

    private void addMainItems(Player player) {
        ItemStack itemStack = new ItemStack(Material.WOODEN_AXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§bДеревянный топор");
        itemMeta.setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);
        player.getInventory().addItem(itemStack);

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compass_itemMeta = compass.getItemMeta();
        compass_itemMeta.setDisplayName("§bМеню");
        compass.setItemMeta(compass_itemMeta);
        player.getInventory().setItem(8, compass);
    }
}
