package net.nessymc.netcore.Managers.Visual.Chat;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class AntiSwordsListener implements Listener {

    private final List<String> bannedWords;

    public AntiSwordsListener(FileConfiguration config) {
        bannedWords = config.getStringList("CensoredWords");
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();

        for (String bannedWord : bannedWords) {
            if (message.contains(bannedWord)) {
                message = message.replace(bannedWord, "****");
            }
        }
        event.setMessage(message);
    }
}
