package me.topilov.Commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.topilov.App;
import me.topilov.DataBase.SQLGetter;
import me.topilov.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class PrisonConsoleCMD implements CommandExecutor, Listener {

    SQLGetter data = App.getInstance().data;
    ConfigManager manager = App.getInstance().manager;
    FileConfiguration messages = manager.getMessagesConfig();

    public BossBar bar_money;
    public BossBar bar_money_2;
    public BossBar bar_blocks;
    public BossBar bar_blocks_2;
    public int taskID_1;
    public int taskID_2;
    public int taskID_3;
    public int taskID_4;
    public int taskID_5;
    public int taskID_6;
    public int taskID_7;
    public int taskID_8;

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        if(cmd.getName().equalsIgnoreCase("prison")) {
            //if(!(sender instanceof ConsoleCommandSender)) return true;

            double amount = Double.parseDouble(args[1]);
            int time = Integer.parseInt(args[2]);
            String playerName = args[3];
            Player argPlayer = App.getInstance().getServer().getPlayer(playerName);

            if (!sender.isOp()) {
                return true;
            }

            if (args[0].equalsIgnoreCase("global_money_booster")) {

                if (data.getActiveMoneyBooster(1) == 0) {
                    createMoneyBooster(amount, playerName);
                    return true;
                }
                if (data.getActiveMoneyBooster(2) == 0) {
                    createMoneyBooster_2(amount, playerName);
                    return true;
                }
                if (data.getActiveMoneyBooster(1) == 1 && data.getActiveMoneyBooster(2) == 1) {
                    sender.sendMessage("" + messages.getString("messages.max_global_money_boosters"));
                }
            }

            if (args[0].equalsIgnoreCase("global_blocks_booster")) {

                if (data.getActiveBlocksBooster(1) == 0) {
                    createBlocksBooster(amount, playerName);
                    return true;
                }
                if (data.getActiveBlocksBooster(2) == 0) {
                    createBlocksBooster_2(amount, playerName);
                    return true;
                }
                if (data.getActiveBlocksBooster(1) == 1 && data.getActiveBlocksBooster(2) == 1) {
                    sender.sendMessage("" + messages.getString("messages.max_global_blocks_boosters"));
                }
            }



            if (args[0].equalsIgnoreCase("local_money_booster")) {
                if (argPlayer == null) {
                    sender.sendMessage("" + messages.getString("messages.error_player_offline"));
                    return true;
                }
                data.addLocalMoneyBooster(argPlayer.getUniqueId(), amount);
                data.addMoneyBooster(argPlayer.getUniqueId(), amount);
                Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        data.removeLocalMoneyBooster(argPlayer.getUniqueId(), amount);
                        data.removeMoneyBooster(argPlayer.getUniqueId(), amount);
                    }
                }, time * 20L);
                sender.sendMessage("" + messages.getString("messages.success_give_booster"));
            }

            if (args[0].equalsIgnoreCase("local_blocks_booster")) {
                if (argPlayer == null) {
                    sender.sendMessage("" + messages.getString("messages.error_player_offline"));
                    return true;
                }
                data.addLocalBlocksBooster(argPlayer.getUniqueId(), amount);
                data.addBlocksBooster(argPlayer.getUniqueId(), amount);
                Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        data.removeLocalBlocksBooster(argPlayer.getUniqueId(), amount);
                        data.removeBlocksBooster(argPlayer.getUniqueId(), amount);
                    }
                }, time * 20L);
                sender.sendMessage("" + messages.getString("messages.success_give_booster"));
            }
        }
        return true;
    }

    public void createMoneyBooster(double amount, String playerName) {

        bar_money = Bukkit.getServer().createBossBar("", BarColor.GREEN, BarStyle.SOLID);
        bar_money.setVisible(false);

        data.addGlobalMoneyBooster(App.getInstance().getName(), amount);
        data.setActiveMoneyBooster(1, 1);
        data.setPlayerMoneyBooster(1, playerName);
        bar_money.setTitle( messages.getString("messages.title_bossBar_global_money_booster") + data.getPlayerMoneyBooster(1));
        bar_money.setVisible(true);

        for (Player player : Bukkit.getOnlinePlayers()) {
            data.addMoneyBooster(player.getUniqueId(), amount);
            data.setThxMoney_1(player.getUniqueId(), 0);
            bar_money.addPlayer(player);
        }

        taskID_1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(App.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                double progressMoney = data.getProgressMoneyBooster(1);
                bar_money.setProgress(progressMoney = progressMoney - 0.01);
                data.setProgressMoneyBooster(1, progressMoney);

                if (progressMoney <= 0) {
                    Bukkit.getServer().getScheduler().cancelTask(taskID_1);
                    Bukkit.getServer().getScheduler().cancelTask(taskID_2);
                    data.removeGlobalMoneyBooster(App.getLevel().getName(), amount);
                    data.setActiveMoneyBooster(1, 0);
                    data.setPlayerMoneyBooster(1, "NONE");
                    bar_money.setVisible(false);
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        data.removeMoneyBooster(player.getUniqueId(), amount);
                    }
                    data.setProgressMoneyBooster(1, 1);
                }
            }
        }, 720, 720);

        taskID_2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(App.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!bar_money.getPlayers().contains(player)) {
                        bar_money.addPlayer(player);
                    }
                }
            }
        }, 40, 40);
    }

    public void createMoneyBooster_2(double amount, String playerName) {

        bar_money_2 = Bukkit.getServer().createBossBar("", BarColor.GREEN, BarStyle.SOLID);
        bar_money_2.setVisible(false);

        data.addGlobalMoneyBooster(App.getInstance().getName(), amount);
        data.setActiveMoneyBooster(2, 1);
        data.setPlayerMoneyBooster(2, playerName);
        bar_money_2.setTitle( messages.getString("messages.title_bossBar_global_money_booster") + data.getPlayerMoneyBooster(2));
        bar_money_2.setVisible(true);

        for (Player player : Bukkit.getOnlinePlayers()) {
            data.addMoneyBooster(player.getUniqueId(), amount);
            data.setThxMoney_2(player.getUniqueId(), 0);
            bar_money_2.addPlayer(player);
        }

        taskID_3 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(App.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                double progressMoney = data.getProgressMoneyBooster(2);
                bar_money_2.setProgress(progressMoney = progressMoney - 0.01);
                data.setProgressMoneyBooster(2, progressMoney);

                if (progressMoney <= 0) {
                    Bukkit.getServer().getScheduler().cancelTask(taskID_3);
                    Bukkit.getServer().getScheduler().cancelTask(taskID_4);
                    data.removeGlobalMoneyBooster(App.getLevel().getName(), 1);
                    data.setActiveMoneyBooster(2, 0);
                    data.setPlayerMoneyBooster(2, "NONE");
                    bar_money_2.setVisible(false);
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        data.removeMoneyBooster(player.getUniqueId(), amount);
                    }
                    data.setProgressMoneyBooster(2, 1);
                }
            }
        }, 720, 720);

        taskID_4 = Bukkit.getScheduler().scheduleSyncRepeatingTask(App.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!bar_money_2.getPlayers().contains(player)) {
                        bar_money_2.addPlayer(player);
                    }
                }
            }
        }, 40, 40);
    }

    public void createBlocksBooster(double amount, String playerName) {

        bar_blocks = Bukkit.getServer().createBossBar("", BarColor.BLUE, BarStyle.SOLID);
        bar_blocks.setVisible(false);

        data.addGlobalBlocksBooster(App.getInstance().getName(), amount);
        data.setActiveBlocksBooster(1, 1);
        data.setPlayerBlocksBooster(1, playerName);
        bar_blocks.setTitle( messages.getString("messages.title_bossBar_global_blocks_booster") + data.getPlayerBlocksBooster(1));
        bar_blocks.setVisible(true);

        for (Player player : Bukkit.getOnlinePlayers()) {
            data.addBlocksBooster(player.getUniqueId(), amount);
            data.setThxBlocks_1(player.getUniqueId(), 0);
            bar_blocks.addPlayer(player);
        }

        taskID_5 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(App.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                double progressBlocks = data.getProgressBlocksBooster(1);
                bar_blocks.setProgress(progressBlocks = progressBlocks - 0.01);
                data.setProgressBlocksBooster(1, progressBlocks);

                if (progressBlocks <= 0) {
                    Bukkit.getServer().getScheduler().cancelTask(taskID_5);
                    Bukkit.getServer().getScheduler().cancelTask(taskID_6);
                    data.removeGlobalBlocksBooster(App.getLevel().getName(), amount);
                    data.setActiveBlocksBooster(1, 0);
                    data.setPlayerBlocksBooster(1, "NONE");
                    bar_blocks.setVisible(false);
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        data.removeBlocksBooster(player.getUniqueId(), amount);
                    }
                    data.setProgressBlocksBooster(1, 1);
                }
            }
        }, 720, 720);

        taskID_6 = Bukkit.getScheduler().scheduleSyncRepeatingTask(App.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!bar_blocks.getPlayers().contains(player)) {
                        bar_blocks.addPlayer(player);
                    }
                }
            }
        }, 40, 40);
    }

    public void createBlocksBooster_2(double amount, String playerName) {

        bar_blocks_2 = Bukkit.getServer().createBossBar("", BarColor.BLUE, BarStyle.SOLID);
        bar_blocks_2.setVisible(false);

        data.addGlobalBlocksBooster(App.getInstance().getName(), amount);
        data.setActiveBlocksBooster(2, 1);
        data.setPlayerBlocksBooster(2, playerName);
        bar_blocks_2.setTitle( messages.getString("messages.title_bossBar_global_blocks_booster") + data.getPlayerBlocksBooster(2));
        bar_blocks_2.setVisible(true);

        for (Player player : Bukkit.getOnlinePlayers()) {
            data.addBlocksBooster(player.getUniqueId(), amount);
            data.setThxBlocks_2(player.getUniqueId(), 0);
            bar_blocks_2.addPlayer(player);
        }

        taskID_7 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(App.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                double progressBlocks = data.getProgressBlocksBooster(2);
                bar_blocks_2.setProgress(progressBlocks = progressBlocks - 0.01);
                data.setProgressBlocksBooster(2, progressBlocks);

                if (progressBlocks <= 0) {
                    Bukkit.getServer().getScheduler().cancelTask(taskID_7);
                    Bukkit.getServer().getScheduler().cancelTask(taskID_8);
                    data.removeGlobalBlocksBooster(App.getLevel().getName(), amount);
                    data.setActiveBlocksBooster(2, 0);
                    data.setPlayerBlocksBooster(2, "NONE");
                    bar_blocks_2.setVisible(false);
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        data.removeBlocksBooster(player.getUniqueId(), amount);
                    }
                    data.setProgressBlocksBooster(2, 1);
                }
            }
        }, 720, 720);

        taskID_8 = Bukkit.getScheduler().scheduleSyncRepeatingTask(App.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!bar_blocks_2.getPlayers().contains(player)) {
                        bar_blocks_2.addPlayer(player);
                    }
                }
            }
        }, 40, 40);
    }
}