package me.serbob.toastedkilldeath.Commands;

import me.serbob.toastedkilldeath.ToastedKillDeath;
import me.serbob.toastedkilldeath.Utils.GlobalUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetKillDeath implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("tset.use")) {
            sender.sendMessage(GlobalUtil.c("You don't have permission to use this command!"));
            return false;
        }
        if(args.length<3) {
            sender.sendMessage(GlobalUtil.c("&cUsage: /tset <player> <kills/deaths> <amount>"));
            return false;
        }
        OfflinePlayer victim = Bukkit.getOfflinePlayer(args[0]);
        if(victim==null) {
            sender.sendMessage(GlobalUtil.c("&cThe player you've entered doesn't exist!"));
            return false;
        }
        String statToBeChanged = args[1].toUpperCase();
        if(Statistic.valueOf(statToBeChanged) == null) {
            sender.sendMessage(GlobalUtil.c("&cThe stat you entered doesn't exist!"));
            return false;
        }
        int amount;
        try {
            amount = Integer.parseInt(args[2]);
        } catch (Exception ignored) {
            sender.sendMessage(GlobalUtil.c("&cYou need to provide a real number!"));
            return false;
        }
        Statistic statistic = Statistic.valueOf(statToBeChanged);
        int initialValue = victim.getStatistic(statistic);
        victim.setStatistic(statistic,amount);
        sender.sendMessage(GlobalUtil.c("&aSuccesfully set stat &f"+statToBeChanged+" &avalue from &f"+initialValue+" &ato &f"+amount+" &afor &f"+victim.getName()));
        return true;
    }
}
