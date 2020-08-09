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

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(player.hasPermission("testcore.commands.spawn") || player.hasPermission("testcore.*") || player.isOp()) {
                if(TestCore.getInstance().getConfig().getConfigurationSection("Spawn") != null) {
                    World world = Bukkit.getWorld(TestCore.getInstance().getConfig().getString("Spawn.world"));
                    double x = TestCore.getInstance().getConfig().getDouble("Spawn.x");
                    double y = TestCore.getInstance().getConfig().getDouble("Spawn.y");
                    double z = TestCore.getInstance().getConfig().getDouble("Spawn.z");
                    float yaw = (float) TestCore.getInstance().getConfig().getDouble("Spawn.yaw");
                    float pitch = (float) TestCore.getInstance().getConfig().getDouble("Spawn.pitch");
                    Location location = new Location(world, x, y, z, yaw, pitch);

                    player.teleport(location, PlayerTeleportEvent.TeleportCause.COMMAND);
                    player.sendMessage(ColorUI.translate("&eYou have teleported to &fSpawn"));
                } else {
                    player.sendMessage(ColorUI.translate("&eCannot locate the &fSpawn &elocation"));
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
