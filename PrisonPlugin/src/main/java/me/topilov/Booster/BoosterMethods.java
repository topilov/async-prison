package me.topilov.Booster;

import me.topilov.App;
import me.topilov.DataBase.SQLGetter;
import org.bukkit.Bukkit;

public class BoosterMethods {

    public static void onDisablePlugin(){
        SQLGetter data = App.getInstance().data;

        double globalMoneyBooster = data.getGlobalMoneyBooster(App.getInstance().getName());
        double globalBlocksBooster = data.getGlobalBlocksBooster(App.getInstance().getName());

        data.setGlobalMoneyBooster(App.getInstance().getName(), 0);
        data.setGlobalBlocksBooster(App.getInstance().getName(), 0);
        data.setActiveMoneyBooster(1, 0);
        data.setActiveMoneyBooster(2, 0);
        data.setActiveBlocksBooster(1, 0);
        data.setActiveBlocksBooster(2, 0);
        data.setPlayerMoneyBooster(1, "NONE");
        data.setPlayerMoneyBooster(2, "NONE");
        data.setPlayerBlocksBooster(1, "NONE");
        data.setPlayerBlocksBooster(2, "NONE");

        Bukkit.getOnlinePlayers().forEach(onlinePlayers -> {
            double localMoneyBooster = data.getLocalMoneyBooster(onlinePlayers.getUniqueId());
            double localBlocksBooster = data.getLocalBlocksBooster(onlinePlayers.getUniqueId());

            data.removeMoneyBooster(onlinePlayers.getUniqueId(), localMoneyBooster);
            data.removeBlocksBooster(onlinePlayers.getUniqueId(), localBlocksBooster);
            data.removeMoneyBooster(onlinePlayers.getUniqueId(), globalMoneyBooster);
            data.removeBlocksBooster(onlinePlayers.getUniqueId(), globalBlocksBooster);

            data.setLocalMoneyBooster(onlinePlayers.getUniqueId(), 0);
            data.setLocalBlocksBooster(onlinePlayers.getUniqueId(), 0);
        });
    }
}
