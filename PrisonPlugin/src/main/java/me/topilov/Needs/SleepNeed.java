package me.topilov.Needs;

import me.topilov.App;
import me.topilov.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.potion.PotionEffectType;

public class SleepNeed implements Listener {

    ConfigManager manager = App.getInstance().manager;
    FileConfiguration messages = manager.getMessagesConfig();

    @EventHandler
    public void onSleep(PlayerBedEnterEvent e) {
        Player player = e.getPlayer();
            Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if (player.isSleeping()) {
                        if (player.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
                            player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
                            player.sendMessage("" + messages.getString("messages.do_sleep"));
                    }
                }
            }
       }, 100);
    }
}
