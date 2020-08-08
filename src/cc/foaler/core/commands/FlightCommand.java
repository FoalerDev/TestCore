package cc.foaler.core.commands;

import cc.foaler.core.TestCore;
import cc.foaler.core.utils.ColorUI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlightCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission("testcore.commands.flight") || sender.hasPermission("testcore.*") || sender.isOp()) {
            if(args.length == 0) {
                if(sender instanceof Player) {
                    Player player = (Player) sender;

                    if(player.getAllowFlight() == true) {
                        player.setFlying(false);
                        player.setNoDamageTicks(60);

                        player.sendMessage(ColorUI.translate("&eYou have &4disabled &eYour Flight Mode"));
                        TestCore.getInstance().getLogger().info(player.getName() + " has disabled Their Flight Mode");
                    } else if(player.getAllowFlight() == false) {
                        player.sendMessage(ColorUI.translate("&eYou have &aenabled &eYour Flight Mode"));
                        TestCore.getInstance().getLogger().info(player.getName() + " has enabled Their Flight Mode");
                    }

                    player.setAllowFlight(!player.getAllowFlight());
                } else {
                    sender.sendMessage(ColorUI.translate("&eUsage&7: &e/" + cmd.getName() + " &7(&eplayer&7)"));
                }
            }
            if(args.length == 1) {
                if(sender.hasPermission("testcore.commands.flight.other") || sender.hasPermission("testcore.*") || sender.isOp()) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null)  {
                        if(target.getAllowFlight() == true) {
                            target.setFlying(false);
                            target.setNoDamageTicks(60);

                            sender.sendMessage(ColorUI.translate("&eYou have &4disabled &f" + target.getName() + "'s &eFlight Mode"));

                            target.sendMessage(ColorUI.translate("&eYou have &4disabled &eYour Flight Mode"));

                            TestCore.getInstance().getLogger().info(sender.getName() + " has disabled " + target.getName() + "'s Flight Mode");
                            TestCore.getInstance().getLogger().info(target.getName() + " has disabled Their Flight Mode");
                        } else if(target.getAllowFlight() == false) {
                            sender.sendMessage(ColorUI.translate("&eYou have &aenabled &f" + target.getName() + "'s &eFlight Mode"));

                            target.sendMessage(ColorUI.translate("&eYou have &aenabled &eYour Flight Mode"));

                            TestCore.getInstance().getLogger().info(sender.getName() + " has enabled " + target.getName() + "'s Flight Mode");
                            TestCore.getInstance().getLogger().info(target.getName() + " has enabled Their Flight Mode");
                        }

                        target.setAllowFlight(!target.getAllowFlight());
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
