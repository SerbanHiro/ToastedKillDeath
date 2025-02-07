package me.serbob.toastedkilldeath.TabCompleters;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.*;
import java.util.stream.Collectors;

public class SetKillDeathTabCompleter implements TabCompleter {
    private static final int MAX_SUGGESTIONS = 20;
    private List<String> cachedOfflinePlayers = new ArrayList<>();

    public SetKillDeathTabCompleter() {
        updateOfflinePlayerCache();
    }

    private void updateOfflinePlayerCache() {
        cachedOfflinePlayers = Arrays.stream(Bukkit.getOfflinePlayers())
                .map(OfflinePlayer::getName)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if(args.length == 1) {
            list = cachedOfflinePlayers.stream()
                    .filter(name -> name.toLowerCase().startsWith(args[0].toLowerCase()))
                    .limit(MAX_SUGGESTIONS)
                    .collect(Collectors.toList());
        } else if(args.length < 3) {
            list = Arrays.stream(Statistic.values())
                    .map(Enum::name)
                    .filter(s -> s.toLowerCase().startsWith(args[1].toLowerCase()))
                    .collect(Collectors.toList());
        } else if(args.length < 4) {
            list.add("<amount>");
        }
        return list;
    }
}
