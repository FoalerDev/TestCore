package cc.foaler.core.commands;

import cc.foaler.core.TestCore;
import cc.foaler.core.utils.ColorUI;
import cc.foaler.core.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(player.hasPermission("testcore.commands.ping") || player.hasPermission("testcore.*") || player.isOp()) {
                if(args.length == 0) {
                    player.sendMessage(ColorUI.translate("&ePing&7: &f" + Utils.getPing(player)));
                }
                if(args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null) {
                        player.sendMessage(ColorUI.translate("&ePing of &f%player%&7: &f".replaceAll("%player%",
                                target.getName()) + Utils.getPing(player)));
                    } else {
                        player.sendMessage(ColorUI.translate("&eThis player has not been founded!"));
                    }
                }
            } else {
                player.sendMessage(ColorUI.translate("&4You don't have permissions to execute this command!"));
            }
        } else {
            sender.sendMessage("Only Players!");
        }
        return false;
    }
}
