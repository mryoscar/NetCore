package net.nessymc.netcore.Managers.Visual.TabList;

import me.clip.placeholderapi.PlaceholderAPI;

import net.nessymc.netcore.Utils.MessageFormatter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.checkerframework.checker.nullness.qual.Nullable;

public class PrefixSuffixManager {

    private static final LuckPerms luckPerms = LuckPermsProvider.get();

    public static void updateTab(Player player, FileConfiguration config) {
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        if (user != null) {
            String prefix = getPrefix(user);
            String suffix = getSuffix(user);
            String tags = MessageFormatter.format(getExtraTags(config));
            tags = PlaceholderAPI.setPlaceholders(player, tags);
            String tabName = prefix + player.getName() + suffix + tags;
            player.setPlayerListName(tabName);
        }
    }

    private static String getPrefix(User user) {
        @Nullable String prefixNode = user.getCachedData().getMetaData().getPrefix();
        return prefixNode != null ? MessageFormatter.format(prefixNode) : "";
    }

    private static String getSuffix(User user) {
        @Nullable String suffixNode = user.getCachedData().getMetaData().getSuffix();
        return suffixNode != null ? MessageFormatter.format(suffixNode) : "";
    }

    private static String getExtraTags(FileConfiguration config) {
        @Nullable String tagsNode = config.getString("TabList.tags");
        return tagsNode != null ? tagsNode : "";
    }
}