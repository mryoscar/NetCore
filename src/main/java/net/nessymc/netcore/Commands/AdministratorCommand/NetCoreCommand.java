package net.nessymc.netcore.Commands.AdministratorCommand;

import net.nessymc.netcore.NetCore;
import net.nessymc.netcore.Utils.MessageFormatter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NetCoreCommand implements CommandExecutor {

    private final NetCore plugin;

    public NetCoreCommand(NetCore plugin) {
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        int serverPort = sender.getServer().getPort();
        String serverIp = sender.getServer().getIp();
        String serverName = sender.getServer().getName();
        String serverVersion = sender.getServer().getVersion();
        GameMode serverGameModeDefault = sender.getServer().getDefaultGameMode();
        FileConfiguration config = plugin.getConfig();
        FileConfiguration messages = plugin.getMessages();

        if(!(sender.hasPermission("netcore.admin"))){
            sender.sendMessage(MessageFormatter.format(messages.getString("Messages.noPermission")));
            return true;
        }

        if(!(strings.length > 0)){
            sender.sendMessage(MessageFormatter.format(messages.getString("Messages.usage")));
            return true;
        }

        if(strings[0].equalsIgnoreCase("help")){
            sender.sendMessage(MessageFormatter.format("&7"));
            sender.sendMessage(MessageFormatter.format("   &b&lNET&f&lCORE &8| &7Comandos "));
            sender.sendMessage(MessageFormatter.format("&7"));
            sender.sendMessage(MessageFormatter.format("&cComandos de administración"));
            sender.sendMessage(MessageFormatter.format("&e/netcore help &8| &7Muestra este mensaje"));
            sender.sendMessage(MessageFormatter.format("&e/netcore info &8| &7Muestra información sobre el servidor"));
            sender.sendMessage(MessageFormatter.format("&e/netcore reload &8| &7Recarga totalmente el plugin"));
            sender.sendMessage(MessageFormatter.format("&e/netcore setspawn &8| &7Setea el spawn del servidor al entrar"));
            sender.sendMessage(MessageFormatter.format("&7"));
            return true;
        }

        if(strings[0].equalsIgnoreCase("info")){
            sender.sendMessage(MessageFormatter.format("&7"));
            sender.sendMessage(MessageFormatter.format("   &b&lNET&f&lCORE &8| &7Información"));
            sender.sendMessage(MessageFormatter.format("&7"));
            sender.sendMessage(MessageFormatter.format("&fNombre del servidor &8➥ &e" +serverName));
            sender.sendMessage(MessageFormatter.format("&fVersión base &8➥ &e" +serverVersion));
            sender.sendMessage(MessageFormatter.format("&fDirección Ip del servidor &8➥ &e" +serverIp));
            sender.sendMessage(MessageFormatter.format("&fPuerto del servidor &8➥ &e" +serverPort));
            sender.sendMessage(MessageFormatter.format("&fModo de juego default &8➥ &e" +serverGameModeDefault));
            sender.sendMessage(MessageFormatter.format("&7"));
            sender.sendMessage(MessageFormatter.format("&7Información del servidor proporcionada por &bNet&fCore"));
            sender.sendMessage(MessageFormatter.format("&7"));
            return true;
        }

        if(strings[0].equalsIgnoreCase("reload")){
            plugin.reloadConfig();
            plugin.reloadMessages();
            sender.sendMessage(MessageFormatter.format(messages.getString("Messages.reload")));
            return true;
        }

        if(strings[0].equalsIgnoreCase("setspawn")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(MessageFormatter.format(messages.getString("Messages.console")));
                return true;
            }
            Player player = (Player) sender;
            plugin.getConfig().set("Spawn", player.getLocation());
            plugin.saveConfig();
            String title = MessageFormatter.format(config.getString("SpawnSystem.title"));
            String subTitle = MessageFormatter.format(config.getString("SpawnSystem.subTitle"));
            player.sendMessage(MessageFormatter.format(config.getString("SpawnSystem.set")));
            player.sendTitle(title,subTitle,10,70,20);
            return true;
        }
        return false;
    }
}
