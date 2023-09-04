package net.nessymc.netcore.Commands.UserCommands;

import net.nessymc.netcore.NetCore;
import net.nessymc.netcore.Utils.MessageFormatter;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {

    private final NetCore plugin;

    public SpawnCommand(NetCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        FileConfiguration config = plugin.getConfig();
        FileConfiguration messages = plugin.getMessages();

        if(!(sender instanceof Player)){
            sender.sendMessage(MessageFormatter.format(messages.getString("Messages.console")));
            return true;
        }

        Player player = (Player) sender;

        Location location = plugin.getConfig().getLocation("Spawn");

        if(location != null){
            player.teleport(location);
        }else{
            player.sendMessage(MessageFormatter.format(config.getString("SpawnSystem.noSet")));
        }
        return false;
    }
}

