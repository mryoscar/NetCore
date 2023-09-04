package net.nessymc.netcore.Managers.Visual.Chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;

public class ChatListener implements Listener {

    private final ChatManager chatManager;

    public ChatListener(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent listener) {
        Player player = listener.getPlayer();
        String playerFormat = chatManager.getChatFormat();
        String message = listener.getMessage();
        String formattedMessage = playerFormat.replace("%send_message%", message);

        formattedMessage = PlaceholderAPI.setPlaceholders(player, formattedMessage);

        if (player.hasPermission("netcore.colors")) {
            message = ChatColor.translateAlternateColorCodes('&', message);
        }

        formattedMessage = formattedMessage.replace("%send_message%", message);

        listener.setFormat(formattedMessage);
    }
}
