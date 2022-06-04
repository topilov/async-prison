package me.topilov.Events;

import me.topilov.App;
import me.topilov.DataBase.SQLGetter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    SQLGetter data = App.getInstance().data;

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        removeBoosters(player);
    }

    private void removeBoosters(Player player) {
        double globalMoneyBooster = data.getGlobalMoneyBooster(App.getInstance().getName());
        double globalBlocksBooster = data.getGlobalBlocksBooster(App.getInstance().getName());

        data.removeMoneyBooster(player.getUniqueId(), globalMoneyBooster);
        data.removeBlocksBooster(player.getUniqueId(), globalBlocksBooster);
    }
}
