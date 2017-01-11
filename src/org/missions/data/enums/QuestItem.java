package org.missions.data.enums;

import org.osbot.rs07.api.map.Area;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public enum QuestItem {

    SHEAR("N/A", -1, new int[]{-1}, new Area(new int[][]{
            {3264, 3374},
            {3274, 3374},
            {3273, 3364},
            {3264, 3365}
    })),
    WOOL(null, -1, null, null),
    BALL_OF_WOOL(null, -1, null, null);

    private final String ACTION;
    private final int ITEM_ID;
    private final int[] OBJECT_IDS;
    private final Area OBJECT_AREA;

    QuestItem(String action, int item_id, int[] object_ids, Area object_area) {
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
