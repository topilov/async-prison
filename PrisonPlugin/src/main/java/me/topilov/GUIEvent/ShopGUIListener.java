package me.topilov.GUIEvent;

import me.topilov.App;
import me.topilov.DataBase.SQLGetter;
import me.topilov.utils.ConfigManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ShopGUIListener implements Listener {

    Economy economy = App.economy;
    ConfigManager manager = App.getInstance().manager;
    SQLGetter data = App.getInstance().data;
    FileConfiguration messages = manager.getMessagesConfig();

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();


        /*
            Если игрок находится в меню улучшения
        */
            if (e.getInventory().getTitle().equalsIgnoreCase("Улучшения")) {

                if (clickedItem == null) return;
                if (!clickedItem.hasItemMeta()) return;

                if (e.getClickedInventory().getType() == InventoryType.PLAYER) {
                    e.setCancelled(true);
                }

                if (clickedItem.getItemMeta().getDisplayName().contains("§cНельзя улучшить")) {
                    e.setCancelled(true);
                    return;
                }

                if (e.getSlot() == 15) {
                    upgradeHelmet(e, player);
                }
                if (e.getSlot() == 24) {
                    upgradeChestPlate(e, player);
                }
                if (e.getSlot() == 33) {
                    upgradeLeggings(e, player);
                }
                if (e.getSlot() == 42) {
                    upgradeBoots(e, player);
                }
                if (e.getSlot() == 12) {
                    upgradeSword(e, player);
                }
                if ((e.getSlot() == 21)) {
                    upgradePickaxe(e, player);
                }
                if (e.getSlot() == 39) {
                    upgradeShovel(e, player);
                }
                if (e.getSlot() == 30) {
                    upgradeAxe(e, player);
                }
        }

    }

    void upgradeHelmet(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getShopConfig();

        manager.getShopConfig().getConfigurationSection("helmet").getKeys(false).forEach(key -> {
            String name = file.getString("helmet." + key + ".name");
            int enchant = file.getInt("helmet." + key + ".enchant");
            int price = file.getInt("helmet." + key + ".price");
            int requiredLevel = file.getInt("helmet." + key + ".level");
            int playerLevel = data.getLevelHelmet(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("helmet." + key + ".material").toUpperCase()));
            String removeItem = file.getString("helmet." + (requiredLevel - 1) + ".material");


            if (playerLevel == requiredLevel) {

                if (economy.getBalance(player) < price) {
                    player.sendMessage("" + messages.getString("messages.not_enough_money"));
                    e.setCancelled(true);
                    return;
                }

                App.getInstance().getServer().dispatchCommand(App.getInstance().getServer().getConsoleSender(), "minecraft:clear " + player.getName() + " " + removeItem + " 1");


                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                itemMeta.setUnbreakable(true);
                item.setItemMeta(itemMeta);

                player.getInventory().setHelmet(item);
                e.setCancelled(true);

                Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        economy.withdrawPlayer(player, price);
                        data.addLevelHelmet(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradeChestPlate(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getShopConfig();

        manager.getShopConfig().getConfigurationSection("chestplate").getKeys(false).forEach(key -> {
            String name = file.getString("chestplate." + key + ".name");
            int enchant = file.getInt("chestplate." + key + ".enchant");
            int price = file.getInt("chestplate." + key + ".price");
            int requiredLevel = file.getInt("chestplate." + key + ".level");
            int playerLevel = data.getLevelChestPlate(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("chestplate." + key + ".material").toUpperCase()));
            String removeItem = file.getString("chestplate." + (requiredLevel - 1) + ".material");


            if (playerLevel == requiredLevel) {

                if (economy.getBalance(player) < price) {
                    player.sendMessage("" + messages.getString("messages.not_enough_money"));
                    e.setCancelled(true);
                    return;
                }

                App.getInstance().getServer().dispatchCommand(App.getInstance().getServer().getConsoleSender(), "minecraft:clear " + player.getName() + " " + removeItem + " 1");


                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                itemMeta.setUnbreakable(true);
                item.setItemMeta(itemMeta);

                player.getInventory().setChestplate(item);
                e.setCancelled(true);

                Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        economy.withdrawPlayer(player, price);
                        data.addLevelChestPlate(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradeLeggings(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getShopConfig();

        manager.getShopConfig().getConfigurationSection("leggings").getKeys(false).forEach(key -> {
            String name = file.getString("leggings." + key + ".name");
            int enchant = file.getInt("leggings." + key + ".enchant");
            int price = file.getInt("leggings." + key + ".price");
            int requiredLevel = file.getInt("leggings." + key + ".level");
            int playerLevel = data.getLevelLeggings(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("leggings." + key + ".material").toUpperCase()));
            String removeItem = file.getString("leggings." + (requiredLevel - 1) + ".material");


            if (playerLevel == requiredLevel) {

                if (economy.getBalance(player) < price) {
                    player.sendMessage("" + messages.getString("messages.not_enough_money"));
                    e.setCancelled(true);
                    return;
                }

                App.getInstance().getServer().dispatchCommand(App.getInstance().getServer().getConsoleSender(), "minecraft:clear " + player.getName() + " " + removeItem + " 1");


                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                itemMeta.setUnbreakable(true);
                item.setItemMeta(itemMeta);

                player.getInventory().setLeggings(item);
                e.setCancelled(true);

                Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        economy.withdrawPlayer(player, price);
                        data.addLevelLeggings(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradeBoots(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getShopConfig();

        manager.getShopConfig().getConfigurationSection("boots").getKeys(false).forEach(key -> {
            String name = file.getString("boots." + key + ".name");
            int enchant = file.getInt("boots." + key + ".enchant");
            int price = file.getInt("boots." + key + ".price");
            int level = file.getInt("boots." + key + ".level");
            int levelTool = data.getLevelBoots(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("boots." + key + ".material").toUpperCase()));
            String removeItem = file.getString("boots." + (level - 1) + ".material");


            if (level == levelTool) {

                if (economy.getBalance(player) < price) {
                    player.sendMessage("" + messages.getString("messages.not_enough_money"));
                    e.setCancelled(true);
                    return;
                }

                App.getInstance().getServer().dispatchCommand(App.getInstance().getServer().getConsoleSender(), "minecraft:clear " + player.getName() + " " + removeItem + " 1");


                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                itemMeta.setUnbreakable(true);
                item.setItemMeta(itemMeta);

                player.getInventory().setBoots(item);
                e.setCancelled(true);

                Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        economy.withdrawPlayer(player, price);
                        data.addLevelBoots(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradeSword(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getShopConfig();

        manager.getShopConfig().getConfigurationSection("sword").getKeys(false).forEach(key -> {
            String name = file.getString("sword." + key + ".name");
            int enchant = file.getInt("sword." + key + ".enchant");
            int price = file.getInt("sword." + key + ".price");
            int requiredLevel = file.getInt("sword." + key + ".level");
            int playerLevel = data.getLevelSword(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("sword." + key + ".material").toUpperCase()));
            String removeItem = file.getString("sword." + (requiredLevel - 1) + ".material");


            if (playerLevel == requiredLevel) {

                if (economy.getBalance(player) < price) {
                    player.sendMessage("" + messages.getString("messages.not_enough_money"));
                    e.setCancelled(true);
                    return;
                }

                App.getInstance().getServer().dispatchCommand(App.getInstance().getServer().getConsoleSender(), "minecraft:clear " + player.getName() + " " + removeItem + " 1");


                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.DAMAGE_ALL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                itemMeta.setUnbreakable(true);
                item.setItemMeta(itemMeta);

                player.getInventory().addItem(item);
                e.setCancelled(true);

                Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        economy.withdrawPlayer(player, price);
                        data.addLevelSword(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradePickaxe(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getShopConfig();

        manager.getShopConfig().getConfigurationSection("pickaxe").getKeys(false).forEach(key -> {
            String name = file.getString("pickaxe." + key + ".name");
            int enchant = file.getInt("pickaxe." + key + ".enchant");
            int price = file.getInt("pickaxe." + key + ".price");
            int requiredLevel = file.getInt("pickaxe." + key + ".level");
            int playerLevel = data.getLevelPickaxe(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("pickaxe." + key + ".material").toUpperCase()));
            String removeItem = file.getString("pickaxe." + (requiredLevel - 1) + ".material");


            if (playerLevel == requiredLevel) {

                if (economy.getBalance(player) < price) {
                    player.sendMessage("" + messages.getString("messages.not_enough_money"));
                    e.setCancelled(true);
                    return;
                }

                App.getInstance().getServer().dispatchCommand(App.getInstance().getServer().getConsoleSender(), "minecraft:clear " + player.getName() + " " + removeItem + " 1");


                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.DIG_SPEED, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                itemMeta.setUnbreakable(true);
                item.setItemMeta(itemMeta);

                player.getInventory().addItem(item);
                e.setCancelled(true);

                Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        economy.withdrawPlayer(player, price);
                        data.addLevelPickaxe(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradeShovel(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getShopConfig();

        manager.getShopConfig().getConfigurationSection("shovel").getKeys(false).forEach(key -> {
            String name = file.getString("shovel." + key + ".name");
            int enchant = file.getInt("shovel." + key + ".enchant");
            int price = file.getInt("shovel." + key + ".price");
            int requiredLevel = file.getInt("shovel." + key + ".level");
            int playerLevel = data.getLevelShovel(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("shovel." + key + ".material").toUpperCase()));
            String removeItem = file.getString("shovel." + (requiredLevel - 1) + ".material");


            if (playerLevel == requiredLevel) {

                if (economy.getBalance(player) < price) {
                    player.sendMessage("" + messages.getString("messages.not_enough_money"));
                    e.setCancelled(true);
                    return;
                }

                App.getInstance().getServer().dispatchCommand(App.getInstance().getServer().getConsoleSender(), "minecraft:clear " + player.getName() + " " + removeItem + " 1");


                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.DIG_SPEED, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                itemMeta.setUnbreakable(true);
                item.setItemMeta(itemMeta);

                player.getInventory().addItem(item);
                e.setCancelled(true);

                Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        economy.withdrawPlayer(player, price);
                        data.addLevelShovel(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradeAxe(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getShopConfig();

        manager.getShopConfig().getConfigurationSection("axe").getKeys(false).forEach(key -> {
            String name = file.getString("axe." + key + ".name");
            int enchant = file.getInt("axe." + key + ".enchant");
            int price = file.getInt("axe." + key + ".price");
            int requiredLevel = file.getInt("axe." + key + ".level");
            int playerLevel = data.getLevelAxe(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("axe." + key + ".material").toUpperCase()));


            if (playerLevel == requiredLevel) {

                if (economy.getBalance(player) < price) {
                    player.sendMessage("" + messages.getString("messages.not_enough_money"));
                    e.setCancelled(true);
                    return;
                }

                if (playerLevel == 0) {
                    App.getInstance().getServer().dispatchCommand(App.getInstance().getServer().getConsoleSender(), "minecraft:clear " + player.getName() + " " + file.getString("axe." + requiredLevel + ".material") + " 1");
                } else {
                    App.getInstance().getServer().dispatchCommand(App.getInstance().getServer().getConsoleSender(), "minecraft:clear " + player.getName() + " " + file.getString("axe." + (requiredLevel - 1) + ".material") + " 1");
                }


                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.DIG_SPEED, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                itemMeta.setUnbreakable(true);
                item.setItemMeta(itemMeta);

                player.getInventory().addItem(item);
                e.setCancelled(true);

                Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        economy.withdrawPlayer(player, price);
                        data.addLevelAxe(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradeShears(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getShopConfig();

        manager.getShopConfig().getConfigurationSection("shears").getKeys(false).forEach(key -> {
            String name = file.getString("shears." + key + ".name");
            int enchant = file.getInt("shears." + key + ".enchant");
            int price = file.getInt("shears." + key + ".price");
            int requiredLevel = file.getInt("shears." + key + ".level");
            int playerLevel = data.getLevelShears(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("shears." + key + ".material").toUpperCase()));
            String removeItem = file.getString("shears." + (requiredLevel - 1) + ".material");


            if (playerLevel == requiredLevel) {

                if (economy.getBalance(player) < price) {
                    player.sendMessage("" + messages.getString("messages.not_enough_money"));
                    e.setCancelled(true);
                    return;
                }

                App.getInstance().getServer().dispatchCommand(App.getInstance().getServer().getConsoleSender(), "minecraft:clear " + player.getName() + " " + removeItem + " 1");


                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.DIG_SPEED, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                itemMeta.setUnbreakable(true);
                item.setItemMeta(itemMeta);

                player.getInventory().addItem(item);
                e.setCancelled(true);

                Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        economy.withdrawPlayer(player, price);
                        data.addLevelShears(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }
}
