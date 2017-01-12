package org.missions.tasks;

import org.missions.OrionSS;
import org.missions.data.enums.SS_QuestNPC;
import org.osbot.rs07.api.model.NPC;
import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public class SS_StartQuest extends Task<OrionSS> {


    private NPC farmer_fred;

    public SS_StartQuest(OrionSS mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(179) == 0;
    }

    @Override
    public void execute() {
        farmer_fred = npcs.closest(SS_QuestNPC.FARMER_FRED.getNPCArea(), SS_QuestNPC.FARMER_FRED.getNPCName());
        if (farmer_fred != null && map.canReach(farmer_fred)) {
            iFact.dialogue("Talk-to", SS_QuestNPC.FARMER_FRED.getNPCName(), 20, 1).execute();
        } else {
            if (walkUtils.walkToArea(SS_QuestNPC.FARMER_FRED.getNPCArea(), () -> {
                farmer_fred = npcs.closest(SS_QuestNPC.FARMER_FRED.getNPCArea(), SS_QuestNPC.FARMER_FRED.getNPCName());
                return farmer_fred != null && farmer_fred.isVisible() && map.canReach(farmer_fred);
            })) {
                Timing.waitCondition(() -> npcs.closest(SS_QuestNPC.FARMER_FRED.getNPCArea(), SS_QuestNPC.FARMER_FRED.getNPCName()) != null, 150, random(2000, 2500));
            }
        }
    }

    @Override
    public String toString() {
        return "Starting Sheep Shearer";
    }

}

