package me.topilov.utils;

import me.topilov.App;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class ConfigManager {

    private App plugin;
    private File minesConfigFile = null;
    private FileConfiguration minesConfig = null;
    private File shopConfigFile = null;
    private FileConfiguration shopConfig = null;
    private File levelConfigFile = null;
    private FileConfiguration levelConfig = null;
    private File blocksConfigFile = null;
    private FileConfiguration blocksConfig = null;
    private File dataBaseConfigFile = null;
    private FileConfiguration dataBaseConfig = null;
    private File messagesConfigFile = null;
    private FileConfiguration messagesConfig = null;
    private File shopSettingsConfigFile = null;
    private FileConfiguration shopSettingsConfig = null;

    public ConfigManager(App plugin) {
        this.plugin = plugin;
        saveDefaultMinesConfig();
        saveDefaultShopConfig();
        saveDefaultLevelConfig();
        saveDefaultBlocksConfig();
    }

    public void reloadMinesConfig() {
        if (this.minesConfigFile == null) {
            this.minesConfigFile = new File(this.plugin.getDataFolder(), "mines.yml");
        }

        this.minesConfig = YamlConfiguration.loadConfiguration(this.minesConfigFile);

        InputStream defaultStream = this.plugin.getResource("mines.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.minesConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getMinesConfig() {
        if (this.minesConfig == null) {
            reloadMinesConfig();
        }

        return this.minesConfig;
    }

    public void saveMinesConfig() {
        if (this.minesConfigFile == null || this.minesConfig == null) {
            return;
        }

        try {
            this.getMinesConfig().save(this.minesConfigFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.minesConfigFile, e);
        }
    }

    public void saveDefaultMinesConfig() {
        if (this.minesConfigFile == null) {
            this.minesConfigFile = new File(this.plugin.getDataFolder(), "mines.yml");

            if (!this.minesConfigFile.exists()) {
                this.plugin.saveResource("mines", false);
            }
        }
    }

    public void reloadShopConfig() {
        if (this.shopConfigFile == null) {
            this.shopConfigFile = new File(this.plugin.getDataFolder(), "shop.yml");
        }

        this.shopConfig = YamlConfiguration.loadConfiguration(this.shopConfigFile);

        InputStream defaultStream = this.plugin.getResource("shop.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.shopConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getShopConfig() {
        if (this.shopConfig == null) {
            reloadShopConfig();
        }

        return this.shopConfig;
    }

    public void saveShopConfig() {
        if (this.shopConfigFile == null || this.shopConfig == null) {
            return;
        }

        try {
            this.getShopConfig().save(this.shopConfigFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.shopConfigFile, e);
        }
    }

    public void saveDefaultShopConfig() {
        if (this.shopConfigFile == null) {
            this.shopConfigFile = new File(this.plugin.getDataFolder(), "shop.yml");

            if (!this.shopConfigFile.exists()) {
                this.plugin.saveResource("shop", false);
            }
        }
    }

    public void reloadLevelConfig() {
        if (this.levelConfigFile == null) {
            this.levelConfigFile = new File(this.plugin.getDataFolder(), "level.yml");
        }

        this.levelConfig = YamlConfiguration.loadConfiguration(this.levelConfigFile);

        InputStream defaultStream = this.plugin.getResource("level.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.levelConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getLevelConfig() {
        if (this.levelConfig == null) {
            reloadLevelConfig();
        }

        return this.levelConfig;
    }

    public void saveLevelConfig() {
        if (this.levelConfigFile == null || this.levelConfig == null) {
            return;
        }

        try {
            this.getLevelConfig().save(this.levelConfigFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.levelConfigFile, e);
        }
    }

    public void saveDefaultLevelConfig() {
        if (this.levelConfigFile == null) {
            this.levelConfigFile = new File(this.plugin.getDataFolder(), "level.yml");

            if (!this.levelConfigFile.exists()) {
                this.plugin.saveResource("level", false);
            }
        }
    }

    public void reloadBlocksConfig() {
        if (this.blocksConfigFile == null) {
            this.blocksConfigFile = new File(this.plugin.getDataFolder(), "blocks.yml");
        }

        this.blocksConfig = YamlConfiguration.loadConfiguration(this.blocksConfigFile);

        InputStream defaultStream = this.plugin.getResource("blocks.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.blocksConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getBlocksConfig() {
        if (this.blocksConfig == null) {
            reloadBlocksConfig();
        }

        return this.blocksConfig;
    }

    public void saveBlocksConfig() {
        if (this.blocksConfigFile == null || this.blocksConfig == null) {
            return;
        }

        try {
            this.getBlocksConfig().save(this.blocksConfigFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.blocksConfigFile, e);
        }
    }

    public void saveDefaultBlocksConfig() {
        if (this.blocksConfigFile == null) {
            this.blocksConfigFile = new File(this.plugin.getDataFolder(), "blocks.yml");

            if (!this.blocksConfigFile.exists()) {
                this.plugin.saveResource("blocks", false);
            }
        }
    }

    public void reloadDataBaseConfig() {
        if (this.dataBaseConfigFile == null) {
            this.dataBaseConfigFile = new File(this.plugin.getDataFolder(), "database.yml");
        }

        this.dataBaseConfig = YamlConfiguration.loadConfiguration(this.dataBaseConfigFile);

        InputStream defaultStream = this.plugin.getResource("database.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataBaseConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getDataBaseConfig() {
        if (this.dataBaseConfig == null) {
            reloadDataBaseConfig();
        }

        return this.dataBaseConfig;
    }

    public void saveDataBaseConfig() {
        if (this.dataBaseConfigFile == null || this.dataBaseConfig == null) {
            return;
        }

        try {
            this.getDataBaseConfig().save(this.dataBaseConfigFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.dataBaseConfigFile, e);
        }
    }

    public void saveDefaultDataBaseConfig() {
        if (this.dataBaseConfigFile == null) {
            this.dataBaseConfigFile = new File(this.plugin.getDataFolder(), "database.yml");

            if (!this.dataBaseConfigFile.exists()) {
                this.plugin.saveResource("database", false);
            }
        }
    }

    public void reloadMessagesConfig() {
        if (this.messagesConfigFile == null) {
            this.messagesConfigFile = new File(this.plugin.getDataFolder(), "messages.yml");
        }

        this.messagesConfig = YamlConfiguration.loadConfiguration(this.messagesConfigFile);

        InputStream defaultStream = this.plugin.getResource("messages.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.messagesConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getMessagesConfig() {
        if (this.messagesConfig == null) {
            reloadMessagesConfig();
        }

        return this.messagesConfig;
    }

    public void saveMessagesConfig() {
        if (this.messagesConfigFile == null || this.messagesConfig == null) {
            return;
        }

        try {
            this.getMessagesConfig().save(this.messagesConfigFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.messagesConfigFile, e);
        }
    }

    public void saveDefaultMessagesConfig() {
        if (this.messagesConfigFile == null) {
            this.messagesConfigFile = new File(this.plugin.getDataFolder(), "messages.yml");

            if (!this.messagesConfigFile.exists()) {
                this.plugin.saveResource("messages", false);
            }
        }
    }

    public void reloadShopSettingsConfig() {
        if (this.shopSettingsConfigFile == null) {
            this.shopSettingsConfigFile = new File(this.plugin.getDataFolder(), "shopSettings.yml");
        }

        this.shopSettingsConfig = YamlConfiguration.loadConfiguration(this.shopSettingsConfigFile);

        InputStream defaultStream = this.plugin.getResource("shopSettings.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.shopSettingsConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getShopSettingsConfig() {
        if (this.shopSettingsConfig == null) {
            reloadShopSettingsConfig();
        }

        return this.shopSettingsConfig;
    }

    public void saveShopSettingsConfig() {
        if (this.shopSettingsConfigFile == null || this.shopSettingsConfig == null) {
            return;
        }

        try {
            this.getShopSettingsConfig().save(this.shopSettingsConfigFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.shopSettingsConfigFile, e);
        }
    }

    public void saveDefaultShopSettingsConfig() {
        if (this.shopSettingsConfigFile == null) {
            this.shopSettingsConfigFile = new File(this.plugin.getDataFolder(), "shopSettings.yml");

            if (!this.shopSettingsConfigFile.exists()) {
                this.plugin.saveResource("shopSettings", false);
            }
        }
    }
}
