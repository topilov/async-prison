package me.topilov.Commands;

import me.topilov.App;
import me.topilov.DataBase.SQLGetter;
import me.topilov.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MineCMD implements CommandExecutor {

    ConfigManager manager = App.getInstance().manager;
    SQLGetter data = App.getInstance().data;
    FileConfiguration messages = manager.getMessagesConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(cmd.getName().equalsIgnoreCase("mine")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("" + messages.getString("messages.console_use_command"));
                return true;
            }
            if (args.length == 0) return true;
            Player player = (Player) sender;

            /*
                Перебираются числа с 1 до 36 (номера шахт на 1 странице),
                далее сверяется в <arg> команды /mine <arg>
                и если <arg> соответствует уровню требуемому для шахты
                и уровень игрока соответствует требуемому уровню
            */
            for (int i = 1; i <= 36; i++) {
                if (args[0].equalsIgnoreCase(String.valueOf(i))) {

                    FileConfiguration file = manager.getMinesConfig();

                    file.getConfigurationSection("mines").getKeys(false).forEach(mine -> {

                        int level = file.getInt("mines." + mine + ".level");
                        Location location = (Location) file.get("mines." + mine + ".location");

                        if (args[0].equalsIgnoreCase(String.valueOf(level))) {

                            if (data.getLevel(player.getUniqueId()) >= level) {

                                player.sendTitle(messages.getString("messages.teleport_mine_title"), messages.getString("messages.teleport_mine_subtitle") + level, 20, 40, 20);
                                player.teleport(location);

                            } else {
                                player.sendMessage("" + messages.getString("messages.not_enough_level"));

                            }
                        }
                    });
                }
            }
        }
        return true;
    }
}
