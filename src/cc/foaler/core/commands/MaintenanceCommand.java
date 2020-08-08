package cc.foaler.core.commands;

import cc.foaler.core.TestCore;
import cc.foaler.core.utils.ColorUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class MaintenanceCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission("testcore.commands.maintenance") || sender.hasPermission("testcore.*") || sender.isOp()) {
            if(TestCore.getInstance().getConfig().getBoolean("Server.maintenance") == false) {
                sender.sendMessage(ColorUI.translate("&eYou have &aenabled &eServer Maintenance"));

                TestCore.getInstance().getLogger().info(sender.getName() + " has enabled Server Maintenance");
            } else if(TestCore.getInstance().getConfig().getBoolean("Server.maintenance") == true) {
                sender.sendMessage(ColorUI.translate("&eYou have &4disabled &eServer Maintenance"));

                TestCore.getInstance().getLogger().info(sender.getName() + " has disabled Server Maintenance");
            }
            TestCore.getInstance().getConfig().set("Server.maintenance", !TestCore.getInstance().getConfig().getBoolean("Server.maintenance"));
            TestCore.getInstance().saveConfig();
        } else {
            sender.sendMessage(ColorUI.translate("&4You don't have permissions to execute this command!"));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}