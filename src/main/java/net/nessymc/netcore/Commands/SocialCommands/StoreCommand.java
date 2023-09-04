package net.nessymc.netcore.Commands.SocialCommands;

import net.nessymc.netcore.NetCore;
import net.nessymc.netcore.Utils.MessageFormatter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StoreCommand implements CommandExecutor {
    private final NetCore plugin;

    public StoreCommand(NetCore plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        FileConfiguration config = plugin.getConfig();
        List<String> storeMessages = config.getStringList("Social.store");
        for (String message : storeMessages) {
            sender.sendMessage(MessageFormatter.format(message));
        }
        return false;
    }
}
