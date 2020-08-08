package cc.foaler.core.commands;

import cc.foaler.core.TestCore;
import cc.foaler.core.utils.ColorUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission("testcore.commands.store") || sender.hasPermission("testcore.*") || sender.isOp()) {
            if(TestCore.getInstance().getConfig().getBoolean("Settings.media.store.enabled") == true) {
                sender.sendMessage(ColorUI.translate("&eStore&7: &f" + TestCore.getInstance().getConfig()
                        .getString("Settings.media.store.message")));
            } else {
                sender.sendMessage(ColorUI.translate("&eWe're currently not supporting any Store!"));
            }
        } else {
            sender.sendMessage(ColorUI.translate("&4You don't have permissions to execute this command!"));
        }
        return false;
    }
}
