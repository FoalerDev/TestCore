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

public class WarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission("testcore.commands.warp") || sender.hasPermission("testcore.*") || sender.isOp()) {
            if(args.length == 0) {
                sender.sendMessage(ColorUI.translate("&eUsage&7: &e/" + cmd.getName() + " &7(&ename&7)"));
            }
            if(args.length == 1) {
                if(sender instanceof Player) {
                    Player player = (Player) sender;
                    String warpName = args[0];

                    if(warpName != null) {
                        if(TestCore.getInstance().getConfig().getString("Warps." + warpName.toLowerCase()) != null) {
                            World world = (World) TestCore.getInstance().getConfig().get("Warps." + warpName.toLowerCase() + ".world");
                            double x = TestCore.getInstance().getConfig().getDouble("Warps." + warpName.toLowerCase() + ".x");
                            double y = TestCore.getInstance().getConfig().getDouble("Warps." + warpName.toLowerCase() + ".y");
                            double z = TestCore.getInstance().getConfig().getDouble("Warps." + warpName.toLowerCase() + ".z");
                            float yaw = (float) TestCore.getInstance().getConfig().getDouble("Warps." + warpName.toLowerCase() + ".yaw");
                            float pitch = (float) TestCore.getInstance().getConfig().getDouble("Warps." + warpName.toLowerCase() + ".pitch");
                            Location location = new Location(world, x, y, z, yaw, pitch);

                            player.teleport(location, PlayerTeleportEvent.TeleportCause.COMMAND);
                            player.sendMessage(ColorUI.translate("&eYou have been warped to &7(&f" + warpName.toLowerCase() + "&7)"));
                        } else {
                            player.sendMessage(ColorUI.translate("&eThis warp does not exist!"));
                        }
                    } else {
                        player.sendMessage(ColorUI.translate("&eUsage&7: &e/" + cmd.getName() + " &7(&ename&7)"));
                    }
                } else {
                    sender.sendMessage(ColorUI.translate("&eUsage&7: &e/" + cmd.getName() + " &7(&ename&7) &7(&eplayer&7)"));
                }
            }
            if(args.length == 2) {
                String warpName = args[0];
                Player target = Bukkit.getPlayer(args[1]);

                if(warpName != null) {
                    if(TestCore.getInstance().getConfig().getString("Warps." + warpName.toLowerCase()) != null) {
                        World world = (World) TestCore.getInstance().getConfig().get("Warps." + warpName.toLowerCase() + ".world");
                        double x = TestCore.getInstance().getConfig().getDouble("Warps." + warpName.toLowerCase() + ".x");
                        double y = TestCore.getInstance().getConfig().getDouble("Warps." + warpName.toLowerCase() + ".y");
                        double z = TestCore.getInstance().getConfig().getDouble("Warps." + warpName.toLowerCase() + ".z");
                        float yaw = (float) TestCore.getInstance().getConfig().getDouble("Warps." + warpName.toLowerCase() + ".yaw");
                        float pitch = (float) TestCore.getInstance().getConfig().getDouble("Warps." + warpName.toLowerCase() + ".pitch");
                        Location location = new Location(world, x, y, z, yaw, pitch);

                        target.teleport(location, PlayerTeleportEvent.TeleportCause.COMMAND);
                        sender.sendMessage(ColorUI.translate("&eYou have warped &f" + target.getName() + "'s &eto &7(&f" + warpName.toLowerCase() + "&7)"));
                    } else {
                        target.sendMessage(ColorUI.translate("&eThis warp does not exist!"));
                    }
                } else {
                    sender.sendMessage(ColorUI.translate("&eUsage&7: &e/" + cmd.getName() + " &7(&ename&7) &7(&eplayer&7)"));
                }
            }
        } else {
            sender.sendMessage(ColorUI.translate("&4You don't have permissions to execute this command!"));
        }
        return false;
    }
}
