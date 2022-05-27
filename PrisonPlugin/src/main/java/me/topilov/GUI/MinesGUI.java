package me.topilov.GUI;

import me.topilov.App;
import me.topilov.utils.MinesManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class MinesGUI implements CommandExecutor {

    MinesManager manager = App.getInstance().manager;

    public Inventory minesInv_1 = Bukkit.getServer().createInventory(null, 54, "§7Шахты 0");
    public Inventory minesInv_2 = Bukkit.getServer().createInventory(null, 54, "§7Шахты 1");

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        if(cmd.getName().equalsIgnoreCase("mines")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.DARK_RED + "You cannot use this");
                return true;
            }
            Player player = (Player) sender;

            if(args.length == 0) {
                setOtherItems(1);
                loadMines(1);
                player.openInventory(minesInv_1);
                return true;
            }
            if(args[0].equalsIgnoreCase("2")) {
                setOtherItems(2);
                loadMines(2);
                player.openInventory(minesInv_2);
                return true;
            }
        }

        if(cmd.getName().equalsIgnoreCase("mine")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.DARK_RED + "You cannot use this");
                return true;
            }
            Player player = (Player) sender;

            for (int i = 1; i <= 36; i++) {
                if (args[0].equalsIgnoreCase(String.valueOf(i))) {
                    FileConfiguration file = manager.getConfig();
                    file.getConfigurationSection("mines1").getKeys(false).forEach(mine -> {
                        String level = file.getString("mines1." + mine + ".level");
                        double x = file.getDouble("mines1." + mine + ".x");
                        double y = file.getDouble("mines1." + mine + ".y");
                        double z = file.getDouble("mines1." + mine + ".z");

                /*
                    Проверка на число шахты которое вводит игрок,
                    далее оно перебирается в конфиге и выводит
                    данные с этим числом, в нашем случае выводит x y z,
                    то-есть местоположение шахты
                */
                        if (args[0].equalsIgnoreCase(level)) {
                            player.sendTitle("§aТелепортация на шахту", "§6Уровень " + level, 20, 40, 20);
                            player.teleport(new Location(Bukkit.getWorld("world"), x, y, z));
                        }
                    });
                } else {
                        FileConfiguration file = manager.getConfig();
                        file.getConfigurationSection("mines2").getKeys(false).forEach(mine -> {
                            String level = file.getString("mines2." + mine + ".level");
                            double x = file.getDouble("mines2." + mine + ".x");
                            double y = file.getDouble("mines2." + mine + ".y");
                            double z = file.getDouble("mines2." + mine + ".z");

                /*
                    Проверка на число шахты которое вводит игрок,
                    далее оно перебирается в конфиге и выводит
                    данные с этим числом, в нашем случае выводит x y z,
                    то-есть местоположение шахты
                */
                            if (args[0].equalsIgnoreCase(level)) {
                                player.sendTitle("§aТелепортация на шахту", "§6Уровень " + level, 20, 40, 20);
                                player.teleport(new Location(Bukkit.getWorld("world"), x, y, z));
                            }
                        });
                }
                }
            }
        return true;
    }























    void loadMines(int value) {
        if (value == 1) {
            FileConfiguration file = manager.getConfig();
            manager.getConfig().getConfigurationSection("mines1").getKeys(false).forEach(mine -> {
                String name = file.getString("mines1." + mine + ".name");
                int level = file.getInt("mines1." + mine + ".level");
                int slot = file.getInt("mines1." + mine + ".slot");
                String pvp = file.getString("mines1." + mine + ".pvp");
                ItemStack mines = new ItemStack(Material.getMaterial(file.getString("mines1." + mine + ".material").toUpperCase()));

                ItemMeta itemMeta = mines.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();
                itemMeta.setDisplayName(name);
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
                lore.add("§8------------");
                lore.add("§fМинимальный уровень: §e" + level);
                lore.add("§fПвп: " + pvp);
                lore.add("§8------------");
                itemMeta.setLore(lore);
                mines.setItemMeta(itemMeta);
                minesInv_1.setItem(slot, mines);
            });
        }

        if (value == 2) {
            FileConfiguration file = manager.getConfig();
            manager.getConfig().getConfigurationSection("mines2").getKeys(false).forEach(mine -> {
                String name = file.getString("mines2." + mine + ".name");
                int level = file.getInt("mines2." + mine + ".level");
                int slot = file.getInt("mines2." + mine + ".slot");
                String pvp = file.getString("mines2." + mine + ".pvp");

                ItemStack mines = new ItemStack(Material.getMaterial(file.getString("mines2." + mine + ".material").toUpperCase()));
                ItemMeta itemMeta = mines.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();
                itemMeta.setDisplayName(name);
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
                lore.add("§8------------");
                lore.add("§fМинимальный уровень: " + level);
                lore.add("§fПвп: " + pvp);
                lore.add("§8------------");
                itemMeta.setLore(lore);
                mines.setItemMeta(itemMeta);
                minesInv_2.setItem(slot, mines);
            });
        }
    }

    void setOtherItems(int value) {
        if (value == 1) {
            for (int i = 0; i <= 8; i++) {
                ItemStack none = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta itemMeta_none = none.getItemMeta();
                itemMeta_none.setDisplayName(" ");
                none.setItemMeta(itemMeta_none);
                minesInv_1.setItem(i, none);
            }
            for (int i = 46; i <= 48; i++) {
                ItemStack none = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta itemMeta_none = none.getItemMeta();
                itemMeta_none.setDisplayName(" ");
                none.setItemMeta(itemMeta_none);
                minesInv_1.setItem(i, none);
            }
            for (int i = 51; i <= 53; i++) {
                ItemStack none = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta itemMeta_none = none.getItemMeta();
                itemMeta_none.setDisplayName(" ");
                none.setItemMeta(itemMeta_none);
                minesInv_1.setItem(i, none);
            }
            ItemStack arrow = new ItemStack(Material.ARROW);
            ItemMeta itemMeta_arrow = arrow.getItemMeta();
            itemMeta_arrow.setDisplayName("§eНазад");
            arrow.setItemMeta(itemMeta_arrow);
            minesInv_1.setItem(45, arrow);

            ItemStack arrow_2 = new ItemStack(Material.ARROW);
            ItemMeta itemMeta_arrow_2 = arrow_2.getItemMeta();
            itemMeta_arrow_2.setDisplayName("§6Следующая страница");
            arrow_2.setItemMeta(itemMeta_arrow_2);
            minesInv_1.setItem(50, arrow_2);

            ItemStack eliteMine = new ItemStack(Material.TNT);
            ItemMeta itemMeta_eliteMine = eliteMine.getItemMeta();
            itemMeta_eliteMine.setDisplayName("§6Элитная шахта");
            eliteMine.setItemMeta(itemMeta_eliteMine);
            minesInv_1.setItem(49, eliteMine);
        }

        if (value == 2) {
            for (int i = 0; i <= 8; i++) {
                ItemStack none = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta itemMeta_none = none.getItemMeta();
                itemMeta_none.setDisplayName(" ");
                none.setItemMeta(itemMeta_none);
                minesInv_2.setItem(i, none);
            }
            for (int i = 46; i <= 48; i++) {
                ItemStack none = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta itemMeta_none = none.getItemMeta();
                itemMeta_none.setDisplayName(" ");
                none.setItemMeta(itemMeta_none);
                minesInv_2.setItem(i, none);
            }
            for (int i = 51; i <= 53; i++) {
                ItemStack none = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta itemMeta_none = none.getItemMeta();
                itemMeta_none.setDisplayName(" ");
                none.setItemMeta(itemMeta_none);
                minesInv_2.setItem(i, none);
            }
            ItemStack exit = new ItemStack(Material.ARROW);
            ItemMeta itemMeta_arrow = exit.getItemMeta();
            itemMeta_arrow.setDisplayName("§eНазад");
            exit.setItemMeta(itemMeta_arrow);
            minesInv_2.setItem(45, exit);

            ItemStack back = new ItemStack(Material.ARROW);
            ItemMeta itemMeta_back = back.getItemMeta();
            itemMeta_back.setDisplayName("§6Предыдущая страница");
            back.setItemMeta(itemMeta_back);
            minesInv_2.setItem(48, back);

            ItemStack next = new ItemStack(Material.ARROW);
            ItemMeta itemMeta_next = next.getItemMeta();
            itemMeta_next.setDisplayName("§6Следующая страница");
            next.setItemMeta(itemMeta_next);
            minesInv_2.setItem(50, next);

            ItemStack eliteMine = new ItemStack(Material.TNT);
            ItemMeta itemMeta_eliteMine = eliteMine.getItemMeta();
            itemMeta_eliteMine.setDisplayName("§6Элитная шахта");
            eliteMine.setItemMeta(itemMeta_eliteMine);
            minesInv_2.setItem(49, eliteMine);
        }
    }
}