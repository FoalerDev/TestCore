package cc.foaler.core.commands;

import cc.foaler.core.utils.ColorUI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ClearInventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission("testcore.commands.clearinventory") || sender.hasPermission("testcore.*") || sender.isOp()) {
            if(args.length == 0) {
                if(sender instanceof Player) {
                    Player player = (Player) sender;

                    player.getInventory().clear();
                    player.getInventory().setArmorContents(new ItemStack[0]);
                    player.updateInventory();

                    player.sendMessage(ColorUI.translate("&eYou have cleared Your Inventory"));
                } else {
                    sender.sendMessage(ColorUI.translate("&eUsage&7: &e/" + cmd.getName() + " &7(&eplayer&7)"));
                }
            }
            if(args.length == 1) {
                if(sender.hasPermission("testcore.commands.clearinventory.other") || sender.hasPermission("testcore.*") || sender.isOp()) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null) {
                        target.getInventory().clear();
                        target.getInventory().setArmorContents(new ItemStack[0]);
                        target.updateInventory();

                        sender.sendMessage(ColorUI.translate("&eYou have cleared &f" + target.getName() + "'s &eInventory"));
                    } else {
                        sender.sendMessage(ColorUI.translate("&eThis player has not been founded!"));
                    }
                } else {
                    sender.sendMessage(ColorUI.translate("&4You don't have permissions to execute this command!"));
                }
            }
        } else {
            sender.sendMessage(ColorUI.translate("&4You don't have permissions to execute this command!"));
        }
        return false;
    }
}