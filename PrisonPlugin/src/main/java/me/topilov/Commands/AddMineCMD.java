package me.topilov.Commands;

import me.topilov.App;
import me.topilov.utils.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class AddMineCMD implements CommandExecutor {

    ConfigManager manager = App.getInstance().manager;
    FileConfiguration messages = manager.getMessagesConfig();

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        if(cmd.getName().equalsIgnoreCase("add_mine")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.DARK_RED + "You cannot use this");
                return true;
            }
            Player player = (Player) sender;
            if (!player.isOp()) {
                player.sendMessage("" + messages.getString("messages.not_enough_rights"));
                return true;
            }

            int var = 1;
            if (App.getMines().contains("mines")) {
                var = App.getMines().getConfigurationSection("mines").getKeys(false).size() + 1;
            }

            App.getMines().set("mines." + var + ".name", "Шахта");
            App.getMines().set("mines." + var + ".material", "Материал");
            App.getMines().set("mines." + var + ".lore", "Описание");
            App.getMines().set("mines." + var + ".level", "Уровень");
            App.getMines().set("mines." + var + ".slot", "Слот в меню");
            App.getMines().set("mines." + var + ".location", player.getLocation());
            App.saveMines();

        }
        return true;
    }
}