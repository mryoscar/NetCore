package net.nessymc.netcore.Commands.UserCommands;

import me.clip.placeholderapi.PlaceholderAPI;
import net.nessymc.netcore.NetCore;
import net.nessymc.netcore.Utils.MessageFormatter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PingCommand implements CommandExecutor {

    private final NetCore plugin;

    public PingCommand(NetCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        FileConfiguration messages = plugin.getMessages();

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageFormatter.format(messages.getString("Messages.console")));
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 0) {
            player.sendMessage(MessageFormatter.format(messages.getString("Messages.usage")));
            return true;
        }

        String pingMessage = plugin.getMessages().getString("Messages.ping");
        pingMessage = PlaceholderAPI.setPlaceholders(player, pingMessage);
        player.sendMessage(MessageFormatter.format(pingMessage));
        return true;
    }
}
