package org.missions.data.enums;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public enum SS_QuestNPC {

    FARMER_FRED("Fred the Farmer", new Area(new Position[]{
            new Position(3183, 3280, 0),
            new Position(3193, 3280, 0),
            new Position(3193, 3268, 0),
            new Position(3183, 3268, 0)
    })),
    
    SHEEP("Sheep", new Area(3193, 3277, 3213, 3257));

    private final String NPC_NAME;
    private final Area NPC_AREA;

    SS_QuestNPC(String npc_name, Area npc_area) {
        this.NPC_NAME = npc_name;
        this.NPC_AREA = npc_area;
    }

    public String getNPCName() {
        return NPC_NAME;
    }

    public Area getNPCArea() {
        return NPC_AREA;
    }

}
