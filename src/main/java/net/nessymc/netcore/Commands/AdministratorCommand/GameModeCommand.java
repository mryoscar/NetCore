package net.nessymc.netcore.Commands.AdministratorCommand;

import net.nessymc.netcore.NetCore;
import net.nessymc.netcore.Utils.MessageFormatter;
import org.bukkit.command.CommandExecutor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GameModeCommand implements CommandExecutor {

    private final NetCore plugin;

    public GameModeCommand(NetCore plugin){
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
        if (!player.hasPermission("netcore.gamemode")) {
            player.sendMessage(MessageFormatter.format(messages.getString("Messages.noPermission")));
            return true;
        }

        if (args.length == 0 || args.length > 2) {
            player.sendMessage(MessageFormatter.format(messages.getString("GameMode.usage")));
            return true;
        }

        GameMode gameMode;
        try {
            int modeId = Integer.parseInt(args[0]);
            gameMode = GameMode.getByValue(modeId);
        } catch (NumberFormatException e) {
            try {
                gameMode = GameMode.valueOf(args[0].toUpperCase());
            } catch (IllegalArgumentException ex) {
                player.sendMessage(MessageFormatter.format(messages.getString("GameMode.noExist")));
                return true;
            }
        } catch (IllegalArgumentException e) {
            player.sendMessage(MessageFormatter.format(messages.getString("GameMode.noExist")));
            return true;
        }

        if (args.length > 1) {
            String playerName = args[1];
            Player target = player.getServer().getPlayer(playerName);
            if (target == null) {
                player.sendMessage(MessageFormatter.format(messages.getString("Messages.noPlayer")));
                return true;
            }

            if (target == player) {
                player.setGameMode(Objects.requireNonNull(gameMode));
                player.sendMessage(MessageFormatter.format(messages.getString("GameMode.change")).replace("%gamemode%", player.getGameMode().name()));
                return true;
            }

            target.setGameMode(Objects.requireNonNull(gameMode));
            player.sendMessage(MessageFormatter.format(messages.getString("GameMode.otherChange")).replace("%player%", target.getName()).replace("%gamemode%", target.getGameMode().name()));
        } else {
            player.setGameMode(Objects.requireNonNull(gameMode));
            player.sendMessage(MessageFormatter.format(messages.getString("GameMode.change")).replace("%gamemode%", player.getGameMode().name()));
        }

        return true;
    }
}
