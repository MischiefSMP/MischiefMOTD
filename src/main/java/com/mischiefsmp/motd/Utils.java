package com.mischiefsmp.motd;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class Utils {
    private static final String DESCRIPTOR = "${%s}";

    private static final String D_WORLD = f("world");
    private static final String D_USERNAME = f("username");

    private static final String D_ONLINE_COUNT = f("onlineCount");
    private static final String D_TOTAL_SLOTS = f("totalSlots");

    private static final String D_TIMEZONE = f("timezone");

    //Time
    private static final String D_HOUR = f("hour");
    private static final String D_MINUTE = f("minute");
    private static final String D_SECOND = f("second");

    //DATE
    private static final String D_DAY = f("day");
    private static final String D_MONTH = f("month");
    private static final String D_YEAR = f("year");

    private static String f(String argument) {
        return String.format(DESCRIPTOR, argument);
    }

    public static String formatArguments(Player player, String line) {
        line = rAll(line, D_WORLD, player.getWorld().getName());
        line = rAll(line, D_USERNAME, player.getName());
        line = rAll(line, D_ONLINE_COUNT, getPlayerCount(player));
        line = rAll(line, D_TOTAL_SLOTS, Bukkit.getServer().getMaxPlayers() + "");

        line = rAll(line, D_TIMEZONE, TimeZone.getDefault().getID());

        LocalDateTime dateTime = LocalDateTime.now();
        line = rAll(line, D_HOUR, fZero(dateTime.getHour()));
        line = rAll(line, D_MINUTE, fZero(dateTime.getMinute()));
        line = rAll(line, D_SECOND, fZero(dateTime.getSecond()));

        line = rAll(line, D_DAY, fZero(dateTime.getDayOfMonth()));
        line = rAll(line, D_MONTH, fZero(dateTime.getMonthValue()));
        line = rAll(line, D_YEAR, fZero(dateTime.getYear()));

        return line;
    }

    public static String rAll(String current, String toReplace, String replacement) {
        return current.replaceAll(Pattern.quote(toReplace), replacement);
    }

    public static String fZero(int nr) {
        if(nr >= 10)
            return nr + "";
        return "0" + nr;
    }

    public static String getPlayerCount(Player player) {
        int i = 0;
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            if(player.canSee(p))
                i++;
        }
        return i + "";
    }
}
