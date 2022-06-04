package me.topilov.Needs;

import me.topilov.App;
import me.topilov.utils.ConfigManager;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

public class ToiletNeed implements Listener {

    ConfigManager manager = App.getInstance().manager;
    FileConfiguration messages = manager.getMessagesConfig();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (e.getClickedBlock() == null) {
            return;
        }

        if (e.getClickedBlock().getType() == Material.LEVER) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (player.hasPotionEffect(PotionEffectType.SLOW)) {
                    player.removePotionEffect(PotionEffectType.SLOW);
                    player.sendMessage("" + messages.getString("messages.do_piss"));
                }
            }
        }

        if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.PAPER) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK ) {
                if (player.hasPotionEffect(PotionEffectType.SLOW)) {
                    player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                    player.removePotionEffect(PotionEffectType.SLOW);
                    player.sendMessage("" + messages.getString("messages.do_piss_with_paper"));
                }
            }
        }
    }
}
