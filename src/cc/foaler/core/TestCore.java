package cc.foaler.core;

import cc.foaler.core.commands.*;
import cc.foaler.core.data.DataManager;
import cc.foaler.core.data.types.ServerData;
import cc.foaler.core.listeners.ConnectionListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Wrapper;
import java.util.Objects;

public class TestCore extends JavaPlugin {

    private static TestCore instance;

    private DataManager dataManager;
    private ServerData serverData;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        dataManager = new DataManager();
        serverData = new ServerData();

        Bukkit.getPluginManager().registerEvents(new ConnectionListener(), this);

        Objects.requireNonNull(getCommand("maintenance")).setExecutor(new MaintenanceCommand());
        Objects.requireNonNull(getCommand("maintenance")).setTabCompleter(new MaintenanceCommand());
        Objects.requireNonNull(getCommand("flight")).setExecutor(new FlightCommand());
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlightCommand());
        Objects.requireNonNull(getCommand("clearinventory")).setExecutor(new ClearInventoryCommand());
        Objects.requireNonNull(getCommand("clear")).setExecutor(new ClearInventoryCommand());
        Objects.requireNonNull(getCommand("ci")).setExecutor(new ClearInventoryCommand());
        Objects.requireNonNull(getCommand("rename")).setExecutor(new RenameCommand());
        Objects.requireNonNull(getCommand("rename")).setTabCompleter(new RenameCommand());
        Objects.requireNonNull(getCommand("vanish")).setExecutor(new VanishCommand());
        Objects.requireNonNull(getCommand("v")).setExecutor(new VanishCommand());
        Objects.requireNonNull(getCommand("teamspeak")).setExecutor(new TeamSpeakCommand());
        Objects.requireNonNull(getCommand("ts")).setExecutor(new TeamSpeakCommand());
        Objects.requireNonNull(getCommand("store")).setExecutor(new StoreCommand());
        Objects.requireNonNull(getCommand("discord")).setExecutor(new DiscordCommand());
        Objects.requireNonNull(getCommand("ds")).setExecutor(new DiscordCommand());
        Objects.requireNonNull(getCommand("warp")).setExecutor(new WarpCommand());
        Objects.requireNonNull(getCommand("createwarp")).setExecutor(new CreateWarpCommand());
        Objects.requireNonNull(getCommand("addwarp")).setExecutor(new CreateWarpCommand());
        Objects.requireNonNull(getCommand("removewarp")).setExecutor(new RemoveWarpCommand());
        Objects.requireNonNull(getCommand("delwarp")).setExecutor(new RemoveWarpCommand());
        Objects.requireNonNull(getCommand("copyinventory")).setExecutor(new CopyInventoryCommand());
        Objects.requireNonNull(getCommand("copyinv")).setExecutor(new CopyInventoryCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public ServerData getServerData() {
        return serverData;
    }

    public static TestCore getInstance() {
        return instance;
    }
}
