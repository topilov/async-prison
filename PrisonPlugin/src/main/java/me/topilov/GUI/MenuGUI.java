package me.topilov.GUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class MenuGUI implements CommandExecutor {

    public Inventory menuInv = Bukkit.getServer().createInventory(null, 45, "§7Меню");

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        if(cmd.getName().equalsIgnoreCase("menu")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.DARK_RED + "You cannot use this");
                return true;
            }
            Player player = (Player) sender;

            addItemsToInventory();
            player.openInventory(menuInv);
        }
        return true;
    }









    void addItemsToInventory() {
        ItemStack upgrades = new ItemStack(Material.GLOWSTONE_DUST);
        ItemMeta itemMeta_upgrades = upgrades.getItemMeta();
        ArrayList<String> lore_upgrades = new ArrayList<>();
        itemMeta_upgrades.setDisplayName("§6Прокачки");
        itemMeta_upgrades.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        lore_upgrades.add("§e§l▶ §fНажмите, чтобы открыть меню прокачек");
        itemMeta_upgrades.setLore(lore_upgrades);
        upgrades.setItemMeta(itemMeta_upgrades);
        menuInv.setItem(4, upgrades);

        ItemStack mines = new ItemStack(Material.GOLDEN_PICKAXE);
        ItemMeta itemMeta_mines = mines.getItemMeta();
        ArrayList<String> lore_mines = new ArrayList<>();
        itemMeta_mines.setDisplayName("§6Шахты");
        itemMeta_mines.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        lore_mines.add("§e§l▶ §aЛКМ§f для телепортации на последнюю шахту ");
        lore_mines.add("§e§l▶ §aПКМ§f, чтобы открыть меню шахт ");
        itemMeta_mines.setLore(lore_mines);
        mines.setItemMeta(itemMeta_mines);
        menuInv.setItem(12, mines);

        ItemStack shop = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta_shop = shop.getItemMeta();
        ArrayList<String> lore_shop = new ArrayList<>();
        itemMeta_shop.setDisplayName("§6Магазин предметов");
        itemMeta_shop.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        lore_shop.add("§e§l▶ §fНажмите, чтобы открыть магазин предметов");
        itemMeta_shop.setLore(lore_shop);
        shop.setItemMeta(itemMeta_shop);
        menuInv.setItem(14, shop);

        ItemStack level = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta itemMeta_level = level.getItemMeta();
        ArrayList<String> lore_level = new ArrayList<>();
        itemMeta_level.setDisplayName("§6Поднять уровень");
        itemMeta_level.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        lore_level.add("§e§l▶ §fБлоки:");
        lore_level.add("§e§l▶ §fДеньги");
        itemMeta_level.setLore(lore_level);
        level.setItemMeta(itemMeta_level);
        menuInv.setItem(19, level);

        ItemStack sell = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta itemMeta_sell = sell.getItemMeta();
        ArrayList<String> lore_sell = new ArrayList<>();
        itemMeta_sell.setDisplayName("§6Продать блоки");
        itemMeta_sell.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        lore_shop.add("§e§l▶ §fНажмите, чтобы продать добытые блоки");
        itemMeta_sell.setLore(lore_sell);
        sell.setItemMeta(itemMeta_sell);
        menuInv.setItem(22, sell);

        ItemStack arena = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta itemMeta_arena = arena.getItemMeta();
        ArrayList<String> lore_arena = new ArrayList<>();
        itemMeta_arena.setDisplayName("§6Пвп арена");
        itemMeta_arena.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        lore_arena.add("§e§l▶ §fНажмите для телепортации");
        itemMeta_arena.setLore(lore_arena);
        arena.setItemMeta(itemMeta_arena);
        menuInv.setItem(25, arena);

        ItemStack bosses = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta_bosses = bosses.getItemMeta();
        ArrayList<String> lore_bosses = new ArrayList<>();
        itemMeta_bosses.setDisplayName("§6Боссы и данжи");
        itemMeta_bosses.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        lore_bosses.add("§e§l▶ §fНажмите для телепортации");
        itemMeta_bosses.setLore(lore_bosses);
        bosses.setItemMeta(itemMeta_bosses);
        menuInv.setItem(30, bosses);

        ItemStack collections = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta itemMeta_collections = collections.getItemMeta();
        ArrayList<String> lore_collections = new ArrayList<>();
        itemMeta_collections.setDisplayName("§6Коллекция");
        itemMeta_collections.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        lore_collections.add("§e§l▶ §fНажмите, чтобы открыть");
        itemMeta_collections.setLore(lore_collections);
        collections.setItemMeta(itemMeta_collections);
        menuInv.setItem(32, collections);

        ItemStack pets = new ItemStack(Material.MAGMA_CUBE_SPAWN_EGG);
        ItemMeta itemMeta_pets = pets.getItemMeta();
        ArrayList<String> lore_pets = new ArrayList<>();
        itemMeta_pets.setDisplayName("§6Питомцы");
        itemMeta_pets.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        lore_pets.add("§e§l▶ §fНажмите, чтобы открыть");
        itemMeta_pets.setLore(lore_pets);
        pets.setItemMeta(itemMeta_pets);
        menuInv.setItem(36, pets);

        ItemStack achievements = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta_achievements = achievements.getItemMeta();
        ArrayList<String> lore_achievements = new ArrayList<>();
        itemMeta_achievements.setDisplayName("§6Достижения");
        itemMeta_achievements.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        lore_achievements.add("§e§l▶ §fНажмите, чтобы открыть");
        itemMeta_achievements.setLore(lore_achievements);
        achievements.setItemMeta(itemMeta_achievements);
        menuInv.setItem(40, achievements);

        ItemStack pass = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta_pass = pass.getItemMeta();
        ArrayList<String> lore_pass = new ArrayList<>();
        itemMeta_pass.setDisplayName("§6AsyncPass");
        itemMeta_pass.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS);
        lore_pass.add("§e§l▶ §fНажмите, чтобы открыть");
        itemMeta_pass.setLore(lore_pass);
        pass.setItemMeta(itemMeta_pass);
        menuInv.setItem(44, pass);
    }
}