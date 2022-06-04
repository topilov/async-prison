package me.topilov.Vault;

import me.topilov.App;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconomyCommands implements CommandExecutor {

    private final Economy economy = App.economy;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (command.getName().equalsIgnoreCase("eco")) {
                if (args[0].equalsIgnoreCase("add")) {
                    if (!sender.isOp()) {
                        sender.sendMessage(ChatColor.DARK_RED + "Недостаточно прав");

                        return true;
                    }
                    if (args.length == 3) {
                        try {
                            Player target = Bukkit.getPlayer(args[1]);
                            int depositAmount = Integer.parseInt(args[2]);

                            economy.depositPlayer(target, depositAmount);
                            assert target != null;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("balance")) {
                    if (args.length == 1) {
                        try {
                            Player target = (Player) sender;
                            int balance = (int) economy.getBalance(target);
                            assert target != null;
                            player.sendMessage(ChatColor.YELLOW + "Баланс: " + balance);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("remove")) {
                    if (args.length == 2) {
                        try {
                            Player target = (Player) sender;
                            int withdrawAmount = Integer.parseInt(args[1]);

                            economy.withdrawPlayer(target, withdrawAmount);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                }
            }
        }
        return true;
    }
}
