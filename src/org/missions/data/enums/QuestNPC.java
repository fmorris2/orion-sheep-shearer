package org.missions.data.enums;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public enum QuestNPC {

    FARMER_FRED("Fred the Farmer", new Area(new Position[]{
            new Position(3183, 3280, 0),
            new Position(3193, 3280, 0),
            new Position(3193, 3268, 0),
            new Position(3183, 3268, 0)
    })),
    SHEEP("Sheep", new Area(new Position[]{
            new Position(3193, 3276, 0),
            new Position(3204, 3276, 0),
            new Position(3205, 3275, 0),
            new Position(3209, 3275, 0),
            new Position(3211, 3273, 0),
            new Position(3211, 3270, 0),
            new Position(3212, 3268, 0),
            new Position(3212, 3257, 0),
            new Position(3194, 3257, 0),
            new Position(3193, 3259, 0),
            new Position(3192, 3260, 0),
            new Position(3193, 3262, 0)
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
