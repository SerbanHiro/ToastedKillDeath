package me.serbob.toastedkilldeath.TabCompleters;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SetKillDeathTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        if(args.length==1) {
            for(OfflinePlayer player: Bukkit.getOfflinePlayers()) {
                list.add(player.getName());
            }
            String letters = args[0].toLowerCase();
            list = list.stream().filter(s -> s.toLowerCase().startsWith(letters)).collect(Collectors.toList());
        } else if(args.length<3) {
            for(Statistic key: Statistic.values()) {
                list.add(key.name());
            }
            String letters = args[1].toLowerCase();
            list = list.stream().filter(s -> s.toLowerCase().startsWith(letters)).collect(Collectors.toList());
        } else if(args.length<4) {
            list.add("<amount>");
        }
        return list;
    }
}
