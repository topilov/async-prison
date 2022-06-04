package me.topilov;

import me.topilov.Booster.BoosterMethods;
import me.topilov.BossBar.BoostersBossBar;
import me.topilov.Commands.*;
import me.topilov.DataBase.MySQL;
import me.topilov.DataBase.SQLGetter;
import me.topilov.Events.JoinEvent;
import me.topilov.Events.MineBreakEvent;
import me.topilov.Events.MainEvents;
import me.topilov.Events.QuitEvent;
import me.topilov.GUI.MenuGUI;
import me.topilov.GUI.MinesGUI;
import me.topilov.GUI.ShopGUI;
import me.topilov.GUIEvent.MenuGUIListener;
import me.topilov.GUIEvent.MinesGUIListener;
import me.topilov.GUIEvent.ShopGUIListener;
import me.topilov.Needs.SchedulerNeeds;
import me.topilov.Needs.ShowerNeed;
import me.topilov.Needs.SleepNeed;
import me.topilov.Needs.ToiletNeed;
import me.topilov.ScoreBoard.ScoreBoard;
import me.topilov.Vault.EconomyCommands;
import me.topilov.utils.ConfigManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class App extends JavaPlugin {

    private static App instance;
    public static Economy economy;
    public ConfigManager manager;
    public MySQL SQL;
    public SQLGetter data;

    @Override
    public void onEnable() {
        instance = this;
        manager = new ConfigManager(this);
        setupDatabase();
        setupEconomy();
        setupCommands();
        setupEvents();

    }

    @Override
    public void onDisable() {
        BoosterMethods.onDisablePlugin();
        SQL.disconnect();
    }

    void setupCommands() {
        getServer().getPluginCommand("menu").setExecutor(new MenuGUI());
        getServer().getPluginCommand("mines").setExecutor(new MinesGUI());
        getServer().getPluginCommand("mine").setExecutor(new MineCMD());
        getServer().getPluginCommand("sell").setExecutor(new SellBlocksCMD());
        getServer().getPluginCommand("shop").setExecutor(new ShopGUI());
        getServer().getPluginCommand("prison").setExecutor(new PrisonConsoleCMD());
        getServer().getPluginCommand("add_mine").setExecutor(new AddMineCMD());
        getServer().getPluginCommand("test").setExecutor(new TestCMD());
        getServer().getPluginCommand("eco").setExecutor(new EconomyCommands());
        getServer().getPluginCommand("thanks").setExecutor(new ThanksCMD());
    }

    void setupEvents() {
        getServer().getPluginManager().registerEvents(new MinesGUIListener(), this);
        getServer().getPluginManager().registerEvents(new MenuGUIListener(), this);
        getServer().getPluginManager().registerEvents(new ShopGUIListener(), this);
        getServer().getPluginManager().registerEvents(new ShowerNeed(), this);
        getServer().getPluginManager().registerEvents(new SleepNeed(), this);
        getServer().getPluginManager().registerEvents(new ToiletNeed(), this);
        getServer().getPluginManager().registerEvents(new SchedulerNeeds(), this);
        getServer().getPluginManager().registerEvents(new BoostersBossBar(), this);
        getServer().getPluginManager().registerEvents(new MineBreakEvent(), this);
        getServer().getPluginManager().registerEvents(new MainEvents(), this);
        getServer().getPluginManager().registerEvents(new PrisonConsoleCMD(), this);
    }

    void setupDatabase(){
        SQL = new MySQL();
        data = new SQLGetter();
        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getLogger().warning("Database not connected");
        }
        if (SQL.isConnected()) {
            Bukkit.getLogger().info("Database is connected!");
            data.createTablePlayers();
            data.createTableTools();
            data.createTableGlobalBoosters();
            data.createTableLocalBoosters();
            data.createTableActiveBoosters();
            data.createTableThanks();
            data.createActiveBoosters(1);
            data.createActiveBoosters(2);
            data.createGlobalBoosters(App.getInstance().getServer());
            getServer().getPluginManager().registerEvents(new JoinEvent(), this);
            getServer().getPluginManager().registerEvents(new QuitEvent(), this);
            getServer().getPluginManager().registerEvents(new ScoreBoard(), this);
        }
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) { economy = economyProvider.getProvider(); }
        return (economy != null);
    }

    public static FileConfiguration getMines() {
        return App.getInstance().manager.getMinesConfig();
    }
    public static void saveMines() {
        App.getInstance().manager.saveMinesConfig();
    }
    public static FileConfiguration getShop() {
        return App.getInstance().manager.getMinesConfig();
    }
    public static void saveShop() {
        App.getInstance().manager.saveMinesConfig();
    }
    public static FileConfiguration getLevel() {
        return App.getInstance().manager.getMinesConfig();
    }
    public static void saveLevel() {
        App.getInstance().manager.saveMinesConfig();
    }
    public static FileConfiguration getBlocks() {
        return App.getInstance().manager.getMinesConfig();
    }
    public static void saveBlocks() {
        App.getInstance().manager.saveMinesConfig();
    }

    public static App getInstance() {
        return instance;
    }
}
