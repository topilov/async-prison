package me.topilov.GUI;

import me.topilov.App;
import me.topilov.DataBase.SQLGetter;
import me.topilov.utils.ConfigManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

public class MenuGUI implements CommandExecutor {

    ConfigManager manager = App.getInstance().manager;
    Economy economy = App.economy;
    SQLGetter data = App.getInstance().data;
    FileConfiguration messages = manager.getMessagesConfig();

    public Inventory menuInv = Bukkit.getServer().createInventory(null, 27, "Меню");

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        if(cmd.getName().equalsIgnoreCase("menu")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage("" + messages.getString("messages.console_use_command"));
                return true;
            }
            Player player = (Player) sender;

            addItemsToInventory(player);
            player.openInventory(menuInv);
        }
        return true;
    }


    void addItemsToInventory(Player player) {
        ItemStack donate = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta_donate = donate.getItemMeta();
        ArrayList<String> lore_upgrades = new ArrayList<>();
        itemMeta_donate.setDisplayName("§6Донат");
        itemMeta_donate.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        lore_upgrades.add("§e§l▶ §fНажмите, чтобы открыть меню доната");
        itemMeta_donate.setLore(lore_upgrades);
        donate.setItemMeta(itemMeta_donate);
        menuInv.setItem(9, donate);

        setLevelItemToInventory(player);

        ItemStack mines = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta itemMeta_mines = mines.getItemMeta();
        ArrayList<String> lore_mines = new ArrayList<>();
        itemMeta_mines.setDisplayName("§6Шахты");
        itemMeta_mines.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        lore_mines.add("§e§l▶ §fНажмите, чтобы открыть меню шахт");
        itemMeta_mines.setLore(lore_mines);
        mines.setItemMeta(itemMeta_mines);
        menuInv.setItem(13, mines);

        ItemStack shop = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta_shop = shop.getItemMeta();
        ArrayList<String> lore_shop = new ArrayList<>();
        itemMeta_shop.setDisplayName("§6Магазин");
        itemMeta_shop.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        lore_shop.add("§e§l▶ §fНажмите, чтобы открыть магазин");
        itemMeta_shop.setLore(lore_shop);
        shop.setItemMeta(itemMeta_shop);
        menuInv.setItem(15, shop);

        ItemStack bosses = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta_bosses = bosses.getItemMeta();
        ArrayList<String> lore_bosses = new ArrayList<>();
        itemMeta_bosses.setDisplayName("§6Боссы");
        lore_bosses.add("§e§l▶ §cСкоро...");
        itemMeta_bosses.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        itemMeta_bosses.setLore(lore_bosses);
        bosses.setItemMeta(itemMeta_bosses);
        menuInv.setItem(17, bosses);

    }

    void setLevelItemToInventory(Player player) {
        FileConfiguration file = manager.getLevelConfig();

        manager.getLevelConfig().getConfigurationSection("level").getKeys(false).forEach(key -> {
            int requiredMoney = file.getInt("level." + key + ".money");
            int requiredBlocks = file.getInt("level." + key + ".blocks");
            int playerMoney = (int) economy.getBalance(player);
            int playerBlocks = data.getBlocks(player.getUniqueId());
            int playerLevel = data.getLevel(player.getUniqueId());
            int requiredLevel = file.getInt("level." + key + ".level");


            if (playerLevel == requiredLevel) {
                ItemStack level = new ItemStack(Material.EXPERIENCE_BOTTLE);
                ItemMeta itemMeta_level = level.getItemMeta();
                ArrayList<String> lore_level = new ArrayList<>();
                itemMeta_level.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
                if (data.getBlocks(player.getUniqueId()) >= requiredBlocks) {
                    lore_level.add("§e§l▶ §fБлоки: §a" + playerBlocks + "/" + requiredBlocks);
                } else {
                    lore_level.add("§e§l▶ §fБлоки: §c" + playerBlocks + "/" + requiredBlocks);
                }
                if (economy.getBalance(player) >= requiredMoney) {
                    lore_level.add("§e§l▶ §fДеньги: §a" + playerMoney + "/" + requiredMoney);
                } else {
                    lore_level.add("§e§l▶ §fДеньги: §c" + playerMoney + "/" + requiredMoney);
                }
                if (data.getLevel(player.getUniqueId()) != 15) {
                    itemMeta_level.setDisplayName("§6Поднять уровень");
                    itemMeta_level.setLore(lore_level);
                }
                if (data.getLevel(player.getUniqueId()) == 15) {
                    itemMeta_level.setDisplayName(messages.getString("messages.max_level"));
                }
                level.setItemMeta(itemMeta_level);
                menuInv.setItem(11, level);
            }
        });
    }
}