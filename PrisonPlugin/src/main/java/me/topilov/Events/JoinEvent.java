package me.topilov.Events;

import me.topilov.App;
import me.topilov.DataBase.SQLGetter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    SQLGetter data = App.getInstance().data;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        data.createTools(player);
    }
}
