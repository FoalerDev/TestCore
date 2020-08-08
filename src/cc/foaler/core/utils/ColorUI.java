package cc.foaler.core.utils;

import org.bukkit.ChatColor;

public class ColorUI {

    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String strip(String text) {
        return ChatColor.stripColor(text);
    }
}
