package me.topilov.GUIEvent;

import me.topilov.App;
import me.topilov.DataBase.SQLGetter;
import me.topilov.utils.ConfigManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class MenuGUIListener implements Listener {

    Economy economy = App.economy;
    ConfigManager manager = App.getInstance().manager;
    SQLGetter data = App.getInstance().data;
    FileConfiguration messages = manager.getMessagesConfig();

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (e.getInventory().getTitle().equalsIgnoreCase("Меню")) {

            if (clickedItem == null) return;
            if (!clickedItem.hasItemMeta()) return;

            if (e.getClickedInventory().getType() == InventoryType.PLAYER) {
                e.setCancelled(true);
            }

            /*
                Прокачки
            */
            if (clickedItem.getItemMeta().getDisplayName().contains("§6Донат")) {
                player.chat("/donate");
                e.setCancelled(true);
            }

            /*
                Поднять уровень
            */
            if (clickedItem.getItemMeta().getDisplayName().contains("§6Поднять уровень")) {
                upLevel(player);
                e.setCancelled(true);
            }

            /*
                Если нажать на "поднять уровень" при максимальном уровне
            */
            if (clickedItem.getItemMeta().getDisplayName().contains("§6Вы достигли максимального уровня")) {
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
            if (clickedItem.getItemMeta().getDisplayName().contains("§6Магазин")) {
                player.chat("/shop");
            }

            /*
                Боссы
            */
            if (clickedItem.getItemMeta().getDisplayName().contains("§6Боссы")) {
                e.setCancelled(true);
            }
        }
    }

    void upLevel(Player player) {
        FileConfiguration file = manager.getLevelConfig();

        manager.getLevelConfig().getConfigurationSection("level").getKeys(false).forEach(key -> {
            int money = file.getInt("level." + key + ".money");
            int blocks = file.getInt("level." + key + ".blocks");
            int playerLevel = data.getLevel(player.getUniqueId());
            int requiredLevel = file.getInt("level." + key + ".level");


            if (playerLevel == requiredLevel) {
                if (economy.getBalance(player) >= money && data.getBlocks(player.getUniqueId()) >= blocks) {
                    Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            economy.withdrawPlayer(player, money);
                            data.addLevel(player.getUniqueId(), 1);
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 20, 20);
                            player.closeInventory();
                            player.sendTitle(messages.getString("messages.level_up_title"), messages.getString("messages.level_up_subtitle"));
                        }
                    }, 1);
                } else {

                    if (economy.getBalance(player) < money && data.getBlocks(player.getUniqueId()) < blocks) {
                        player.sendMessage("" + messages.getString("messages.not_enough_money_and_blocks"));
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 20, 20);
                        return;
                    }
                    if (economy.getBalance(player) < money) {
                        player.sendMessage("" + messages.getString("messages.not_enough_money"));
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 20, 20);
                        return;
                    }
                    if (data.getBlocks(player.getUniqueId()) < blocks) {
                        player.sendMessage("" + messages.getString("messages.not_enough_blocks"));
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 20, 20);
                        return;
                    }
                }
            }
        });
    }
}
