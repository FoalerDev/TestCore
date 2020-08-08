package cc.foaler.core.commands;

import cc.foaler.core.TestCore;
import cc.foaler.core.utils.ColorUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateWarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(player.hasPermission("testcore.commands.warp.create") || player.hasPermission("testcore.*") || player.isOp()) {
                if(args.length == 0) {
                    player.sendMessage(ColorUI.translate("&eUsage&7: &e/" + cmd.getName() + " &7(&ename&7)"));
                }
                if(args.length == 1) {
                    String name = args[0];

                    if(name != null) {
                        if(TestCore.getInstance().getConfig().getString("Warps." + name.toLowerCase()) == null) {
                            TestCore.getInstance().getConfig().set("Warps." + name.toLowerCase() + ".world", player.getWorld());
                            TestCore.getInstance().getConfig().set("Warps." + name.toLowerCase() + ".x", player.getLocation().getX());
                            TestCore.getInstance().getConfig().set("Warps." + name.toLowerCase() + ".y", player.getLocation().getY());
                            TestCore.getInstance().getConfig().set("Warps." + name.toLowerCase() + ".z", player.getLocation().getZ());
                            TestCore.getInstance().getConfig().set("Warps." + name.toLowerCase() + ".yaw", player.getLocation().getYaw());
                            TestCore.getInstance().getConfig().set("Warps." + name.toLowerCase() + ".pitch", player.getLocation().getPitch());
                            TestCore.getInstance().saveConfig();

                            player.sendMessage(ColorUI.translate("&eYou have registered &7(&f" + name.toLowerCase() + "&7) &eas a warp!"));
                        } else {
                            player.sendMessage(ColorUI.translate("&eThis warped is already registered!"));
                        }
                    } else {
                        player.sendMessage(ColorUI.translate("&eUsage&7: &e/" + cmd.getName() + " &7(&ename&7)"));
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
