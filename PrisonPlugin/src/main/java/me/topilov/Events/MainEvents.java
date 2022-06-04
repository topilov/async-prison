package me.topilov.Events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class MainEvents implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.COMPASS) {
            e.getPlayer().chat("/menu");
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (!(e.getPlayer().getGameMode() == GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSlime(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        Location location = player.getLocation().clone();
        location.add(0, -1, 0);

        if (location.getBlock().getType() == Material.SLIME_BLOCK) {
            player.setVelocity(player.getLocation().getDirection().multiply(0).setY(6));
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.setKeepInventory(true);
    }
}
