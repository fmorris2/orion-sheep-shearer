package org.missions.tasks;

import org.missions.OrionSS;
import org.missions.data.enums.QuestObject;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.RS2Widget;
import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public class SpinWool extends Task<OrionSS> {

    private RS2Object spinning_wheel;
    private final int SPINNING_WIDGET = 459;
    private final int SPINNING_WIDGET_CHILD = 101;
    private final int ENTER_AMOUNT = 162;
    private final int ENTER_AMOUNT_CHILD = 33;

    public SpinWool(OrionSS mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(179) == 1 && inventory.getAmount(QuestObject.BALL_OF_WOOL.getItemID()) + inventory.getAmount(QuestObject.WOOL.getItemID()) >= 20 && inventory.getAmount(QuestObject.WOOL.getItemID()) > 0;
    }

    @Override
    public void execute() {
        spinning_wheel = objects.closest(QuestObject.SPINNING_WHEEL.getObjectArea().setPlane(1), QuestObject.SPINNING_WHEEL.getObjectIDs());
        if (spinning_wheel != null && map.canReach(spinning_wheel)) {
            spinWool();
        } else {
            if (walkUtils.walkToArea(QuestObject.SPINNING_WHEEL.getObjectArea().setPlane(1), () -> {
                spinning_wheel = objects.closest(QuestObject.SPINNING_WHEEL.getObjectArea().setPlane(1), QuestObject.SPINNING_WHEEL.getObjectIDs());
                return spinning_wheel != null && spinning_wheel.isVisible() && map.canReach(spinning_wheel);
            })) {
                Timing.waitCondition(() -> npcs.closest(QuestObject.SPINNING_WHEEL.getObjectArea().setPlane(1), QuestObject.SPINNING_WHEEL.getObjectIDs()) != null, 150, random(2000, 2500));
            }
        }
    }

    private void spinWool() {
        if (myPlayer().isMoving() || myPlayer().getAnimation() != -1)
            return;

        final RS2Widget ENTER_AMOUNT_WIDGET = widgets.get(ENTER_AMOUNT, ENTER_AMOUNT_CHILD);
        if (ENTER_AMOUNT_WIDGET != null && ENTER_AMOUNT_WIDGET.isVisible()) {
            if (keyboard.typeString(Integer.toString(random(20, 99))))
                Timing.waitCondition(() -> myPlayer().isAnimating(), 150, random(2000, 2500));
        } else if (widgets.get(SPINNING_WIDGET, SPINNING_WIDGET_CHILD) != null) {
            if (widgets.interact(SPINNING_WIDGET, SPINNING_WIDGET_CHILD, "Make X"))
                Timing.waitCondition(() -> widgets.get(ENTER_AMOUNT, ENTER_AMOUNT_CHILD) != null, 150, random(1500, 2000));
        } else {
            if (spinning_wheel.interact("Spin"))
                Timing.waitCondition(() -> widgets.get(SPINNING_WIDGET, SPINNING_WIDGET_CHILD) != null, 150, random(2000, 2500));
        }
    }

    @Override
    public String toString() {
        return "Spinning wool";
    }
}

