package cc.foaler.core.data;

import cc.foaler.core.data.types.PlayerData;
import cc.foaler.core.data.types.ServerData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DataManager {

    private Map<UUID, PlayerData> dataMap = new HashMap<>();

    public DataManager() {

    }

    public Map<UUID, PlayerData> getDataMap() {
        return dataMap;
    }
}
