package me.topilov.Events;

import me.topilov.App;
import me.topilov.DataBase.SQLGetter;
import me.topilov.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class MineBreakEvent implements Listener {

    ConfigManager manager = App.getInstance().manager;
    SQLGetter data = App.getInstance().data;

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        if (e.isCancelled()) return;

        Player player = e.getPlayer();
        FileConfiguration file = manager.getBlocksConfig();
        FileConfiguration messages = manager.getMessagesConfig();

        manager.getBlocksConfig().getConfigurationSection("blocks").getKeys(false).forEach(key -> {
            int level = file.getInt("blocks." + key + ".level");
            Material block = Material.getMaterial(file.getString("blocks." + key + ".material").toUpperCase());

            if (e.getBlock().getType() == block) {

                if (data.getLevel(player.getUniqueId()) < level) {
                    player.sendMessage("" + messages.getString("messages.not_enough_level_for_mine"));
                    e.setDropItems(false);
                    return;
                }

                double boosterBlocks = data.getBlocksBooster(player.getUniqueId());
                e.setDropItems(false);
                data.addBlocks(player.getUniqueId(), (int) (boosterBlocks));
                player.getInventory().addItem(new ItemStack(block));
            }

        });
    }
}
