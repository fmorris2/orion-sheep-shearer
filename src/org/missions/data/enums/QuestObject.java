package org.missions.data.enums;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public enum QuestObject {

    SHEARS("Take", 1735, null, new Area(new Position[]{
            new Position(3193, 3276, 0),
            new Position(3193, 3269, 0),
            new Position(3187, 3269, 0),
            new Position(3187, 3276, 0)
    })),
    SPINNING_WHEEL("Spin", -1, new int[]{14889}, new Area(new Position[]{
            new Position(3204, 3217, 1),
            new Position(3213, 3217, 1),
            new Position(3213, 3209, 1),
            new Position(3204, 3209, 1)
    })),
    WOOL(null, 1737, null, null),
    BALL_OF_WOOL(null, 1759, null, null);

    private final String ACTION;
    private final int ITEM_ID;
    private final int[] OBJECT_IDS;
    private final Area OBJECT_AREA;

    QuestObject(String action, int item_id, int[] object_ids, Area object_area) {
        this.ACTION = action;
        this.ITEM_ID = item_id;
        this.OBJECT_IDS = object_ids;
        this.OBJECT_AREA = object_area;
    }

    public String getAction() {
        return ACTION;
    }

    public int getItemID() {
        return ITEM_ID;
    }

    public int[] getObjectIDs() {
        return OBJECT_IDS;
    }

    public Area getObjectArea() {
        return OBJECT_AREA;
    }

}
