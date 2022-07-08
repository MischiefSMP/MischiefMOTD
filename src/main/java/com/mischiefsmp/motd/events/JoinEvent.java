package com.mischiefsmp.motd.events;

import com.mischiefsmp.motd.MischiefMOTD;
import com.mischiefsmp.motd.Utils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        ArrayList<String> motd = MischiefMOTD.getPluginConfig().getMotd();
        if(motd == null)
            return;

        Bukkit.getScheduler ().runTaskLater (MischiefMOTD.getInstance(), () -> {
            for(String msg : motd) {
                event.getPlayer().sendMessage(Utils.formatArguments(event.getPlayer(), msg));
            }
        }, 10);
    }
}
