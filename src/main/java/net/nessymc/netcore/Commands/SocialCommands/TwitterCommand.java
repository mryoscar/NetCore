package net.nessymc.netcore.Commands.SocialCommands;

import net.nessymc.netcore.NetCore;
import net.nessymc.netcore.Utils.MessageFormatter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TwitterCommand implements CommandExecutor {
    private final NetCore plugin;

    public TwitterCommand(NetCore plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        FileConfiguration config = plugin.getConfig();
        List<String> twitterMessages = config.getStringList("Social.twitter");
        for (String message : twitterMessages) {
            sender.sendMessage(MessageFormatter.format(message));
        }
        return false;
    }
}
