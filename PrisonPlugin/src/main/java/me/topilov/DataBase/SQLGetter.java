package me.topilov.DataBase;

import me.topilov.App;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter {

    public void createTablePlayers() {
        PreparedStatement ps;
        try {
            ps = App.getInstance().SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS players "
                    + "(name VARCHAR(100),uuid VARCHAR(100),level INT(100) NOT NULL DEFAULT '1',blocks INT(100) NOT NULL DEFAULT '0',kills INT(100) NOT NULL DEFAULT '0',booster_Money DOUBLE NOT NULL DEFAULT '1.0',booster_Blocks DOUBLE NOT NULL DEFAULT '1.0',PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            if (!exists(uuid)) {
                PreparedStatement ps2 = App.getInstance().SQL.getConnection().prepareStatement("INSERT IGNORE INTO players"
                        + " (name,uuid) VALUES (?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(UUID uuid){
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT * FROM players WHERE uuid=?");
            ps.setString(1, uuid.toString());

            ResultSet results = ps.executeQuery();
            if(results.next()) {
                // player is found
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTableGlobalBoosters() {
        PreparedStatement ps;
        try {
            ps = App.getInstance().SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS global_boosters "
                    + "(uuid VARCHAR(100),booster_Money DOUBLE NOT NULL DEFAULT '0.0',booster_Blocks DOUBLE NOT NULL DEFAULT '0.0',PRIMARY KEY (UUID))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createGlobalBoosters(Server server) {
        try {
            String serverID = App.getInstance().getName();
            if (!existsGlobalBooster(serverID)) {
                PreparedStatement ps3 = App.getInstance().SQL.getConnection().prepareStatement("INSERT IGNORE INTO global_boosters"
                        + " (uuid) VALUES (?)");
                ps3.setString(1, serverID);
                ps3.executeUpdate();

                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existsGlobalBooster(String server){
        try {
            PreparedStatement ps4 = App.getInstance().SQL.getConnection().prepareStatement("SELECT * FROM global_boosters WHERE uuid=?");
            ps4.setString(1, server);

            ResultSet results = ps4.executeQuery();
            if(results.next()) {
                // player is found
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTableLocalBoosters() {
        PreparedStatement ps;
        try {
            ps = App.getInstance().SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS local_boosters "
                    + "(name VARCHAR(100),uuid VARCHAR(100),booster_Money DOUBLE NOT NULL DEFAULT '0.0',booster_Blocks DOUBLE NOT NULL DEFAULT '0.0',PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createLocalBoosters(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            if (!existsLocalBoosters(uuid)) {
                PreparedStatement ps2 = App.getInstance().SQL.getConnection().prepareStatement("INSERT IGNORE INTO local_boosters"
                        + " (name,uuid) VALUES (?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.executeUpdate();

                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existsLocalBoosters(UUID uuid){
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT * FROM local_boosters WHERE uuid=?");
            ps.setString(1, uuid.toString());

            ResultSet results = ps.executeQuery();
            if(results.next()) {
                // player is found
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTableTools() {
        PreparedStatement ps;
        try {
            ps = App.getInstance().SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS tools "
                    + "(name VARCHAR(100),uuid VARCHAR(100),helmet INT(100) NOT NULL DEFAULT '0',chestplate INT(100) NOT NULL DEFAULT '0',leggings INT(100) NOT NULL DEFAULT '0',boots INT(100) NOT NULL DEFAULT '0',sword INT(100) NOT NULL DEFAULT '0',pickaxe INT(100) NOT NULL DEFAULT '0',shovel INT(100) NOT NULL DEFAULT '0',axe INT(100) NOT NULL DEFAULT '0',shears INT(100) NOT NULL DEFAULT '0', PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTools(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            if (!existsTools(uuid)) {
                PreparedStatement ps2 = App.getInstance().SQL.getConnection().prepareStatement("INSERT IGNORE INTO tools"
                        + " (name,uuid) VALUES (?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existsTools(UUID uuid){
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT * FROM tools WHERE uuid=?");
            ps.setString(1, uuid.toString());

            ResultSet results = ps.executeQuery();
            if(results.next()) {
                // player is found
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTableActiveBoosters() {
        PreparedStatement ps;
        try {
            ps = App.getInstance().SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS active_boosters "
                    + "(name INT(100),booster_Money INT(100) NOT NULL DEFAULT '0',progress_Money DOUBLE NOT NULL DEFAULT '1.0',player_Money VARCHAR(100) NOT NULL DEFAULT 'NONE',booster_Blocks INT(100) NOT NULL DEFAULT '0',progress_Blocks DOUBLE NOT NULL DEFAULT '1.0',player_Blocks VARCHAR(100) NOT NULL DEFAULT 'NONE',PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createActiveBoosters(int number) {
        try {
            if (!existsActiveBooster(number)) {
                PreparedStatement ps3 = App.getInstance().SQL.getConnection().prepareStatement("INSERT IGNORE INTO active_boosters"
                        + " (name) VALUES (?)");
                ps3.setInt(1, number);
                ps3.executeUpdate();

                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existsActiveBooster(int number){
        try {
            PreparedStatement ps4 = App.getInstance().SQL.getConnection().prepareStatement("SELECT * FROM active_boosters WHERE name=?");
            ps4.setInt(1, number);

            ResultSet results = ps4.executeQuery();
            if(results.next()) {
                // player is found
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTableThanks() {
        PreparedStatement ps;
        try {
            ps = App.getInstance().SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS thanks "
                    + "(name VARCHAR(100),uuid VARCHAR(100),thx_money_1 INT(1) NOT NULL DEFAULT '0',thx_money_2 INT(1) NOT NULL DEFAULT '0',thx_blocks_1 INT(1) NOT NULL DEFAULT '0',thx_blocks_2 INT(1) NOT NULL DEFAULT '0',PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createThanks(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            if (!existsThanks(uuid)) {
                PreparedStatement ps3 = App.getInstance().SQL.getConnection().prepareStatement("INSERT IGNORE INTO thanks"
                        + " (name,uuid) VALUES (?,?)");
                ps3.setString(1, player.getName());
                ps3.setString(2, uuid.toString());
                ps3.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existsThanks(UUID uuid){
        try {
            PreparedStatement ps4 = App.getInstance().SQL.getConnection().prepareStatement("SELECT * FROM thanks WHERE uuid=?");
            ps4.setString(1, uuid.toString());

            ResultSet results = ps4.executeQuery();
            if(results.next()) {
                // player is found
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addLevelPickaxe(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET pickaxe=? WHERE uuid=?");
            ps.setInt(1, (getLevelPickaxe(uuid) +  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLevelPickaxe(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET pickaxe=? WHERE uuid=?");
            ps.setInt(1, (getLevelPickaxe(uuid) -  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLevelPickaxe(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET pickaxe=? WHERE uuid=?");
            ps.setInt(1, (level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLevelPickaxe(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT pickaxe FROM tools WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int level = 0;
            if (rs.next()) {
                level = rs.getInt("pickaxe");
                return level;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addLevelHelmet(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET helmet=? WHERE uuid=?");
            ps.setInt(1, (getLevelHelmet(uuid) +  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLevelHelmet(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET helmet=? WHERE uuid=?");
            ps.setInt(1, (getLevelPickaxe(uuid) -  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLevelHelmet(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET helmet=? WHERE uuid=?");
            ps.setInt(1, (level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLevelHelmet(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT helmet FROM tools WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int helmet = 0;
            if (rs.next()) {
                helmet = rs.getInt("helmet");
                return helmet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addLevelChestPlate(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET chestplate=? WHERE uuid=?");
            ps.setInt(1, (getLevelChestPlate(uuid) +  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLevelChestPlate(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET chestplate=? WHERE uuid=?");
            ps.setInt(1, (getLevelChestPlate(uuid) -  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLevelChestPlate(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET chestplate=? WHERE uuid=?");
            ps.setInt(1, (level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLevelChestPlate(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT chestplate FROM tools WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int chestplate = 0;
            if (rs.next()) {
                chestplate = rs.getInt("chestplate");
                return chestplate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addLevelLeggings(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET leggings=? WHERE uuid=?");
            ps.setInt(1, (getLevelLeggings(uuid) +  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLevelLeggings(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET leggings=? WHERE uuid=?");
            ps.setInt(1, (getLevelLeggings(uuid) -  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLevelLeggings(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET leggings=? WHERE uuid=?");
            ps.setInt(1, (level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLevelLeggings(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT leggings FROM tools WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int leggings = 0;
            if (rs.next()) {
                leggings = rs.getInt("leggings");
                return leggings;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addLevelBoots(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET boots=? WHERE uuid=?");
            ps.setInt(1, (getLevelBoots(uuid) +  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLevelBoots(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET boots=? WHERE uuid=?");
            ps.setInt(1, (getLevelBoots(uuid) -  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLevelBoots(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET boots=? WHERE uuid=?");
            ps.setInt(1, (level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLevelBoots(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT boots FROM tools WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int boots = 0;
            if (rs.next()) {
                boots = rs.getInt("boots");
                return boots;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addLevelSword(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET sword=? WHERE uuid=?");
            ps.setInt(1, (getLevelSword(uuid) +  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLevelSword(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET sword=? WHERE uuid=?");
            ps.setInt(1, (getLevelSword(uuid) -  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLevelSword(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET sword=? WHERE uuid=?");
            ps.setInt(1, (level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLevelSword(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT sword FROM tools WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int sword = 0;
            if (rs.next()) {
                sword = rs.getInt("sword");
                return sword;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addLevelShovel(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET shovel=? WHERE uuid=?");
            ps.setInt(1, (getLevelShovel(uuid) +  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLevelShovel(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET shovel=? WHERE uuid=?");
            ps.setInt(1, (getLevelShovel(uuid) -  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLevelShovel(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET shovel=? WHERE uuid=?");
            ps.setInt(1, (level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLevelShovel(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT shovel FROM tools WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int shovel = 0;
            if (rs.next()) {
                shovel = rs.getInt("shovel");
                return shovel;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addLevelAxe(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET axe=? WHERE uuid=?");
            ps.setInt(1, (getLevelAxe(uuid) +  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLevelAxe(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET axe=? WHERE uuid=?");
            ps.setInt(1, (getLevelAxe(uuid) -  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLevelAxe(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET axe=? WHERE uuid=?");
            ps.setInt(1, (level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLevelAxe(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT axe FROM tools WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int axe = 0;
            if (rs.next()) {
                axe = rs.getInt("axe");
                return axe;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addLevelShears(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET shears=? WHERE uuid=?");
            ps.setInt(1, (getLevelShears(uuid) +  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLevelShears(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET shears=? WHERE uuid=?");
            ps.setInt(1, (getLevelShears(uuid) -  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLevelShears(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE tools SET shears=? WHERE uuid=?");
            ps.setInt(1, (level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLevelShears(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT shears FROM tools WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int shears = 0;
            if (rs.next()) {
                shears = rs.getInt("shears");
                return shears;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addLevel(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE players SET level=? WHERE uuid=?");
            ps.setInt(1, (getLevel(uuid) +  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLevel(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE players SET level=? WHERE uuid=?");
            ps.setInt(1, (getLevel(uuid) -  level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLevel(UUID uuid, int level) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE players SET level=? WHERE uuid=?");
            ps.setInt(1, (level));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLevel(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT level FROM players WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int level = 0;
            if (rs.next()) {
                level = rs.getInt("level");
                return level;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addBlocks(UUID uuid, int blocks) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE players SET blocks=? WHERE uuid=?");
            ps.setInt(1, (getBlocks(uuid) +  blocks));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeBlocks(UUID uuid, int blocks) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE players SET blocks=? WHERE uuid=?");
            ps.setInt(1, (getBlocks(uuid) -  blocks));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setBlocks(UUID uuid, int blocks) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE players SET blocks=? WHERE uuid=?");
            ps.setInt(1, (blocks));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getBlocks(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT blocks FROM players WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int blocks = 0;
            if (rs.next()) {
                blocks = rs.getInt("blocks");
                return blocks;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addGlobalMoneyBooster(String serverID, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE global_boosters SET booster_Money=? WHERE uuid=?");
            ps.setDouble(1, (getGlobalMoneyBooster(serverID) +  booster));
            ps.setString(2, serverID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeGlobalMoneyBooster(String serverID, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE global_boosters SET booster_Money=? WHERE uuid=?");
            ps.setDouble(1, (getGlobalMoneyBooster(serverID) -  booster));
            ps.setString(2, serverID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setGlobalMoneyBooster(String serverID, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE global_boosters SET booster_Money=? WHERE uuid=?");
            ps.setDouble(1, (booster));
            ps.setString(2, serverID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getGlobalMoneyBooster(String serverID) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT booster_Money FROM global_boosters WHERE uuid=?");
            ps.setString(1, serverID);
            ResultSet rs = ps.executeQuery();
            double booster_Money = 0;
            if (rs.next()) {
                booster_Money = rs.getDouble("booster_Money");
                return booster_Money;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addGlobalBlocksBooster(String serverID, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE global_boosters SET booster_blocks=? WHERE uuid=?");
            ps.setDouble(1, (getGlobalBlocksBooster(serverID) +  booster));
            ps.setString(2,serverID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeGlobalBlocksBooster(String serverID, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE global_boosters SET booster_blocks=? WHERE uuid=?");
            ps.setDouble(1, (getGlobalBlocksBooster(serverID) -  booster));
            ps.setString(2, serverID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setGlobalBlocksBooster(String serverID, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE global_boosters SET booster_blocks=? WHERE uuid=?");
            ps.setDouble(1, (booster));
            ps.setString(2, serverID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getGlobalBlocksBooster(String serverID) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT booster_blocks FROM global_boosters WHERE uuid=?");
            ps.setString(1, serverID);
            ResultSet rs = ps.executeQuery();
            double booster_Blocks = 0;
            if (rs.next()) {
                booster_Blocks = rs.getDouble("booster_Blocks");
                return booster_Blocks;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addLocalMoneyBooster(UUID uuid, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE local_boosters SET booster_Money=? WHERE uuid=?");
            ps.setDouble(1, (getLocalMoneyBooster(uuid) +  booster));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLocalMoneyBooster(UUID uuid, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE local_boosters SET booster_Money=? WHERE uuid=?");
            ps.setDouble(1, (getLocalMoneyBooster(uuid) -  booster));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLocalMoneyBooster(UUID uuid, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE local_boosters SET booster_Money=? WHERE uuid=?");
            ps.setDouble(1, (booster));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getLocalMoneyBooster(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT booster_Money FROM local_boosters WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            double booster_Money = 0;
            if (rs.next()) {
                booster_Money = rs.getDouble("booster_Money");
                return booster_Money;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addLocalBlocksBooster(UUID uuid, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE local_boosters SET booster_Blocks=? WHERE uuid=?");
            ps.setDouble(1, (getLocalBlocksBooster(uuid) +  booster));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeLocalBlocksBooster(UUID uuid, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE local_boosters SET booster_Blocks=? WHERE uuid=?");
            ps.setDouble(1, (getLocalBlocksBooster(uuid) -  booster));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLocalBlocksBooster(UUID uuid, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE local_boosters SET booster_Blocks=? WHERE uuid=?");
            ps.setDouble(1, (booster));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getLocalBlocksBooster(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT booster_Blocks FROM local_boosters WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            double booster_Blocks = 0;
            if (rs.next()) {
                booster_Blocks = rs.getDouble("booster_Blocks");
                return booster_Blocks;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addMoneyBooster(UUID uuid, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE players SET booster_Money=? WHERE uuid=?");
            ps.setDouble(1, (getMoneyBooster(uuid) +  booster));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeMoneyBooster(UUID uuid, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE players SET booster_Money=? WHERE uuid=?");
            ps.setDouble(1, (getMoneyBooster(uuid) -  booster));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMoneyBooster(UUID uuid, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE players SET booster_Money=? WHERE uuid=?");
            ps.setDouble(1, (booster));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getMoneyBooster(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT booster_Money FROM players WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            double booster_Money = 0;
            if (rs.next()) {
                booster_Money = rs.getDouble("booster_Money");
                return booster_Money;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addBlocksBooster(UUID uuid, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE players SET booster_Blocks=? WHERE uuid=?");
            ps.setDouble(1, (getBlocksBooster(uuid) +  booster));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeBlocksBooster(UUID uuid, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE players SET booster_Blocks=? WHERE uuid=?");
            ps.setDouble(1, (getBlocksBooster(uuid) -  booster));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setBlocksBooster(UUID uuid, double booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE players SET booster_Blocks=? WHERE uuid=?");
            ps.setDouble(1, (booster));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getBlocksBooster(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT booster_Blocks FROM players WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            double booster_Blocks = 0;
            if (rs.next()) {
                booster_Blocks = rs.getDouble("booster_Blocks");
                return booster_Blocks;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setActiveMoneyBooster(int name, int booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE active_boosters SET booster_Money=? WHERE name=?");
            ps.setInt(1, (booster));
            ps.setInt(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addActiveMoneyBooster(int name, int booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE active_boosters SET booster_Money=? WHERE name=?");
            ps.setInt(1, (getActiveMoneyBooster(name) + booster));
            ps.setInt(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getActiveMoneyBooster(int name) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT booster_Money FROM active_boosters WHERE name=?");
            ps.setInt(1, name);
            ResultSet rs = ps.executeQuery();
            int booster_Money = 0;
            if (rs.next()) {
                booster_Money = rs.getInt("booster_Money");
                return booster_Money;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setProgressMoneyBooster(int name, double progress) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE active_boosters SET progress_Money=? WHERE name=?");
            ps.setDouble(1, (progress));
            ps.setInt(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProgressMoneyBooster(int name, double progress) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE active_boosters SET progress_Money=? WHERE name=?");
            ps.setDouble(1, (getProgressMoneyBooster(name) + progress));
            ps.setInt(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getProgressMoneyBooster(int name) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT progress_Money FROM active_boosters WHERE name=?");
            ps.setInt(1, name);
            ResultSet rs = ps.executeQuery();
            double progress_Money = 0;
            if (rs.next()) {
                progress_Money = rs.getDouble("progress_Money");
                return progress_Money;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setActiveBlocksBooster(int name, int booster) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE active_boosters SET booster_Blocks=? WHERE name=?");
            ps.setInt(1, (booster));
            ps.setInt(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getActiveBlocksBooster(int name) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT booster_Blocks FROM active_boosters WHERE name=?");
            ps.setInt(1, name);
            ResultSet rs = ps.executeQuery();
            double booster_Blocks = 0;
            if (rs.next()) {
                booster_Blocks = rs.getDouble("booster_Blocks");
                return booster_Blocks;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setProgressBlocksBooster(int name, double progress) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE active_boosters SET progress_Blocks=? WHERE name=?");
            ps.setDouble(1, (progress));
            ps.setInt(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProgressBlocksBooster(int name, double progress) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE active_boosters SET progress_Blocks=? WHERE name=?");
            ps.setDouble(1, (getProgressBlocksBooster(name) + progress));
            ps.setInt(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getProgressBlocksBooster(int name) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT progress_Blocks FROM active_boosters WHERE name=?");
            ps.setInt(1, name);
            ResultSet rs = ps.executeQuery();
            double progress_Blocks = 0;
            if (rs.next()) {
                progress_Blocks = rs.getDouble("progress_Blocks");
                return progress_Blocks;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setPlayerMoneyBooster(int name, String player) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE active_boosters SET player_Money=? WHERE name=?");
            ps.setString(1, (player));
            ps.setInt(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getPlayerMoneyBooster(int name) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT player_Money FROM active_boosters WHERE name=?");
            ps.setInt(1, name);
            ResultSet rs = ps.executeQuery();
            String player_Money = null;
            if (rs.next()) {
                player_Money = rs.getString("player_Money");
                return player_Money;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setPlayerBlocksBooster(int name, String player) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE active_boosters SET player_Blocks=? WHERE name=?");
            ps.setString(1, (player));
            ps.setInt(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getPlayerBlocksBooster(int name) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT player_Blocks FROM active_boosters WHERE name=?");
            ps.setInt(1, name);
            ResultSet rs = ps.executeQuery();
            String player_Blocks = null;
            if (rs.next()) {
                player_Blocks = rs.getString("player_Blocks");
                return player_Blocks;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setThxMoney_1(UUID uuid, int thanks) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE thanks SET thx_money_1=? WHERE uuid=?");
            ps.setInt(1, (thanks));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getThxMoney_1(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT thx_money_1 FROM thanks WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int thanks = 0;
            if (rs.next()) {
                thanks = rs.getInt("thx_money_1");
                return thanks;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setThxMoney_2(UUID uuid, int thanks) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE thanks SET thx_money_2=? WHERE uuid=?");
            ps.setInt(1, (thanks));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getThxMoney_2(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT thx_money_2 FROM thanks WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int thanks = 0;
            if (rs.next()) {
                thanks = rs.getInt("thx_money_2");
                return thanks;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setThxBlocks_1(UUID uuid, int thanks) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE thanks SET thx_blocks_1=? WHERE uuid=?");
            ps.setInt(1, (thanks));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getThxBlocks_1(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT thx_blocks_1 FROM thanks WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int thanks = 0;
            if (rs.next()) {
                thanks = rs.getInt("thx_blocks_1");
                return thanks;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setThxBlocks_2(UUID uuid, int thanks) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("UPDATE thanks SET thx_blocks_2=? WHERE uuid=?");
            ps.setInt(1, (thanks));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getThxBlocks_2(UUID uuid) {
        try {
            PreparedStatement ps = App.getInstance().SQL.getConnection().prepareStatement("SELECT thx_blocks_2 FROM thanks WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int thanks = 0;
            if (rs.next()) {
                thanks = rs.getInt("thx_blocks_2");
                return thanks;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
