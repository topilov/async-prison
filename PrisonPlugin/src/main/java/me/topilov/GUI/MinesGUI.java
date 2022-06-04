package me.topilov.GUI;

import me.topilov.App;
import me.topilov.DataBase.SQLGetter;
import me.topilov.utils.ConfigManager;
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
import java.util.List;
import java.util.stream.Collectors;

public class MinesGUI implements CommandExecutor {

    ConfigManager manager = App.getInstance().manager;
    SQLGetter data = App.getInstance().data;
    FileConfiguration messages = manager.getMessagesConfig();

    public Inventory minesInventory = Bukkit.getServer().createInventory(null, 45, "Шахты");

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        if(cmd.getName().equalsIgnoreCase("mines")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage("" + messages.getString("messages.console_use_command"));
                return true;
            }
            Player player = (Player) sender;

            if(args.length == 0) {
                setOtherItems();
                loadMines(player);
                player.openInventory(minesInventory);
                return true;
            }
        }
        return true;
    }

    void loadMines(Player player) {
        FileConfiguration file = manager.getMinesConfig();
        manager.getMinesConfig().getConfigurationSection("mines").getKeys(false).forEach(mine -> {
            String name = file.getString("mines." + mine + ".name");
            int level = file.getInt("mines." + mine + ".level");
            int slot = file.getInt("mines." + mine + ".slot");
            List<String> lore = file.getStringList("mines." + mine + ".lore");
            ItemStack mines = new ItemStack(Material.getMaterial(file.getString("mines." + mine + ".material").toUpperCase()));

            if (data.getLevel(player.getUniqueId()) >= level) {
                ItemMeta itemMeta = mines.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
                itemMeta.setLore(lore);
                mines.setItemMeta(itemMeta);
                minesInventory.setItem(slot, mines);
            } else {
                ItemStack notRequired = new ItemStack(Material.POPPED_CHORUS_FRUIT);
                ItemMeta itemMeta = notRequired.getItemMeta();
                ArrayList<String> lore_none = new ArrayList<>();
                itemMeta.setDisplayName("§cЗаблокировано");
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
                lore_none.add("§fУровень: §c" + level);
                itemMeta.setLore(lore_none);
                notRequired.setItemMeta(itemMeta);
                minesInventory.setItem(slot, notRequired);
            }
        });
    }

    void setOtherItems() {
        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta itemMeta_arrow = arrow.getItemMeta();
        itemMeta_arrow.setDisplayName("§eНазад в меню");
        arrow.setItemMeta(itemMeta_arrow);
        minesInventory.setItem(36, arrow);
    }
}