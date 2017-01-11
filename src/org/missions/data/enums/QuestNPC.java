package org.missions.data.enums;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public enum QuestNPC {

    FARMER_FRED("N/A", new Area(new Position[]{
            new Position(3154, 3427, 1),
            new Position(3162, 3427, 1),
            new Position(3162, 3424, 1),
            new Position(3154, 3424, 1)
    }));

    private final String NPC_NAME;
    private final Area NPC_AREA;

    QuestNPC(String npc_name, Area npc_area) {
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
