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

    ConfigManager manager = App.getInstance().manager;
    SQLGetter data = App.getInstance().data;
    FileConfiguration messages = manager.getMessagesConfig();
    Economy economy = App.economy;
    
    public Inventory shop_inventory = Bukkit.createInventory(null, 54, "Улучшения");

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        if(label.equalsIgnoreCase("shop")) {

            if(!(sender instanceof Player)) {
                sender.sendMessage("" + messages.getString("messages.console_use_command"));
                return true;
            }

            Player player = (Player) sender;

            if (args.length == 0) {

                addItemsToTools(player);
                addItemsToEquipment(player);
                player.openInventory(shop_inventory);
                return true;

            }
        }
        return true;
    }

    void addItemsToEquipment(Player player) {

        FileConfiguration file = manager.getShopConfig();

        manager.getShopConfig().getConfigurationSection("helmet").getKeys(false).forEach(key -> {
            String name = file.getString("helmet." + key + ".name");
            int level = file.getInt("helmet." + key + ".level");
            int price = file.getInt("helmet." + key + ".price");
            int enchant = file.getInt("helmet." + key + ".enchant");
            List<String> lore = file.getStringList("helmet." + key + ".lore");
            int levelTool = data.getLevelHelmet(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("helmet." + key + ".material").toUpperCase()));

            int slot = manager.getShopSettingsConfig().getInt("helmet.item_slot");
            String displayName_allow = manager.getShopSettingsConfig().getString("helmet.displayName_allow");
            String displayName_disallow = manager.getShopSettingsConfig().getString("helmet.displayName_disallow");
            int upgrade_slot = manager.getShopSettingsConfig().getInt("helmet.upgrade_slot");
            ItemStack item_allow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("helmet.item_allow").toUpperCase()));
            ItemStack item_disallow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("helmet.item_disallow").toUpperCase()));
            int balance = (int) economy.getBalance(player);

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                item.setItemMeta(itemMeta);
                shop_inventory.setItem(slot, item);

                if (economy.getBalance(player) >= price && data.getLevelHelmet(player.getUniqueId()) < 15) {
                    ItemMeta itemMeta_allow = item_allow.getItemMeta();
                    itemMeta_allow.setDisplayName(displayName_allow);
                    itemMeta_allow.setLore(lore);
                    item_allow.setItemMeta(itemMeta_allow);
                    shop_inventory.setItem(upgrade_slot, item_allow);
                } else {
                    ArrayList<String> lore_disallow = new ArrayList<String>();
                    ItemMeta itemMeta_disallow = item_disallow.getItemMeta();
                    itemMeta_disallow.setDisplayName(displayName_disallow);
                    if (data.getLevelHelmet(player.getUniqueId()) < 15) {
                        lore_disallow.add("");
                        lore_disallow.add("§7Не хватает §f" + (price - balance) + " монет");
                        lore_disallow.add("");
                    } else {
                        lore_disallow.add("§cШлем имеет максимальный уровень");
                    }
                    itemMeta_disallow.setLore(lore_disallow);
                    item_disallow.setItemMeta(itemMeta_disallow);
                    shop_inventory.setItem(upgrade_slot, item_disallow);
                }
            }
        });

        /*
            Добавление нагрудника в магазин
        */
        manager.getShopConfig().getConfigurationSection("chestplate").getKeys(false).forEach(key -> {
            String name = file.getString("chestplate." + key + ".name");
            int level = file.getInt("chestplate." + key + ".level");
            int price = file.getInt("chestplate." + key + ".price");
            int enchant = file.getInt("chestplate." + key + ".enchant");
            List<String> lore = file.getStringList("chestplate." + key + ".lore");
            int levelTool = data.getLevelChestPlate(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("chestplate." + key + ".material").toUpperCase()));

            int slot = manager.getShopSettingsConfig().getInt("chestplate.item_slot");
            String displayName_allow = manager.getShopSettingsConfig().getString("chestplate.displayName_allow");
            String displayName_disallow = manager.getShopSettingsConfig().getString("chestplate.displayName_disallow");
            int upgrade_slot = manager.getShopSettingsConfig().getInt("chestplate.upgrade_slot");
            ItemStack item_allow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("chestplate.item_allow").toUpperCase()));
            ItemStack item_disallow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("chestplate.item_disallow").toUpperCase()));
            int balance = (int) economy.getBalance(player);

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                item.setItemMeta(itemMeta);
                shop_inventory.setItem(slot, item);

                if (economy.getBalance(player) >= price && data.getLevelChestPlate(player.getUniqueId()) < 15) {
                    ItemMeta itemMeta_allow = item_allow.getItemMeta();
                    itemMeta_allow.setDisplayName(displayName_allow);
                    itemMeta_allow.setLore(lore);
                    item_allow.setItemMeta(itemMeta_allow);
                    shop_inventory.setItem(upgrade_slot, item_allow);
                } else {
                    ArrayList<String> lore_disallow = new ArrayList<String>();
                    ItemMeta itemMeta_disallow = item_disallow.getItemMeta();
                    itemMeta_disallow.setDisplayName(displayName_disallow);
                    if (data.getLevelChestPlate(player.getUniqueId()) < 15) {
                        lore_disallow.add("");
                        lore_disallow.add("§7Не хватает §f" + (price - balance) + " монет");
                        lore_disallow.add("");
                    } else {
                        lore_disallow.add("§cНагрудник имеет максимальный уровень");
                    }
                    itemMeta_disallow.setLore(lore_disallow);
                    item_disallow.setItemMeta(itemMeta_disallow);
                    shop_inventory.setItem(upgrade_slot, item_disallow);
                }
            }
        });

        /*
            Добавление штанов в магазин
        */
        manager.getShopConfig().getConfigurationSection("leggings").getKeys(false).forEach(key -> {
            String name = file.getString("leggings." + key + ".name");
            int level = file.getInt("leggings." + key + ".level");
            int price = file.getInt("leggings." + key + ".price");
            int enchant = file.getInt("leggings." + key + ".enchant");
            List<String> lore = file.getStringList("leggings." + key + ".lore");
            int levelTool = data.getLevelLeggings(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("leggings." + key + ".material").toUpperCase()));

            int slot = manager.getShopSettingsConfig().getInt("leggings.item_slot");
            String displayName_allow = manager.getShopSettingsConfig().getString("leggings.displayName_allow");
            String displayName_disallow = manager.getShopSettingsConfig().getString("leggings.displayName_disallow");
            int upgrade_slot = manager.getShopSettingsConfig().getInt("leggings.upgrade_slot");
            ItemStack item_allow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("leggings.item_allow").toUpperCase()));
            ItemStack item_disallow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("leggings.item_disallow").toUpperCase()));
            int balance = (int) economy.getBalance(player);

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                item.setItemMeta(itemMeta);
                shop_inventory.setItem(slot, item);

                if (economy.getBalance(player) >= price && data.getLevelLeggings(player.getUniqueId()) < 15) {
                    ItemMeta itemMeta_allow = item_allow.getItemMeta();
                    itemMeta_allow.setDisplayName(displayName_allow);
                    itemMeta_allow.setLore(lore);
                    item_allow.setItemMeta(itemMeta_allow);
                    shop_inventory.setItem(upgrade_slot, item_allow);
                } else {
                    ArrayList<String> lore_disallow = new ArrayList<String>();
                    ItemMeta itemMeta_disallow = item_disallow.getItemMeta();
                    itemMeta_disallow.setDisplayName(displayName_disallow);
                    if (data.getLevelLeggings(player.getUniqueId()) < 15) {
                        lore_disallow.add("");
                        lore_disallow.add("§7Не хватает §f" + (price - balance) + " монет");
                        lore_disallow.add("");
                    } else {
                        lore_disallow.add("§cБотинки имеют максимальный уровень");
                    }
                    itemMeta_disallow.setLore(lore_disallow);
                    item_disallow.setItemMeta(itemMeta_disallow);
                    shop_inventory.setItem(upgrade_slot, item_disallow);
                }
            }
        });

        /*
            Добавление ботинок в магазин
        */
        manager.getShopConfig().getConfigurationSection("boots").getKeys(false).forEach(key -> {
            String name = file.getString("boots." + key + ".name");
            int level = file.getInt("boots." + key + ".level");
            int price = file.getInt("boots." + key + ".price");
            int enchant = file.getInt("boots." + key + ".enchant");
            List<String> lore = file.getStringList("boots." + key + ".lore");
            int levelTool = data.getLevelBoots(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("boots." + key + ".material").toUpperCase()));

            int slot = manager.getShopSettingsConfig().getInt("boots.item_slot");
            String displayName_allow = manager.getShopSettingsConfig().getString("boots.displayName_allow");
            String displayName_disallow = manager.getShopSettingsConfig().getString("boots.displayName_disallow");
            int upgrade_slot = manager.getShopSettingsConfig().getInt("boots.upgrade_slot");
            ItemStack item_allow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("boots.item_allow").toUpperCase()));
            ItemStack item_disallow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("boots.item_disallow").toUpperCase()));
            int balance = (int) economy.getBalance(player);

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                item.setItemMeta(itemMeta);
                shop_inventory.setItem(slot, item);

                if (economy.getBalance(player) >= price && data.getLevelBoots(player.getUniqueId()) < 15) {
                    ItemMeta itemMeta_allow = item_allow.getItemMeta();
                    itemMeta_allow.setDisplayName(displayName_allow);
                    itemMeta_allow.setLore(lore);
                    item_allow.setItemMeta(itemMeta_allow);
                    shop_inventory.setItem(upgrade_slot, item_allow);
                } else {
                    ArrayList<String> lore_disallow = new ArrayList<String>();
                    ItemMeta itemMeta_disallow = item_disallow.getItemMeta();
                    itemMeta_disallow.setDisplayName(displayName_disallow);
                    if (data.getLevelBoots(player.getUniqueId()) < 15) {
                        lore_disallow.add("");
                        lore_disallow.add("§7Не хватает §f" + (price - balance) + " монет");
                        lore_disallow.add("");
                    } else {
                        lore_disallow.add("§cШтаны имеют максимальный уровень");
                    }
                    itemMeta_disallow.setLore(lore_disallow);
                    item_disallow.setItemMeta(itemMeta_disallow);
                    shop_inventory.setItem(upgrade_slot, item_disallow);
                }
            }
        });
    }

    void addItemsToTools(Player player) {
        FileConfiguration file = manager.getShopConfig();
         /*
            Добавление меча в магазин
        */
        manager.getShopConfig().getConfigurationSection("sword").getKeys(false).forEach(key -> {
            String name = file.getString("sword." + key + ".name");
            int level = file.getInt("sword." + key + ".level");
            int price = file.getInt("sword." + key + ".price");
            int enchant = file.getInt("sword." + key + ".enchant");
            List<String> lore = file.getStringList("sword." + key + ".lore");
            int levelTool = data.getLevelSword(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("sword." + key + ".material").toUpperCase()));

            int slot = manager.getShopSettingsConfig().getInt("sword.item_slot");
            String displayName_allow = manager.getShopSettingsConfig().getString("sword.displayName_allow");
            String displayName_disallow = manager.getShopSettingsConfig().getString("sword.displayName_disallow");
            int upgrade_slot = manager.getShopSettingsConfig().getInt("sword.upgrade_slot");
            ItemStack item_allow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("sword.item_allow").toUpperCase()));
            ItemStack item_disallow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("sword.item_disallow").toUpperCase()));
            int balance = (int) economy.getBalance(player);

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.DAMAGE_ALL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                item.setItemMeta(itemMeta);
                shop_inventory.setItem(slot, item);

                if (economy.getBalance(player) >= price && data.getLevelSword(player.getUniqueId()) < 15) {
                    ItemMeta itemMeta_allow = item_allow.getItemMeta();
                    itemMeta_allow.setDisplayName(displayName_allow);
                    itemMeta_allow.setLore(lore);
                    item_allow.setItemMeta(itemMeta_allow);
                    shop_inventory.setItem(upgrade_slot, item_allow);
                } else {
                    ArrayList<String> lore_disallow = new ArrayList<String>();
                    ItemMeta itemMeta_disallow = item_disallow.getItemMeta();
                    itemMeta_disallow.setDisplayName(displayName_disallow);
                    if (data.getLevelSword(player.getUniqueId()) < 15) {
                        lore_disallow.add("");
                        lore_disallow.add("§7Не хватает §f" + (price - balance) + " монет");
                        lore_disallow.add("");
                    } else {
                        lore_disallow.add("§cМеч имеет максимальный уровень");
                    }
                    itemMeta_disallow.setLore(lore_disallow);
                    item_disallow.setItemMeta(itemMeta_disallow);
                    shop_inventory.setItem(upgrade_slot, item_disallow);
                }
            }
        });

        /*
            Добавление кирки в магазин
        */
        manager.getShopConfig().getConfigurationSection("pickaxe").getKeys(false).forEach(key -> {
            String name = file.getString("pickaxe." + key + ".name");
            int level = file.getInt("pickaxe." + key + ".level");
            int price = file.getInt("pickaxe." + key + ".price");
            int enchant = file.getInt("pickaxe." + key + ".enchant");
            List<String> lore = file.getStringList("pickaxe." + key + ".lore");
            int levelTool = data.getLevelPickaxe(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("pickaxe." + key + ".material").toUpperCase()));

            int slot = manager.getShopSettingsConfig().getInt("pickaxe.item_slot");
            String displayName_allow = manager.getShopSettingsConfig().getString("pickaxe.displayName_allow");
            String displayName_disallow = manager.getShopSettingsConfig().getString("pickaxe.displayName_disallow");
            int upgrade_slot = manager.getShopSettingsConfig().getInt("pickaxe.upgrade_slot");
            ItemStack item_allow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("pickaxe.item_allow").toUpperCase()));
            ItemStack item_disallow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("pickaxe.item_disallow").toUpperCase()));
            int balance = (int) economy.getBalance(player);

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.DAMAGE_ALL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                item.setItemMeta(itemMeta);
                shop_inventory.setItem(slot, item);

                if (economy.getBalance(player) >= price && data.getLevelPickaxe(player.getUniqueId()) < 15) {
                    ItemMeta itemMeta_allow = item_allow.getItemMeta();
                    itemMeta_allow.setDisplayName(displayName_allow);
                    itemMeta_allow.setLore(lore);
                    item_allow.setItemMeta(itemMeta_allow);
                    shop_inventory.setItem(upgrade_slot, item_allow);
                } else {
                    ArrayList<String> lore_disallow = new ArrayList<String>();
                    ItemMeta itemMeta_disallow = item_disallow.getItemMeta();
                    itemMeta_disallow.setDisplayName(displayName_disallow);
                    if (data.getLevelPickaxe(player.getUniqueId()) < 15) {
                        lore_disallow.add("");
                        lore_disallow.add("§7Не хватает §f" + (price - balance) + " монет");
                        lore_disallow.add("");
                    } else {
                        lore_disallow.add("§cКирка имеет максимальный уровень");
                    }
                    itemMeta_disallow.setLore(lore_disallow);
                    item_disallow.setItemMeta(itemMeta_disallow);
                    shop_inventory.setItem(upgrade_slot, item_disallow);
                }
            }
        });

        /*
            Добавление лопаты в магазин
        */
        manager.getShopConfig().getConfigurationSection("shovel").getKeys(false).forEach(key -> {
            String name = file.getString("shovel." + key + ".name");
            int level = file.getInt("shovel." + key + ".level");
            int price = file.getInt("shovel." + key + ".price");
            int enchant = file.getInt("shovel." + key + ".enchant");
            List<String> lore = file.getStringList("shovel." + key + ".lore");
            int levelTool = data.getLevelShovel(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("shovel." + key + ".material").toUpperCase()));

            int slot = manager.getShopSettingsConfig().getInt("shovel.item_slot");
            String displayName_allow = manager.getShopSettingsConfig().getString("shovel.displayName_allow");
            String displayName_disallow = manager.getShopSettingsConfig().getString("shovel.displayName_disallow");
            int upgrade_slot = manager.getShopSettingsConfig().getInt("shovel.upgrade_slot");
            ItemStack item_allow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("shovel.item_allow").toUpperCase()));
            ItemStack item_disallow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("shovel.item_disallow").toUpperCase()));
            int balance = (int) economy.getBalance(player);

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.DAMAGE_ALL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                item.setItemMeta(itemMeta);
                shop_inventory.setItem(slot, item);

                if (economy.getBalance(player) >= price && data.getLevelShovel(player.getUniqueId()) < 15) {
                    ItemMeta itemMeta_allow = item_allow.getItemMeta();
                    itemMeta_allow.setDisplayName(displayName_allow);
                    itemMeta_allow.setLore(lore);
                    item_allow.setItemMeta(itemMeta_allow);
                    shop_inventory.setItem(upgrade_slot, item_allow);
                } else {
                    ArrayList<String> lore_disallow = new ArrayList<String>();
                    ItemMeta itemMeta_disallow = item_disallow.getItemMeta();
                    itemMeta_disallow.setDisplayName(displayName_disallow);
                    if (data.getLevelShovel(player.getUniqueId()) < 15) {
                        lore_disallow.add("");
                        lore_disallow.add("§7Не хватает §f" + (price - balance) + " монет");
                        lore_disallow.add("");
                    } else {
                        lore_disallow.add("§cЛопата имеет максимальный уровень");
                    }
                    itemMeta_disallow.setLore(lore_disallow);
                    item_disallow.setItemMeta(itemMeta_disallow);
                    shop_inventory.setItem(upgrade_slot, item_disallow);
                }
            }
        });

        /*
            Добавление топора в магазин
        */
        manager.getShopConfig().getConfigurationSection("axe").getKeys(false).forEach(key -> {
            String name = file.getString("axe." + key + ".name");
            int level = file.getInt("axe." + key + ".level");
            int price = file.getInt("axe." + key + ".price");
            int enchant = file.getInt("axe." + key + ".enchant");
            List<String> lore = file.getStringList("axe." + key + ".lore");
            int levelTool = data.getLevelAxe(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("axe." + key + ".material").toUpperCase()));

            int slot = manager.getShopSettingsConfig().getInt("axe.item_slot");
            String displayName_allow = manager.getShopSettingsConfig().getString("axe.displayName_allow");
            String displayName_disallow = manager.getShopSettingsConfig().getString("axe.displayName_disallow");
            int upgrade_slot = manager.getShopSettingsConfig().getInt("axe.upgrade_slot");
            ItemStack item_allow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("shovel.item_allow").toUpperCase()));
            ItemStack item_disallow = new ItemStack(Material.getMaterial(manager.getShopSettingsConfig().getString("shovel.item_disallow").toUpperCase()));
            int balance = (int) economy.getBalance(player);

            if (levelTool == level) {
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.DAMAGE_ALL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);;
                item.setItemMeta(itemMeta);
                shop_inventory.setItem(slot, item);

                if (economy.getBalance(player) >= price && data.getLevelAxe(player.getUniqueId()) < 15) {
                    ItemMeta itemMeta_allow = item_allow.getItemMeta();
                    itemMeta_allow.setDisplayName(displayName_allow);
                    itemMeta_allow.setLore(lore);
                    item_allow.setItemMeta(itemMeta_allow);
                    shop_inventory.setItem(upgrade_slot, item_allow);
                } else {
                    ArrayList<String> lore_disallow = new ArrayList<String>();
                    ItemMeta itemMeta_disallow = item_disallow.getItemMeta();
                    itemMeta_disallow.setDisplayName(displayName_disallow);
                    if (data.getLevelAxe(player.getUniqueId()) < 15) {
                        lore_disallow.add("");
                        lore_disallow.add("§7Не хватает §f" + (price - balance) + " монет");
                        lore_disallow.add("");
                    } else {
                        lore_disallow.add("§cТопор имеет максимальный уровень");
                    }
                    itemMeta_disallow.setLore(lore_disallow);
                    item_disallow.setItemMeta(itemMeta_disallow);
                    shop_inventory.setItem(upgrade_slot, item_disallow);
                }
            }
        });
    }
}