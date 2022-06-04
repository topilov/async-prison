package me.topilov.GUIEvent;

import me.topilov.App;
import me.topilov.utils.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class MinesGUIListener implements Listener {

    ConfigManager manager = App.getInstance().manager;

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (e.getInventory().getTitle().contains("Шахты")) {
            if (clickedItem == null) return;
            if (!clickedItem.hasItemMeta()) return;

            if (e.getClickedInventory().getType() == InventoryType.PLAYER) {
                e.setCancelled(true);
            }

            if (clickedItem.getItemMeta().getDisplayName().contains("§cЗаблокировано")) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getItemMeta().getDisplayName().contains("§eНазад в меню")) {
                player.chat("/menu");
                e.setCancelled(true);
            }
        }

        if (e.getInventory().getTitle().equalsIgnoreCase("Шахты")) {

            FileConfiguration file = manager.getMinesConfig();
            file.getConfigurationSection("mines").getKeys(false).forEach(mine -> {
                String name = file.getString("mines." + mine + ".name");
                int level = file.getInt("mines." + mine + ".level");
            /*
                Проверка на имя предмета, далее подбирается из конфига к этому имени требуемый уровень
            */
                if (clickedItem.getItemMeta().getDisplayName().equals(name)) {
                    player.chat("/mine " + level);
                    e.setCancelled(true);
                }

            });
        }
    }
}
