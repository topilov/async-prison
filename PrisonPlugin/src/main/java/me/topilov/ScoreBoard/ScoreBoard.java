package me.topilov.ScoreBoard;

import me.clip.placeholderapi.PlaceholderAPI;
import me.topilov.App;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.*;

public class ScoreBoard implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        createBoard(player);
        startReloadingScoreBoard(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        stopReloadingScoreBoard();
    }

    public void createBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("prison-1", "dummy", "§b      Prison     ");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        String level_placeHolder =  " §fУровень: §a%mysql_level%";
        String balance_placeHolder =  " §fБаланс: §a%vault_eco_balance_formatted%$";
        String blocks_placeHolder =  " §fДобыто блоков: §a%mysql_blocks%";
        String kills_placeHolder =  " §fУбийства: §a%mysql_kills%";
        String online_placeHolder =  " §fОнлайн режима: §a%server_online%";

        Score score = obj.getScore("           ");
        Score score1 = obj.getScore(PlaceholderAPI.setPlaceholders(player, level_placeHolder));
        Score score2 = obj.getScore(PlaceholderAPI.setPlaceholders(player, balance_placeHolder));
        Score score3 = obj.getScore(PlaceholderAPI.setPlaceholders(player, blocks_placeHolder));
        Score score4 = obj.getScore(PlaceholderAPI.setPlaceholders(player, kills_placeHolder));
        Score score5 = obj.getScore("        ");
        Score score6 = obj.getScore(PlaceholderAPI.setPlaceholders(player, online_placeHolder));
        Score score7 = obj.getScore("      ");


        score.setScore(7);
        score1.setScore(6);
        score2.setScore(5);
        score3.setScore(4);
        score4.setScore(3);
        score5.setScore(2);
        score6.setScore(1);
        score7.setScore(0);
        player.setScoreboard(board);
    }

    public int taskID;

    public void startReloadingScoreBoard(Player player) {
        taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(App.getPlugin(App.class), new Runnable() {
            @Override
            public void run() {
                createBoard(player);
            }
        }, 5, 5);
    }

    public void stopReloadingScoreBoard() {
        Bukkit.getServer().getScheduler().cancelTask(taskID);
    }
}
