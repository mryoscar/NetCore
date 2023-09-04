package net.nessymc.netcore.Managers.Visual.TabList;

import net.nessymc.netcore.NetCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class TabAPI implements Runnable {
    private final NetCore plugin;

    public TabAPI(NetCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            FileConfiguration config = plugin.getConfig();
            updateTabList(player);
            PrefixSuffixManager.updateTab(player, config);
        });
    }

    public void updateTabList(Player player) {
        FileConfiguration config = plugin.getConfig();
        List<String> header = modifyTabContent(player, config.getStringList("TabList.header"));
        List<String> footer = modifyTabContent(player, config.getStringList("TabList.footer"));
        player.setPlayerListHeaderFooter(
                String.join("\n", header),
                String.join("\n", footer));
    }

    private List<String> modifyTabContent(Player player, List<String> originalContent) {
        String playerName = player.getName();
        return originalContent.stream()
                .map(line -> ChatColor.translateAlternateColorCodes('&', line.replace("{player}", playerName)))
                .collect(Collectors.toList());
    }
}
