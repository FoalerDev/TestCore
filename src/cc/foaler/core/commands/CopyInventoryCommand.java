package cc.foaler.core.commands;

import cc.foaler.core.utils.ColorUI;
import cc.foaler.core.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CopyInventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("testcore.commands.copyinventory") || player.hasPermission("testcore.*") || player.isOp()) {
                if(args.length == 0) {
                    player.sendMessage(ColorUI.translate("&eUsage&7: &e/" + cmd.getName() + " &7(&eplayer&7)"));
                }
                if(args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null) {
                        Utils.copyInventoryToPlayer(target, player);
                        player.updateInventory();

                        player.sendMessage(ColorUI.translate("&eYou have copyed &f" + target.getName() + "'s &eInventory"));
                    } else {
                        player.sendMessage(ColorUI.translate("&eThis player has not been founded!"));
                    }
                }
            } else {
                sender.sendMessage(ColorUI.translate("&4You don't have permissions to execute this command!"));
            }
        } else {
            sender.sendMessage("Only Players!");
        }
        return false;
    }
}
