package me.topilov.GUIEvent;

import me.topilov.App;
import me.topilov.DataBase.SQLGetter;
import me.topilov.utils.MinesManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ShopGUIListener implements Listener {

    MinesManager manager = App.getInstance().manager;
    SQLGetter data = App.getInstance().data;

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (e.getInventory().getTitle().equalsIgnoreCase("§7Магазин")) {

            if (clickedItem == null) return;
            if (!clickedItem.hasItemMeta()) return;

            if (clickedItem.getItemMeta().getDisplayName().contains("§cMAX")) {
                player.sendMessage("§cПредмет имеет максимальный уровень");
                e.setCancelled(true);
                return;
            }

            if (clickedItem.getType() == Material.LEATHER_HELMET || clickedItem.getType() == Material.IRON_HELMET || clickedItem.getType() == Material.DIAMOND_HELMET) {
                upgradeHelmet(e, player);
            }
            if (clickedItem.getType() == Material.LEATHER_CHESTPLATE || clickedItem.getType() == Material.IRON_CHESTPLATE || clickedItem.getType() == Material.DIAMOND_CHESTPLATE) {
                upgradeChestPlate(e, player);
            }
            if (clickedItem.getType() == Material.LEATHER_LEGGINGS || clickedItem.getType() == Material.IRON_LEGGINGS || clickedItem.getType() == Material.DIAMOND_LEGGINGS) {
                upgradeLeggings(e, player);
            }
            if (clickedItem.getType() == Material.LEATHER_BOOTS || clickedItem.getType() == Material.IRON_BOOTS || clickedItem.getType() == Material.DIAMOND_BOOTS) {
                upgradeBoots(e, player);
            }
            if (clickedItem.getType() == Material.WOODEN_SWORD || clickedItem.getType() == Material.IRON_SWORD || clickedItem.getType() == Material.DIAMOND_SWORD) {
                upgradeSword(e, player);
            }
            if (clickedItem.getType() == Material.WOODEN_PICKAXE || clickedItem.getType() == Material.IRON_PICKAXE || clickedItem.getType() == Material.DIAMOND_PICKAXE) {
                upgradePickaxe(e, player);
            }
            if (clickedItem.getType() == Material.WOODEN_SHOVEL || clickedItem.getType() == Material.IRON_SHOVEL || clickedItem.getType() == Material.DIAMOND_SHOVEL) {
                upgradeShovel(e, player);
            }
            if (clickedItem.getType() == Material.WOODEN_AXE || clickedItem.getType() == Material.IRON_AXE || clickedItem.getType() == Material.DIAMOND_AXE) {
                upgradeAxe(e, player);
            }
            if (clickedItem.getType() == Material.SHEARS) {
                upgradeShears(e, player);
            }




            if (clickedItem.getType() == Material.BLACK_STAINED_GLASS_PANE) {
                e.setCancelled(true);
            }

            if (clickedItem.getItemMeta().getDisplayName().contains("§eНазад")) {
                player.chat("/menu");
                e.setCancelled(true);
            }

            if (clickedItem.getItemMeta().getDisplayName().contains("§eМагазин предметов")) {
                e.setCancelled(true);
            }

            if (clickedItem.getItemMeta().getDisplayName().contains("§eПокупка доната")) {
                player.sendMessage("§cВ разработке");
                e.setCancelled(true);
            }

            if (clickedItem.getItemMeta().getDisplayName().contains("§eПосохи")) {
                player.sendMessage("§cВ разработке");
                e.setCancelled(true);
            }

            if (clickedItem.getItemMeta().getDisplayName().contains("§eПерки")) {
                player.sendMessage("§cВ разработке");
                e.setCancelled(true);
            }
        }
    }

    void upgradeHelmet(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getConfig();

        manager.getConfig().getConfigurationSection("helmet").getKeys(false).forEach(key -> {
            String name = file.getString("helmet." + key + ".name");
            int enchant = file.getInt("helmet." + key + ".enchant");
            int level = file.getInt("helmet." + key + ".level");
            int levelTool = data.getLevelHelmet(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("helmet." + key + ".material").toUpperCase()));
            String removeItem = file.getString("helmet." + (level - 1) + ".material");


            if (level == levelTool) {

                App.getInstance().getServer().dispatchCommand(App.getInstance().getServer().getConsoleSender(), "minecraft:clear " + player.getName() + " " + removeItem + " 1");


                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                itemMeta.setUnbreakable(true);
                item.setItemMeta(itemMeta);

                player.getInventory().addItem(item);
                e.setCancelled(true);

                Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        data.addLevelHelmet(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradeChestPlate(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getConfig();

        manager.getConfig().getConfigurationSection("chestplate").getKeys(false).forEach(key -> {
            String name = file.getString("chestplate." + key + ".name");
            int enchant = file.getInt("chestplate." + key + ".enchant");
            int level = file.getInt("chestplate." + key + ".level");
            int levelTool = data.getLevelChestPlate(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("chestplate." + key + ".material").toUpperCase()));
            String removeItem = file.getString("chestplate." + (level - 1) + ".material");


            if (level == levelTool) {

                App.getInstance().getServer().dispatchCommand(App.getInstance().getServer().getConsoleSender(), "minecraft:clear " + player.getName() + " " + removeItem + " 1");


                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                itemMeta.setUnbreakable(true);
                item.setItemMeta(itemMeta);

                player.getInventory().addItem(item);
                e.setCancelled(true);

                Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        data.addLevelChestPlate(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradeLeggings(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getConfig();

        manager.getConfig().getConfigurationSection("leggings").getKeys(false).forEach(key -> {
            String name = file.getString("leggings." + key + ".name");
            int enchant = file.getInt("leggings." + key + ".enchant");
            int level = file.getInt("leggings." + key + ".level");
            int levelTool = data.getLevelLeggings(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("leggings." + key + ".material").toUpperCase()));
            String removeItem = file.getString("leggings." + (level - 1) + ".material");


            if (level == levelTool) {

                App.getInstance().getServer().dispatchCommand(App.getInstance().getServer().getConsoleSender(), "minecraft:clear " + player.getName() + " " + removeItem + " 1");


                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                itemMeta.setUnbreakable(true);
                item.setItemMeta(itemMeta);

                player.getInventory().addItem(item);
                e.setCancelled(true);

                Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        data.addLevelLeggings(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradeBoots(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getConfig();

        manager.getConfig().getConfigurationSection("boots").getKeys(false).forEach(key -> {
            String name = file.getString("boots." + key + ".name");
            int enchant = file.getInt("boots." + key + ".enchant");
            int level = file.getInt("boots." + key + ".level");
            int levelTool = data.getLevelBoots(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("boots." + key + ".material").toUpperCase()));
            String removeItem = file.getString("boots." + (level - 1) + ".material");


            if (level == levelTool) {

                App.getInstance().getServer().dispatchCommand(App.getInstance().getServer().getConsoleSender(), "minecraft:clear " + player.getName() + " " + removeItem + " 1");


                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(name);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, enchant, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
                itemMeta.setUnbreakable(true);
                item.setItemMeta(itemMeta);

                player.getInventory().addItem(item);
                e.setCancelled(true);

                Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        data.addLevelBoots(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradeSword(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getConfig();

        manager.getConfig().getConfigurationSection("sword").getKeys(false).forEach(key -> {
            String name = file.getString("sword." + key + ".name");
            int enchant = file.getInt("sword." + key + ".enchant");
            int level = file.getInt("sword." + key + ".level");
            int levelTool = data.getLevelSword(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("sword." + key + ".material").toUpperCase()));
            String removeItem = file.getString("sword." + (level - 1) + ".material");


            if (level == levelTool) {

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
                        data.addLevelSword(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradePickaxe(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getConfig();

        manager.getConfig().getConfigurationSection("pickaxe").getKeys(false).forEach(key -> {
            String name = file.getString("pickaxe." + key + ".name");
            int enchant = file.getInt("pickaxe." + key + ".enchant");
            int level = file.getInt("pickaxe." + key + ".level");
            int levelTool = data.getLevelPickaxe(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("pickaxe." + key + ".material").toUpperCase()));
            String removeItem = file.getString("pickaxe." + (level - 1) + ".material");


            if (level == levelTool) {

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
                        data.addLevelPickaxe(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradeShovel(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getConfig();

        manager.getConfig().getConfigurationSection("shovel").getKeys(false).forEach(key -> {
            String name = file.getString("shovel." + key + ".name");
            int enchant = file.getInt("shovel." + key + ".enchant");
            int level = file.getInt("shovel." + key + ".level");
            int levelTool = data.getLevelShovel(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("shovel." + key + ".material").toUpperCase()));
            String removeItem = file.getString("shovel." + (level - 1) + ".material");


            if (level == levelTool) {

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
                        data.addLevelShovel(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradeAxe(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getConfig();

        manager.getConfig().getConfigurationSection("axe").getKeys(false).forEach(key -> {
            String name = file.getString("axe." + key + ".name");
            int enchant = file.getInt("axe." + key + ".enchant");
            int level = file.getInt("axe." + key + ".level");
            int levelTool = data.getLevelAxe(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("axe." + key + ".material").toUpperCase()));
            String removeItem = file.getString("axe." + (level - 1) + ".material");


            if (level == levelTool) {

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
                        data.addLevelAxe(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }

    void upgradeShears(InventoryClickEvent e, Player player) {
        FileConfiguration file = manager.getConfig();

        manager.getConfig().getConfigurationSection("shears").getKeys(false).forEach(key -> {
            String name = file.getString("shears." + key + ".name");
            int enchant = file.getInt("shears." + key + ".enchant");
            int level = file.getInt("shears." + key + ".level");
            int levelTool = data.getLevelShears(player.getUniqueId());
            ItemStack item = new ItemStack(Material.getMaterial(file.getString("shears." + key + ".material").toUpperCase()));
            String removeItem = file.getString("shears." + (level - 1) + ".material");


            if (level == levelTool) {

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
                        data.addLevelShears(player.getUniqueId(), 1);
                        player.chat("/shop");
                    }
                }, 1);
            }
        });
    }
}
