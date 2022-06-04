package me.topilov.Commands;

import me.topilov.App;
import me.topilov.DataBase.SQLGetter;
import me.topilov.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class ThanksCMD implements CommandExecutor {

    SQLGetter data = App.getInstance().data;
    ConfigManager manager = App.getInstance().manager;
    FileConfiguration messages = manager.getMessagesConfig();

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        if(cmd.getName().equalsIgnoreCase("thanks")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.DARK_RED + "You cannot use this");
                return true;
            }

            Player player = (Player) sender;
            Player player_Money_1 = Bukkit.getServer().getPlayer(data.getPlayerMoneyBooster(1));
            Player player_Money_2 = Bukkit.getServer().getPlayer(data.getPlayerMoneyBooster(2));
            Player player_Blocks_1 = Bukkit.getServer().getPlayer(data.getPlayerBlocksBooster(1));
            Player player_Blocks_2 = Bukkit.getServer().getPlayer(data.getPlayerBlocksBooster(2));

            if (data.getThxMoney_1(player.getUniqueId()) == 0) {
                if (!data.getPlayerMoneyBooster(1).equalsIgnoreCase("NONE")) {

                    if (player_Money_1 == null) return true;
                    if (player.getName().equalsIgnoreCase(player_Money_1.getName())) return true;
                    if (data.getThxMoney_1(player.getUniqueId()) == 1) return true; // if true

                    player.sendMessage(messages.getString("messages.thanks_to_player") + player_Money_1.getName());
                    player_Money_1.sendMessage(messages.getString("messages.thanks_from_player") + player.getName());
                    data.setThxMoney_1(player.getUniqueId(), 1);

                }
            }

            if (data.getThxMoney_2(player.getUniqueId()) == 0) {
                if (!data.getPlayerMoneyBooster(2).equalsIgnoreCase("NONE")) {

                    if (player_Money_2 == null) return true;
                    if (player.getName().equalsIgnoreCase(player_Money_2.getName())) return true;
                    if (data.getThxMoney_2(player.getUniqueId()) == 1) return true; // if true

                    player.sendMessage("§bВы поблагодарили игрока §f" + player_Money_2.getName());
                    player_Money_2.sendMessage("§bВас поблагодарил игрок §f" + player.getName());
                    data.setThxMoney_2(player.getUniqueId(), 1);

                }
            }

            if (data.getThxBlocks_1(player.getUniqueId()) == 0) {
                if (!data.getPlayerBlocksBooster(1).equalsIgnoreCase("NONE")) {

                    if (player_Blocks_1 == null) return true;
                    if (player.getName().equalsIgnoreCase(player_Blocks_1.getName())) return true;
                    if (data.getThxBlocks_1(player.getUniqueId()) == 1) return true; // if true

                    player.sendMessage(messages.getString("messages.thanks_to_player") + player_Blocks_1.getName());
                    player_Blocks_1.sendMessage(messages.getString("messages.thanks_from_player") + player.getName());
                    data.setThxBlocks_1(player.getUniqueId(), 1);

                }
            }

            if (data.getThxBlocks_2(player.getUniqueId()) == 0) {
                if (!data.getPlayerBlocksBooster(2).equalsIgnoreCase("NONE")) {

                    if (player_Blocks_2 == null) return true;
                    if (player.getName().equalsIgnoreCase(player_Blocks_2.getName())) return true;
                    if (data.getThxBlocks_2(player.getUniqueId()) == 1) return true; // if true

                    player.sendMessage(messages.getString("messages.thanks_to_player") + player_Blocks_2.getName());
                    player_Blocks_2.sendMessage(messages.getString("messages.thanks_from_player") + player.getName());
                    data.setThxBlocks_2(player.getUniqueId(), 1);

                }
            }

        }
        return true;
    }
}