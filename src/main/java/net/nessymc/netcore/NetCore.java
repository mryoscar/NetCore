package net.nessymc.netcore;

import net.nessymc.netcore.Commands.AdministratorCommand.GameModeCommand;
import net.nessymc.netcore.Commands.AdministratorCommand.NetCoreCommand;
import net.nessymc.netcore.Commands.SocialCommands.DiscordCommand;
import net.nessymc.netcore.Commands.SocialCommands.StoreCommand;
import net.nessymc.netcore.Commands.SocialCommands.TwitterCommand;
import net.nessymc.netcore.Commands.SocialCommands.YouTubeCommand;
import net.nessymc.netcore.Commands.UserCommands.FlyCommand;
import net.nessymc.netcore.Commands.UserCommands.PingCommand;
import net.nessymc.netcore.Commands.UserCommands.SpawnCommand;
import net.nessymc.netcore.Listener.FlightListener;
import net.nessymc.netcore.Listener.HotBarListener;
import net.nessymc.netcore.Listener.PrefixSuffixListener;
import net.nessymc.netcore.Listener.SpawnListener;
import net.nessymc.netcore.Managers.HotBar.HotBarAPI.SuperHotBar;
import net.nessymc.netcore.Managers.HotBar.HubItems;
import net.nessymc.netcore.Managers.Visual.Chat.AntiSwordsListener;
import net.nessymc.netcore.Managers.Visual.Chat.ChatListener;
import net.nessymc.netcore.Managers.Visual.Chat.ChatManager;
import net.nessymc.netcore.Managers.Visual.Chat.MessagesJoin;
import net.nessymc.netcore.Managers.Visual.Scoreboard.Scoreboard;
import net.nessymc.netcore.Managers.Visual.Scoreboard.src.main.java.scoreboard.ScoreboardManager;
import net.nessymc.netcore.Managers.Visual.TabList.TabAPI;
import net.nessymc.netcore.Managers.World.AntiPlaceLava;
import net.nessymc.netcore.Managers.World.WorldProtect;
import net.nessymc.netcore.Utils.MessageFormatter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public final class NetCore extends JavaPlugin {
    private static NetCore instance;
    private FileConfiguration messages = null;
    private File messagesFile = null;
    private ScoreboardManager scoreboardManager;
    String version = getDescription().getVersion();
    String api = getDescription().getAPIVersion();
    private TabAPI tabManager;
    private BukkitTask tabUpdaterTask;
    private ChatManager chatManager;
    private SuperHotBar superHotBar;
    String serverName = getServer().getName();


    @Override
    public void onEnable() {
        instance = this;
        superHotBar = new SuperHotBar(this);
        chatManager = new ChatManager(this);
        this.init();
        loadConfig();
        loadMessages();
        loadScoreboard();
        loadCommand();
        loadTab();
        getLogger().info(MessageFormatter.format("&7"));
        getLogger().info(MessageFormatter.format("&b&lNET&f&lCORE &aEl NetCore cargó correctamente todas sus funciones."));
        getLogger().info(MessageFormatter.format("&7"));
        getLogger().info(MessageFormatter.format("&fDeveloper: &eSuperYoscar"));
        getLogger().info(MessageFormatter.format("&fVersion: &e" +version));
        getLogger().info(MessageFormatter.format("&fAPI Version: &a" +api));
        getLogger().info(MessageFormatter.format("&7"));
        getLogger().info(MessageFormatter.format("&aDependencies"));
        getLogger().info(MessageFormatter.format("&eVault, PlaceholderAPI & LuckPerms"));
        getLogger().info(MessageFormatter.format("&7"));
        getLogger().info(MessageFormatter.format("&aManagers"));
        getLogger().info(MessageFormatter.format("&eHotBar, Visual & Worlds"));
        getLogger().info(MessageFormatter.format("&7"));
        getLogger().info(MessageFormatter.format("&fEl &b&lNET&f&lCORE &fse esta ejecutando en el servidor:&a " +serverName));
        getLogger().info(MessageFormatter.format("&7"));
    }

    @Override
    public void onDisable() {
        this.scoreboardManager.disable();
        this.disableTab();
        disableHotBar();
        getLogger().info(MessageFormatter.format("&7"));
        getLogger().info(MessageFormatter.format("&b&lNET&f&lCORE &cEl NetCore desactivó correctamente todas sus funciones."));
        getLogger().info(MessageFormatter.format("&7"));
    }

    private void registerListeners(Listener... listeners) {
        Arrays.stream(listeners).forEach(l -> Bukkit.getServer().getPluginManager().registerEvents(l, this));
    }

    public void init() {
        this.registerListeners(
                new SpawnListener(this),
                new PrefixSuffixListener(this),
                new ChatListener(chatManager),
                new WorldProtect(this),
                new AntiSwordsListener(getConfig()),
                new FlightListener(),
                new AntiPlaceLava(),
                new HotBarListener(superHotBar),
                new MessagesJoin(this)
        );
    }

    public void loadCommand(){
        Objects.requireNonNull(getCommand("netcore")).setExecutor(new NetCoreCommand(this));
        Objects.requireNonNull(getCommand("nc")).setExecutor(new NetCoreCommand(this));
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommand(this));
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand(this));
        Objects.requireNonNull(getCommand("ping")).setExecutor(new PingCommand(this));
        Objects.requireNonNull(getCommand("gamemode")).setExecutor(new GameModeCommand(this));
        Objects.requireNonNull(getCommand("gm")).setExecutor(new GameModeCommand(this));
        Objects.requireNonNull(getCommand("discord")).setExecutor(new DiscordCommand(this));
        Objects.requireNonNull(getCommand("dc")).setExecutor(new DiscordCommand(this));
        Objects.requireNonNull(getCommand("store")).setExecutor(new StoreCommand(this));
        Objects.requireNonNull(getCommand("tienda")).setExecutor(new StoreCommand(this));
        Objects.requireNonNull(getCommand("twitter")).setExecutor(new TwitterCommand(this));
        Objects.requireNonNull(getCommand("youtube")).setExecutor(new YouTubeCommand(this));
        Objects.requireNonNull(getCommand("yt")).setExecutor(new YouTubeCommand(this));
    }

    public void loadConfig(){
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            getConfig().options().copyDefaults();
            saveDefaultConfig();
        }
    }

    public void loadScoreboard(){
        this.scoreboardManager = new ScoreboardManager(this, new Scoreboard(this));
        this.scoreboardManager.setUpdateInterval(2000L);
    }

    public void loadTab() {
        tabManager = new TabAPI(this);
        int updateInterval = 20;
        getServer().getScheduler().runTaskTimer(this, tabManager, 0, updateInterval);
    }

    public void disableTab(){
        if (tabUpdaterTask != null) {
            tabUpdaterTask.cancel();
        }
    }

    public void disableHotBar(){
        if (superHotBar != null) {
            superHotBar.cleanup();
        }
    }

    public FileConfiguration getMessages() {
        if(messages == null) {
            reloadMessages();
            messagesFile = new File(getDataFolder(), "messages.yml");
        }
        return messages;
    }

    public void reloadMessages() {
        if(messages == null){
            messagesFile = new File(getDataFolder(),"messages.yml");
        }
        messages = YamlConfiguration.loadConfiguration(messagesFile);
        Reader defConfigStream;
        defConfigStream = new InputStreamReader(Objects.requireNonNull(this.getResource("messages.yml")), StandardCharsets.UTF_8);
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        messages.setDefaults(defConfig);

    }

    public void saveMessages() {
        try{
            messages.save(messagesFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMessages() {
        messagesFile = new File(getDataFolder(), "messages.yml");
        if(!messagesFile.exists()){
            this.getMessages().options().copyDefaults(true);
            saveMessages();
        }
    }
}
