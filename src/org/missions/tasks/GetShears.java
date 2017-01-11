package org.missions.tasks;

import org.missions.OrionSS;
import org.missions.data.enums.QuestObject;
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
        return configs.get(179) == 1 && inventory.getAmount(QuestObject.BALL_OF_WOOL.getItemID()) < 20 && inventory.getItem(QuestObject.SHEARS.getItemID()) == null;
    }

    @Override
    public void execute() {
        shears = groundItems.closest(QuestObject.SHEARS.getObjectArea(), QuestObject.SHEARS.getItemID());
        if (shears != null && map.canReach(shears)) {
            if (myPlayer().isMoving() || myPlayer().getAnimation() != -1)
                return;

            final Item[] inventory_cache = inventory.getItems();
            if (shears.interact(QuestObject.SHEARS.getAction()))
                Timing.waitCondition(() -> inventory.getItems().length != inventory_cache.length || myPlayer().isMoving(), 150, random(2000, 2500));
        } else {
            if (!QuestObject.SHEARS.getObjectArea().contains(myPlayer())) {
                if (walkUtils.walkToArea(QuestObject.SHEARS.getObjectArea(), () -> {
                    shears = groundItems.closest(QuestObject.SHEARS.getObjectArea(), QuestObject.SHEARS.getItemID());
                    return shears != null && shears.isVisible() && map.canReach(shears);
                })) {
                    Timing.waitCondition(() -> groundItems.closest(QuestObject.SHEARS.getObjectArea(), QuestObject.SHEARS.getItemID()) != null, 150, random(2000, 2500));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Getting shears";
    }
}

