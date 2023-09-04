package net.nessymc.netcore.Commands.UserCommands;

import net.nessymc.netcore.NetCore;
import net.nessymc.netcore.Utils.MessageFormatter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlyCommand implements CommandExecutor {

    private final NetCore plugin;

    public FlyCommand(NetCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] strings) {
        FileConfiguration messages = plugin.getMessages();

        if(!(sender instanceof Player)) {
            sender.sendMessage(MessageFormatter.format(messages.getString("Messages.console")));
            return true;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("netcore.fly")){
            sender.sendMessage(MessageFormatter.format(messages.getString("Messages.noPermission")));
            return true;
        }

        if(strings.length == 0) {
            toggleFly(player);
        } else {

           Player object = Bukkit.getPlayer(strings[0]);

        if (object != null) {
            toggleFly(object);
        } else {
            sender.sendMessage(MessageFormatter.format(messages.getString("Messages.noPlayer")));
        }
    }

    return false;
}

    private void toggleFly(Player player) {
        FileConfiguration config = plugin.getConfig();

        String deactivateTitle = MessageFormatter.format(config.getString("Flight.deactivated-title"));
        String deactivateSubTitle = MessageFormatter.format(config.getString("Flight.deactivated-subtitle"));

        String activateTitle = MessageFormatter.format(config.getString("Flight.activated-title"));
        String activateSubTitle = MessageFormatter.format(config.getString("Flight.activated-subtitle"));

        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(MessageFormatter.format(config.getString("Flight.deactivated")));
            player.sendTitle(deactivateTitle,deactivateSubTitle,10,70,20);
        } else {
            player.setAllowFlight(true);
            player.sendMessage(MessageFormatter.format(config.getString("Flight.activated")));
            player.sendTitle(activateTitle,activateSubTitle,10,70,20);
        }
    }
}
