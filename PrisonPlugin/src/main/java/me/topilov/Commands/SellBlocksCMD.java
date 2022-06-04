package me.topilov.Commands;

import me.topilov.App;
import me.topilov.DataBase.SQLGetter;
import me.topilov.utils.ConfigManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class SellBlocksCMD implements CommandExecutor {

    ConfigManager manager = App.getInstance().manager;
    SQLGetter data = App.getInstance().data;
    Economy economy = App.economy;
    FileConfiguration messages = manager.getMessagesConfig();

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        if(cmd.getName().equalsIgnoreCase("sell")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage("" + messages.getString("messages.console_use_command"));
                return true;
            }
            Player player = (Player) sender;
            FileConfiguration file = manager.getBlocksConfig();

            manager.getBlocksConfig().getConfigurationSection("blocks").getKeys(false).forEach(key -> {
                int price = file.getInt("blocks." + key + ".price");
                Material item = Material.getMaterial(file.getString("blocks." + key + ".material").toUpperCase());
                double booster = data.getMoneyBooster(player.getUniqueId());

                /*
                    Проверяет слоты от 0 до 35 на предмет из конфига,
                    если предмет равен null, то проверяет следующий слот.
                    Если все успешно, то через 1 тик, слоты, в которых лежали
                    проданные предметы, заменяются на null и выдается денежка
                */
                for (int i = 0; i <= player.getInventory().getSize(); i++) {
                    try {
                        if (player.getInventory().getItem(i).getType() == item) {
                            int finalI = i;
                            Bukkit.getScheduler().runTaskLater(App.getInstance(), () -> {
                                player.getInventory().setItem(finalI, null);
                            }, 1);

                            economy.depositPlayer(player, price * player.getInventory().getItem(i).getAmount() * booster);
                        }
                    } catch (NullPointerException e) {
                        if (player.getInventory().getItem(i) == null) {
                            i++;
                        }
                    }
                }

                player.sendMessage("" + messages.getString("messages.sell_blocks"));
            });

        }
        return true;
    }
}