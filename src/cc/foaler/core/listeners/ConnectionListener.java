package cc.foaler.core.listeners;

import cc.foaler.core.TestCore;
import cc.foaler.core.data.types.PlayerData;
import cc.foaler.core.utils.ColorUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);

        TestCore.getInstance().getDataManager().getDataMap().put(player.getUniqueId(), new PlayerData(player.getUniqueId()));
        PlayerData data = TestCore.getInstance().getDataManager().getDataMap().get(player.getUniqueId());

        setupMaintenance(player);
        setupVanish(data);

        Bukkit.broadcastMessage(ColorUI.translate("&7(&a+&7) &e" + player.getName()));
    }

    public void setupMaintenance(Player player) {
        if(!TestCore.getInstance().getConfig().isConfigurationSection("Server.maintenance")) {
            TestCore.getInstance().getServerData().setMaintenance(TestCore.getInstance().getConfig().getBoolean("Server.maintenance"));

            if(TestCore.getInstance().getServerData().isMaintenanceActivated()) {
                player.kickPlayer("Maintenance");
            }
        } else {
            TestCore.getInstance().getConfig().set("Server.maintenance", false);
            TestCore.getInstance().saveConfig();
        }
    }

    public void setupVanish(PlayerData data) {
        if(data.getPlayer().hasPermission("testcore.commands.vanish") || data.getPlayer().hasPermission("testcore.*") || data.getPlayer().isOp()) {
            data.setVanished(data.getPlayer(), true);
        } else data.setVanished(data.getPlayer(), false);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(null);
        PlayerData data = TestCore.getInstance().getDataManager().getDataMap().get(player.getUniqueId());

        data.setVanished(player, false);
        TestCore.getInstance().getDataManager().getDataMap().remove(player.getUniqueId());

        Bukkit.broadcastMessage(ColorUI.translate("&7(&4-&7) &e" + player.getName()));
    }
}
