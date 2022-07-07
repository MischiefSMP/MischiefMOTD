package com.mischiefsmp.motd.events;

import com.mischiefsmp.motd.MischiefMOTD;
import com.mischiefsmp.motd.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler ().runTaskLater (MischiefMOTD.getInstance(), () -> {
            for(String msg : MischiefMOTD.getPluginConfig().getMotd()) {
                event.getPlayer().sendMessage(Utils.formatArguments(event.getPlayer(), msg));
            }
        }, 10);
    }
}
