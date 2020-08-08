package cc.foaler.core.commands;

import cc.foaler.core.utils.ColorUI;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class RenameCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(player.hasPermission("testcore.commands.rename") || player.hasPermission("testcore.*") || player.isOp()) {
                if(args.length > 0) {
                    if(player.getItemInHand().getType() != Material.AIR) {
                        StringBuilder nameBuilder = new StringBuilder();
                        String name = "";
                        for(int i = 0; i != args.length; i++) {
                            nameBuilder.append(args[i]).append(" "); name = nameBuilder.toString();
                        }
                        ItemMeta meta = player.getItemInHand().getItemMeta();
                        meta.setDisplayName(ColorUI.translate("&r" + name));
                        player.getItemInHand().setItemMeta(meta);
                        player.sendMessage(ColorUI.translate("&eYou have renamed &f" + player.getItemInHand().getItemMeta().getDisplayName().trim() + " &eto &r" + name.trim()));
                    }
                } else {
                    player.sendMessage(ColorUI.translate("&eUsage&7: &e/" + cmd.getName() + " &7(&ename&7)"));
                }
            } else {
                player.sendMessage(ColorUI.translate("&4You don't have permissions to execute this command!"));
            }
        } else {
            sender.sendMessage("Only Players!");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
