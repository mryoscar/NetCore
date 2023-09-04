package net.nessymc.netcore.Commands.SocialCommands;

import net.nessymc.netcore.NetCore;
import net.nessymc.netcore.Utils.MessageFormatter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class YouTubeCommand implements CommandExecutor {

    private final NetCore plugin;

    public YouTubeCommand(NetCore plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        FileConfiguration config = plugin.getConfig();
        List<String> youtubeMessages = config.getStringList("Social.youtube");
        for (String message : youtubeMessages) {
            sender.sendMessage(MessageFormatter.format(message));
        }
        return false;
    }
}
