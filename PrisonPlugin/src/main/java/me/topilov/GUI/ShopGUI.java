package me.topilov.GUI;

import me.topilov.App;
import me.topilov.DataBase.SQLGetter;
import me.topilov.utils.MinesManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ShopGUI implements CommandExecutor {

    MinesManager manager = App.getInstance().manager;
    SQLGetter data = App.getInstance().data;

    public Inventory shop = Bukkit.getServer().createInventory(null, 45, "§7Магазин");

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        if(cmd.getName().equalsIgnoreCase("shop")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.DARK_RED + "You cannot use this");
                return true;
            }
            Player player = (Player) sender;

            setOtherItems();
            setToolsToInventory(player);
            player.openInventory(shop);

        }
        return true;
    }

    void setToolsToInventory(Player player) {
        FileConfiguration file = manager.getConfig();

        /*
            Добавление шлема в магазин
        */
        manager.getConfig().getConfigurationSection("helmet").getKeys(false).forEach(key -> {
            String name = file.getString("helmet." + key + ".name");
            int level = file.getInt("helmet." + key + ".level");
            int slot = file.getInt("helmet." + key + ".slot");
            int enchant = file.getInt("helmet." + key + ".enchant");
            List<String> lore = file.getStringList("helmet." + key + ".lore");
            int levelTool = data.getLevelHelmet(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("helmet." + key + ".material").toUpperCase()));

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                shop.setItem(slot, item);
            }
        });

        /*
            Добавление нагрудника в магазин
        */
        manager.getConfig().getConfigurationSection("chestplate").getKeys(false).forEach(key -> {
            String name = file.getString("chestplate." + key + ".name");
            int level = file.getInt("chestplate." + key + ".level");
            int slot = file.getInt("chestplate." + key + ".slot");
            int enchant = file.getInt("chestplate." + key + ".enchant");
            List<String> lore = file.getStringList("chestplate." + key + ".lore");
            int levelTool = data.getLevelChestPlate(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("chestplate." + key + ".material").toUpperCase()));

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                shop.setItem(slot, item);
            }
        });

        /*
            Добавление штанов в магазин
        */
        manager.getConfig().getConfigurationSection("leggings").getKeys(false).forEach(key -> {
            String name = file.getString("leggings." + key + ".name");
            int level = file.getInt("leggings." + key + ".level");
            int slot = file.getInt("leggings." + key + ".slot");
            int enchant = file.getInt("leggings." + key + ".enchant");
            List<String> lore = file.getStringList("leggings." + key + ".lore");
            int levelTool = data.getLevelLeggings(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("leggings." + key + ".material").toUpperCase()));

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                shop.setItem(slot, item);
            }
        });

        /*
            Добавление ботинок в магазин
        */
        manager.getConfig().getConfigurationSection("boots").getKeys(false).forEach(key -> {
            String name = file.getString("boots." + key + ".name");
            int level = file.getInt("boots." + key + ".level");
            int slot = file.getInt("boots." + key + ".slot");
            int enchant = file.getInt("boots." + key + ".enchant");
            List<String> lore = file.getStringList("boots." + key + ".lore");
            int levelTool = data.getLevelBoots(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("boots." + key + ".material").toUpperCase()));

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                shop.setItem(slot, item);
            }
        });

        /*
            Добавление меча в магазин
        */
        manager.getConfig().getConfigurationSection("sword").getKeys(false).forEach(key -> {
            String name = file.getString("sword." + key + ".name");
            int level = file.getInt("sword." + key + ".level");
            int slot = file.getInt("sword." + key + ".slot");
            int enchant = file.getInt("sword." + key + ".enchant");
            List<String> lore = file.getStringList("sword." + key + ".lore");
            int levelTool = data.getLevelSword(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("sword." + key + ".material").toUpperCase()));

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.DAMAGE_ALL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                shop.setItem(slot, item);
            }
        });

        /*
            Добавление кирки в магазин
        */
        manager.getConfig().getConfigurationSection("pickaxe").getKeys(false).forEach(key -> {
            String name = file.getString("pickaxe." + key + ".name");
            int level = file.getInt("pickaxe." + key + ".level");
            int slot = file.getInt("pickaxe." + key + ".slot");
            int enchant = file.getInt("pickaxe." + key + ".enchant");
            List<String> lore = file.getStringList("pickaxe." + key + ".lore");
            int levelTool = data.getLevelPickaxe(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("pickaxe." + key + ".material").toUpperCase()));

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.DIG_SPEED, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                shop.setItem(slot, item);
            }
        });

        /*
            Добавление лопаты в магазин
        */
        manager.getConfig().getConfigurationSection("shovel").getKeys(false).forEach(key -> {
            String name = file.getString("shovel." + key + ".name");
            int level = file.getInt("shovel." + key + ".level");
            int slot = file.getInt("shovel." + key + ".slot");
            int enchant = file.getInt("shovel." + key + ".enchant");
            List<String> lore = file.getStringList("shovel." + key + ".lore");
            int levelTool = data.getLevelShovel(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("shovel." + key + ".material").toUpperCase()));

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.DIG_SPEED, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                shop.setItem(slot, item);
            }
        });

        /*
            Добавление топора в магазин
        */
        manager.getConfig().getConfigurationSection("axe").getKeys(false).forEach(key -> {
            String name = file.getString("axe." + key + ".name");
            int level = file.getInt("axe." + key + ".level");
            int slot = file.getInt("axe." + key + ".slot");
            int enchant = file.getInt("axe." + key + ".enchant");
            List<String> lore = file.getStringList("axe." + key + ".lore");
            int levelTool = data.getLevelAxe(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("axe." + key + ".material").toUpperCase()));

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.DIG_SPEED, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                shop.setItem(slot, item);
            }
        });

        /*
            Добавление ножниц в магазин
        */
        manager.getConfig().getConfigurationSection("shears").getKeys(false).forEach(key -> {
            String name = file.getString("shears." + key + ".name");
            int level = file.getInt("shears." + key + ".level");
            int slot = file.getInt("shears." + key + ".slot");
            int enchant = file.getInt("shears." + key + ".enchant");
            List<String> lore = file.getStringList("shears." + key + ".lore");
            int levelTool = data.getLevelShears(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("shears." + key + ".material").toUpperCase()));

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.DIG_SPEED, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                shop.setItem(slot, item);
            }
        });
    }

    void setOtherItems() {
        for (int i = 0; i <= 8; i++) {
            ItemStack none = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta itemMeta_none = none.getItemMeta();
            itemMeta_none.setDisplayName(" ");
            none.setItemMeta(itemMeta_none);
            shop.setItem(i, none);
        }

        ItemStack none = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta itemMeta_none = none.getItemMeta();
        itemMeta_none.setDisplayName(" ");
        none.setItemMeta(itemMeta_none);
        shop.setItem(37, none);
        shop.setItem(40, none);
        shop.setItem(43, none);
        shop.setItem(44, none);

        ItemStack exit = new ItemStack(Material.ARROW);
        ItemMeta itemMeta_arrow = exit.getItemMeta();
        itemMeta_arrow.setDisplayName("§eНазад");
        exit.setItemMeta(itemMeta_arrow);
        shop.setItem(36, exit);

        ItemStack shopItems = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta_shopItems = shopItems.getItemMeta();
        itemMeta_shopItems.setDisplayName("§eМагазин предметов");
        shopItems.setItemMeta(itemMeta_shopItems);
        shop.setItem(38, shopItems);

        ItemStack shopDonate = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta itemMeta_shopDonate = shopDonate.getItemMeta();
        itemMeta_shopDonate.setDisplayName("§eПокупка доната");
        shopDonate.setItemMeta(itemMeta_shopDonate);
        shop.setItem(39, shopDonate);

        ItemStack shopSticks = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta itemMeta_shopSticks = shopSticks.getItemMeta();
        itemMeta_shopSticks.setDisplayName("§eПосохи");
        shopSticks.setItemMeta(itemMeta_shopSticks);
        shop.setItem(41, shopSticks);

        ItemStack shopPerks = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta itemMeta_shopPerks = shopPerks.getItemMeta();
        itemMeta_shopPerks.setDisplayName("§eПерки");
        shopPerks.setItemMeta(itemMeta_shopPerks);
        shop.setItem(42, shopPerks);


    }
}