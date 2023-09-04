package net.nessymc.netcore.Utils;

import org.bukkit.ChatColor;
import java.util.List;
import java.util.stream.Collectors;

public class MessageFormatter {

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> format(List<String> text) {
        return text.stream().map(MessageFormatter::format).collect(Collectors.toList());
    }
}






