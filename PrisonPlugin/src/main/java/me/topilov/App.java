package me.topilov;

import me.topilov.DataBase.MySQL;
import me.topilov.DataBase.SQLGetter;
import me.topilov.Events.JoinEvent;
import me.topilov.GUI.MenuGUI;
import me.topilov.GUI.MinesGUI;
import me.topilov.GUI.ShopGUI;
import me.topilov.GUIEvent.MenuGUIListener;
import me.topilov.GUIEvent.MinesGUIListener;
import me.topilov.GUIEvent.ShopGUIListener;
import me.topilov.utils.MinesManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class App extends JavaPlugin {

    private static App instance;
    public MinesManager manager;
    public MySQL SQL;
    public SQLGetter data;

    @Override
    public void onEnable() {
        instance = this;
        manager = new MinesManager(this);
        setupDatabase();
        setupCommands();
        setupEvents();

    }

    @Override
    public void onDisable() {
        SQL.disconnect();
    }

    void setupCommands() {
        getServer().getPluginCommand("menu").setExecutor(new MenuGUI());
        getServer().getPluginCommand("mines").setExecutor(new MinesGUI());
        getServer().getPluginCommand("mine").setExecutor(new MinesGUI());
        getServer().getPluginCommand("shop").setExecutor(new ShopGUI());
    }

    void setupEvents() {
        getServer().getPluginManager().registerEvents(new MinesGUIListener(), this);
        getServer().getPluginManager().registerEvents(new MenuGUIListener(), this);
        getServer().getPluginManager().registerEvents(new ShopGUIListener(), this);
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
            data.createTableTools();
            getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        }
    }

    public static App getInstance() {
        return instance;
    }
}
