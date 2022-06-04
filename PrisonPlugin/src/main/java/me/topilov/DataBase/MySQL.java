package me.topilov.DataBase;

import me.topilov.App;
import me.topilov.utils.ConfigManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private Connection connection;

    public boolean isConnected() {
        return (connection != null);
    }

    ConfigManager manager = App.getInstance().manager;

    public void connect() throws ClassNotFoundException, SQLException {
        if (!isConnected()) {
            String password = manager.getDataBaseConfig().getString("database.password");
            String username = manager.getDataBaseConfig().getString("database.username");
            String database = manager.getDataBaseConfig().getString("database.databaseName");
            String port = manager.getDataBaseConfig().getString("database.port");
            String host = manager.getDataBaseConfig().getString("database.host");
            connection = DriverManager.getConnection("jdbc:mysql://" +
                            host + ":" + port + "/" + database + "?useSSL=false",
                    username, password);
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}