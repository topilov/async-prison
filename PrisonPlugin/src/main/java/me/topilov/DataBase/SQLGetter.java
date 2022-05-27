package me.topilov.DataBase;

import me.topilov.App;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter {

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
}
