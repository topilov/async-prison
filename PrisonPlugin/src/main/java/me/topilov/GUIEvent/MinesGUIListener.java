package me.topilov.GUIEvent;

import me.topilov.App;
import me.topilov.utils.MinesManager;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MinesGUIListener implements Listener {

    MinesManager manager = App.getInstance().manager;

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (e.getInventory().getTitle().equalsIgnoreCase("§7Шахты 0")) {

            FileConfiguration file = manager.getConfig();
            file.getConfigurationSection("mines1").getKeys(false).forEach(mine -> {
                String name = file.getString("mines1." + mine + ".name");
                int level = file.getInt("mines1." + mine + ".level");
            /*
                Проверка на имя предмета, далее подбирается из конфига к этому имени требуемый уровень
            */
                if (clickedItem.getItemMeta().getDisplayName().equals(name)) {
                    player.chat("/mine " + level);
                    e.setCancelled(true);
                }

            });
        }

        if (e.getInventory().getTitle().equalsIgnoreCase("§7Шахты 1")) {

            FileConfiguration file = manager.getConfig();
            file.getConfigurationSection("mines2").getKeys(false).forEach(mine -> {
                String name = file.getString("mines2." + mine + ".name");
                int level = file.getInt("mines2." + mine + ".level");
            /*
                Проверка на имя предмета, далее подбирается из конфига к этому имени требуемый уровень
            */
                if (clickedItem.getItemMeta().getDisplayName().equals(name)) {
                    player.chat("/mine " + level);
                    e.setCancelled(true);
                }

            });
        }

        /*
            Проверка находится ли игрок
            в меню "Шахты", чтобы правильно
            использовать вспомогательные предметы
            в инвентаре
        */
        if (e.getInventory().getTitle().contains("Шахты")) {

        /*
            Следующая страница
        */
            if (clickedItem.getType() == Material.ARROW && clickedItem.getItemMeta().getDisplayName().contains("§6Следующая страница")) {
                if (e.getInventory().getTitle().contains("§7Шахты 0")) {
                    player.chat("/mines 2");
                }
                if (e.getInventory().getTitle().contains("§7Шахты 1")) {
                    player.sendMessage("§cСледующей страницы пока что нет");
                    e.setCancelled(true);
                }
            }

        /*
            Предыдущая страница
        */
            if (clickedItem.getType() == Material.ARROW && clickedItem.getItemMeta().getDisplayName().contains("§6Предыдущая страница")) {
                if (e.getInventory().getTitle().contains("§7Шахты 1")) {
                    player.chat("/mines");
                }
            }

        /*
            Назад
        */
            if (clickedItem.getItemMeta().getDisplayName().contains("§eНазад")) {
                player.chat("/menu");
                e.setCancelled(true);
            }

        /*
            Заполнители
        */
            if (clickedItem.getType() == Material.BLACK_STAINED_GLASS_PANE) {
                e.setCancelled(true);
            }
        }
    }
}
