package cc.foaler.core.commands;

import cc.foaler.core.TestCore;
import cc.foaler.core.data.types.PlayerData;
import cc.foaler.core.utils.ColorUI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission("testcore.commands.vanish") || sender.hasPermission("testcore.*") || sender.isOp()) {
            if(args.length == 0) {
                if(sender instanceof Player) {
                    Player player = (Player) sender;

                    PlayerData data = TestCore.getInstance().getDataManager().getDataMap().get(player.getUniqueId());

                    if(data.vanished == false) {
                        player.sendMessage(ColorUI.translate("&eYou have &aenabled &eYour Vanish Mode"));
                    } else {
                        player.sendMessage(ColorUI.translate("&eYou have &4disabled &eYour Vanish Mode"));
                    }

                    data.setVanished(data.getPlayer(), !data.isVanished());
                } else {
                    sender.sendMessage(ColorUI.translate("&eUsage&7: &e/" + cmd.getName() + " &7(&eplayer&7)"));
                }
            }
            if(args.length == 1) {
                if(sender.hasPermission("testcore.commands.vanish.other") || sender.hasPermission("testcore.*") || sender.isOp()) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null) {
                        PlayerData data = TestCore.getInstance().getDataManager().getDataMap().get(target.getUniqueId());

                        if(data.vanished == false) {
                            sender.sendMessage(ColorUI.translate("&eYou have &aenabled &f" + target.getName() + "'s &eVanish Mode"));
                        } else {
                            sender.sendMessage(ColorUI.translate("&eYou have &4disabled &f" + target.getName() + "'s &eVanish Mode"));
                        }
                        data.setVanished(data.getPlayer(), !data.isVanished());
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
