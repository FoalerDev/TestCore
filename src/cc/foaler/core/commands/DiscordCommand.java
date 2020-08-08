package cc.foaler.core.commands;

import cc.foaler.core.TestCore;
import cc.foaler.core.utils.ColorUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission("testcore.commands.discord") || sender.hasPermission("testcore.*") || sender.isOp()) {
            if(TestCore.getInstance().getConfig().getBoolean("Settings.media.discord.enabled") == true) {
                sender.sendMessage(ColorUI.translate("&eDiscord&7: &f" + TestCore.getInstance().getConfig()
                        .getString("Settings.media.discord.message")));
            } else {
                sender.sendMessage(ColorUI.translate("&eWe're currently not supporting any Discord!"));
            }
        } else {
            sender.sendMessage(ColorUI.translate("&4You don't have permissions to execute this command!"));
        }
        return false;
    }
}
