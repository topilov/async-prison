package me.topilov.Needs;

import me.topilov.App;
import me.topilov.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class SchedulerNeeds implements Listener {

    ConfigManager manager = App.getInstance().manager;
    FileConfiguration messages = manager.getMessagesConfig();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        /*
            Потребность: туалет
        */
        Bukkit.getScheduler().runTaskTimer(App.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 3, true, false));
                player.sendTitle(messages.getString("messages.want_toilet_title"), messages.getString("messages.want_toilet_subtitle"));
            }
        }, 12000, 12000);

        /*
            Потребность: душ
        */
        Bukkit.getScheduler().runTaskTimer(App.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, 10, true, false));
                player.sendTitle(messages.getString("messages.want_shower_title"), messages.getString("messages.want_shower_subtitle"));
            }
        }, 16000, 16000);

        /*
            Потребность: спать
        */
        Bukkit.getScheduler().runTaskTimer(App.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 10, true, false));
                player.sendTitle(messages.getString("messages.want_sleep_title"), messages.getString("messages.want_sleep_subtitle"));
            }
        }, 24000, 24000);
    }
}
