package cc.foaler.core.commands;

import cc.foaler.core.TestCore;
import cc.foaler.core.utils.ColorUI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(player.hasPermission("testcore.commands.setspawn") || player.hasPermission("testcore.*") || player.isOp()) {
                TestCore.getInstance().getConfig().set("Spawn.world", player.getWorld().getName());
                TestCore.getInstance().getConfig().set("Spawn.x", player.getLocation().getX());
                TestCore.getInstance().getConfig().set("Spawn.y", player.getLocation().getY());
                TestCore.getInstance().getConfig().set("Spawn.z", player.getLocation().getZ());
                TestCore.getInstance().getConfig().set("Spawn.yaw", player.getLocation().getYaw());
                TestCore.getInstance().getConfig().set("Spawn.pitch", player.getLocation().getPitch());
                TestCore.getInstance().saveConfig();

                player.sendMessage(ColorUI.translate("&eYou have updated the &7(&fSpawn Point&7)"));
            } else {
                player.sendMessage(ColorUI.translate("&4You don't have permissions to execute this command!"));
            }
        } else {
            sender.sendMessage("Only Players!");
        }
        return false;
    }
}
