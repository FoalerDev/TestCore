package cc.foaler.core.commands;

import cc.foaler.core.TestCore;
import cc.foaler.core.utils.ColorUI;
import com.sun.xml.internal.bind.v2.runtime.SwaRefAdapter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveWarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(player.hasPermission("testcore.commands.warp.remove") || player.hasPermission("testcore.*") || player.isOp()) {
                if(args.length == 0) {
                    player.sendMessage(ColorUI.translate("&eUsage&7: &e/" + cmd.getName() + " &7(&ename&7)"));
                }
                if(args.length == 1) {
                    String name = args[0];

                    if(name != null) {
                        if(TestCore.getInstance().getConfig().getString("Warps." + name.toLowerCase()) != null) {
                            TestCore.getInstance().getConfig().set("Warps." + name.toLowerCase(), null);
                            TestCore.getInstance().saveConfig();

                            player.sendMessage(ColorUI.translate("&eYou have unregistered &7(&f" + name.toLowerCase() + "&7) &eas a warp!"));
                        } else {
                            player.sendMessage(ColorUI.translate("&eThis warped is does not exist!"));
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
