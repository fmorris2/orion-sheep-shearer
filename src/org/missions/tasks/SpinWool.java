package org.missions.tasks;

import org.missions.OrionSS;
import org.missions.data.enums.SS_QuestObject;
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
    private long last_spinning_time;

    public SpinWool(OrionSS mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(179) == 1 && inventory.getAmount(SS_QuestObject.BALL_OF_WOOL.getItemID()) + inventory.getAmount(SS_QuestObject.WOOL.getItemID()) >= 20 && inventory.getAmount(SS_QuestObject.WOOL.getItemID()) > 0;
    }

    @Override
    public void execute() {
        spinning_wheel = objects.closest(SS_QuestObject.SPINNING_WHEEL.getObjectArea().setPlane(1), SS_QuestObject.SPINNING_WHEEL.getObjectIDs());
        if (spinning_wheel != null && map.canReach(spinning_wheel)) {
            spinWool();
        } else {
            if (walkUtils.walkToArea(SS_QuestObject.SPINNING_WHEEL.getObjectArea().setPlane(1), () -> {
                spinning_wheel = objects.closest(SS_QuestObject.SPINNING_WHEEL.getObjectArea().setPlane(1), SS_QuestObject.SPINNING_WHEEL.getObjectIDs());
                return spinning_wheel != null && map.canReach(spinning_wheel);
            })) {
                Timing.waitCondition(() -> npcs.closest(SS_QuestObject.SPINNING_WHEEL.getObjectArea().setPlane(1), SS_QuestObject.SPINNING_WHEEL.getObjectIDs()) != null, 150, random(2000, 2500));
            }
        }
    }

    private void spinWool() {
        if (myPlayer().getAnimation() != -1) {
            last_spinning_time = Timing.currentMs();
        } else if (Timing.timeFromMark(last_spinning_time) > 3000) {
            final RS2Widget ENTER_AMOUNT_WIDGET = widgets.get(ENTER_AMOUNT, ENTER_AMOUNT_CHILD);
            if (ENTER_AMOUNT_WIDGET != null && ENTER_AMOUNT_WIDGET.isVisible()) {
                if (keyboard.typeString(Integer.toString(random(20, 99))))
                    Timing.waitCondition(() -> myPlayer().isAnimating(), 150, random(2000, 2500));
            } else if (widgets.get(SPINNING_WIDGET, SPINNING_WIDGET_CHILD) != null && widgets.get(SPINNING_WIDGET, SPINNING_WIDGET_CHILD).isVisible()) {
                if (widgets.interact(SPINNING_WIDGET, SPINNING_WIDGET_CHILD, "Make X"))
                    Timing.waitCondition(() -> widgets.get(ENTER_AMOUNT, ENTER_AMOUNT_CHILD) != null && widgets.get(ENTER_AMOUNT, ENTER_AMOUNT_CHILD).isVisible(), 150, random(2000, 2500));
            } else {
                if (spinning_wheel.interact("Spin"))
                    Timing.waitCondition(() -> widgets.get(SPINNING_WIDGET, SPINNING_WIDGET_CHILD) != null && widgets.get(SPINNING_WIDGET, SPINNING_WIDGET_CHILD).isVisible(), 150, random(4000, 5000));
            }
        }
    }

    @Override
    public String toString() {
        return "Spinning wool";
    }
}

