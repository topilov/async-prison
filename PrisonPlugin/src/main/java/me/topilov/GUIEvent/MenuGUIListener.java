package me.topilov.GUIEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MenuGUIListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (e.getInventory().getTitle().equalsIgnoreCase("§7Меню")) {

            if (clickedItem == null) return;
            if (!clickedItem.hasItemMeta()) return;

            /*
                Прокачки
            */
            if (clickedItem.getItemMeta().getDisplayName().contains("§6Прокачки")) {
                player.sendMessage("§cВ разработке");
                e.setCancelled(true);
            }

            /*
                Шахты
            */
            if (clickedItem.getItemMeta().getDisplayName().contains("§6Шахты")) {
                player.chat("/mines");
            }

            /*
                Магазин предметов
            */
            if (clickedItem.getItemMeta().getDisplayName().contains("§6Магазин предметов")) {
                player.chat("/shop");
            }

            /*
                Поднять уровень
            */
            if (clickedItem.getItemMeta().getDisplayName().contains("§6Поднять уровень")) {
                player.sendMessage("§cВ разработке");
                e.setCancelled(true);
            }

            /*
                Продать блоки
            */
            if (clickedItem.getItemMeta().getDisplayName().contains("§6Продать блоки")) {
                player.sendMessage("§cВ разработке");
                e.setCancelled(true);
            }

            /*
                Пвп Арена
            */
            if (clickedItem.getItemMeta().getDisplayName().contains("§6Пвп арена")) {
                player.sendMessage("§cВ разработке");
                e.setCancelled(true);
            }

            /*
                Боссы и данжи
            */
            if (clickedItem.getItemMeta().getDisplayName().contains("§6Боссы и данжи")) {
                player.sendMessage("§cВ разработке");
                e.setCancelled(true);
            }

            /*
                Коллекция
            */
            if (clickedItem.getItemMeta().getDisplayName().contains("§6Коллекция")) {
                player.sendMessage("§cВ разработке");
                e.setCancelled(true);
            }

            /*
                Питомцы
            */
            if (clickedItem.getItemMeta().getDisplayName().contains("§6Питомцы")) {
                player.sendMessage("§cВ разработке");
                e.setCancelled(true);
            }

            /*
                Достижения
            */
            if (clickedItem.getItemMeta().getDisplayName().contains("§6Достижения")) {
                player.sendMessage("§cВ разработке");
                e.setCancelled(true);
            }

            /*
                AsyncPass
            */
            if (clickedItem.getItemMeta().getDisplayName().contains("§6AsyncPass")) {
                player.sendMessage("§cВ разработке");
                e.setCancelled(true);
            }
        }
    }
}
