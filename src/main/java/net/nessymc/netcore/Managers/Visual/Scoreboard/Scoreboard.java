package net.nessymc.netcore.Managers.Visual.Scoreboard;

import me.clip.placeholderapi.PlaceholderAPI;
import net.nessymc.netcore.Managers.Visual.Scoreboard.src.main.java.scoreboard.PlayerScoreboard;
import net.nessymc.netcore.Managers.Visual.Scoreboard.src.main.java.scoreboard.adapter.ScoreAdapter;
import net.nessymc.netcore.NetCore;
import net.nessymc.netcore.Utils.MessageFormatter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class Scoreboard implements ScoreAdapter {


    private final NetCore plugin;

    public Scoreboard(NetCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getTitle() {
        FileConfiguration config = plugin.getConfig();
        return MessageFormatter.format(config.getString("Scoreboard.title"));
    }

    @Override
    public void updateLines(PlayerScoreboard scoreboard) {
        Player player = scoreboard.getPlayer();
        FileConfiguration config = plugin.getConfig();
        List<String> scorelines = config.getStringList("Scoreboard.lines");
        for (String lines : scorelines) {
            lines = PlaceholderAPI.setPlaceholders(player, lines);
            scoreboard.addLine(lines);
        }
    }
}