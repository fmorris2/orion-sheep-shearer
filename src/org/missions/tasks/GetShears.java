package org.missions.tasks;

import org.missions.OrionSS;
import org.missions.data.enums.SS_QuestObject;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.Item;
import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public class GetShears extends Task<OrionSS> {

    private GroundItem shears;

    public GetShears(OrionSS mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(179) == 1 && inventory.getAmount(SS_QuestObject.BALL_OF_WOOL.getItemID()) < 20 && inventory.getItem(SS_QuestObject.SHEARS.getItemID()) == null;
    }

    @Override
    public void execute() {
        shears = groundItems.closest(SS_QuestObject.SHEARS.getObjectArea(), SS_QuestObject.SHEARS.getItemID());
        boolean inArea = SS_QuestObject.SHEARS.getObjectArea().contains(myPlayer());
        if (shears != null && inArea) {
            if (myPlayer().isMoving() || myPlayer().getAnimation() != -1)
                return;

            final Item[] inventory_cache = inventory.getItems();
            if (shears.interact(SS_QuestObject.SHEARS.getAction()))
                Timing.waitCondition(() -> inventory.getItems().length != inventory_cache.length || myPlayer().isMoving(), 150, random(2000, 2500));
        } else {
            if (!inArea) {
                if (walkUtils.walkToArea(SS_QuestObject.SHEARS.getObjectArea(), () -> {
                    shears = groundItems.closest(SS_QuestObject.SHEARS.getObjectArea(), SS_QuestObject.SHEARS.getItemID());
                    return shears != null && map.canReach(shears);
                })) {
                    Timing.waitCondition(() -> groundItems.closest(SS_QuestObject.SHEARS.getObjectArea(), SS_QuestObject.SHEARS.getItemID()) != null, 150, random(2000, 2500));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Getting shears";
    }
}

