package me.topilov.BossBar;

import me.clip.placeholderapi.PlaceholderAPI;
import me.topilov.App;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;


public class BoostersBossBar implements Listener {

    public int taskID_1;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        create(e.getPlayer());
    }


    void create(Player player) {
        BossBar bar = Bukkit.getServer().createBossBar("", BarColor.YELLOW, BarStyle.SOLID);

        String money_PlaceHolder = "%mysql_booster_money%";
        String money = PlaceholderAPI.setPlaceholders(player, money_PlaceHolder);
        String blocks_PlaceHolder = "%mysql_booster_blocks%";
        String blocks = PlaceholderAPI.setPlaceholders(player, blocks_PlaceHolder);
        String title = BossBarUtils.getBossBarBoost(BossBarUtils.getSpacedInt(money), IconType.MONEY) + "   " + BossBarUtils.getBossBarBoost(BossBarUtils.getSpacedInt(blocks), IconType.BLOCKS);

        bar.setTitle(title);
        bar.addPlayer(player);
        reload(bar,player);
    }

    void reload(BossBar bar, Player player) {

        taskID_1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(App.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                String money_PlaceHolder = "%mysql_booster_money%";
                String money = PlaceholderAPI.setPlaceholders(player, money_PlaceHolder);
                String blocks_PlaceHolder = "%mysql_booster_blocks%";
                String blocks = PlaceholderAPI.setPlaceholders(player, blocks_PlaceHolder);
                String title = BossBarUtils.getBossBarBoost(BossBarUtils.getSpacedInt(String.valueOf(money)), IconType.MONEY) + "   " + BossBarUtils.getBossBarBoost(BossBarUtils.getSpacedInt(String.valueOf(blocks)), IconType.BLOCKS);
                bar.setTitle(title);

            }
        }, 20, 20);
    }
}