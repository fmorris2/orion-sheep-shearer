package org.missions;

import org.missions.tasks.*;
import viking.framework.goal.GoalList;
import viking.framework.mission.Mission;
import viking.framework.script.VikingScript;
import viking.framework.task.TaskManager;

public class OrionSS extends Mission {

    private final TaskManager<OrionSS> TASK_MANAGER = new TaskManager<>(this);

    public OrionSS(VikingScript script) {
        super(script);
    }

    @Override
    public boolean canEnd() {
        return configs.get(179) >= 21;
    }

    @Override
    public String getMissionName() {
        return null;
    }

    @Override
    public String getCurrentTaskName() {
        return TASK_MANAGER.getStatus();
    }

    @Override
    public String getEndMessage() {
        return null;
    }

    @Override
    public GoalList getGoals() {
        return null;
    }

    @Override
    public String[] getMissionPaint() {
        return null;
    }

    @Override
    public int execute() {
        TASK_MANAGER.loop();
        return 150;
    }

    @Override
    public void onMissionStart() {
        TASK_MANAGER.addTask(new SS_DepositItems(this), new SS_StartQuest(this), new GetShears(this), new ShearSheep(this), new SpinWool(this), new SS_FinishQuest(this));
    }

    @Override
    public void resetPaint() {
    }

}
