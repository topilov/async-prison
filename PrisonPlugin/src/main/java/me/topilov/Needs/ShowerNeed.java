package me.topilov.Needs;

import me.topilov.App;
import me.topilov.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

public class ShowerNeed implements Listener {

    ConfigManager manager = App.getInstance().manager;
    FileConfiguration messages = manager.getMessagesConfig();

    @EventHandler
    public void onWater(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (e.getPlayer().getLocation().getBlock().getType() == Material.WATER) {
            Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (player.getLocation().getBlock().getType() == Material.WATER) {
                        if (player.hasPotionEffect(PotionEffectType.CONFUSION)) {
                            player.removePotionEffect(PotionEffectType.CONFUSION);
                            player.sendMessage("" + messages.getString("messages.do_wash"));
                        }
                    }
                }
            }, 60);
        }
    }
}
