package cc.foaler.core.data.types;

import cc.foaler.core.data.objects.ClassType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {

    private final UUID uuid;
    private Player player;

    public boolean vanished;

    private ClassType classType;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;

        this.player = Bukkit.getPlayer(uuid);
    }

    public boolean isVanished() {
        return vanished;
    }

    public void setVanished(Player user, boolean vanished) {
        if(vanished) {
            this.vanished = true;

            for(Player online : Bukkit.getServer().getOnlinePlayers()) {
                online.hidePlayer(player);

                player.spigot().setCollidesWithEntities(false);
            }
        } else {
            this.vanished = false;

            for(Player online : Bukkit.getServer().getOnlinePlayers()) {
                online.showPlayer(player);
            }

            player.spigot().setCollidesWithEntities(true);
            
        }
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public Player getPlayer() {
        return player;
    }
}
