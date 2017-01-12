package org.missions.tasks;

import org.missions.OrionSS;
import org.missions.data.enums.SS_QuestNPC;
import org.missions.data.enums.SS_QuestObject;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import viking.api.Timing;
import viking.framework.task.Task;

import java.util.Arrays;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public class ShearSheep extends Task<OrionSS> {

    private NPC sheep;

    public ShearSheep(OrionSS mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(179) == 1 && inventory.getAmount(SS_QuestObject.BALL_OF_WOOL.getItemID()) + inventory.getAmount(SS_QuestObject.WOOL.getItemID()) < 20 && inventory.getItem(SS_QuestObject.SHEARS.getItemID()) != null;
    }

    @SuppressWarnings("unchecked")
	@Override
    public void execute() {
        sheep = npcs.closest((Filter<NPC>) npc -> {
            if (npc == null)
                return false;

            final String NPC_NAME = npc.getName();
            if (NPC_NAME == null)
                return false;

            if (!SS_QuestNPC.SHEEP.getNPCArea().contains(npc))
                return false;

            final String[] ACTIONS = npc.getActions();
            if (Arrays.asList(ACTIONS).contains("Talk-to"))
                return false;

            return true;
        });
        if (sheep != null && map.canReach(sheep)) {
            if (myPlayer().isMoving() || myPlayer().getAnimation() != -1)
                return;

            final Item[] inventory_cache = inventory.getItems();
            if (sheep.interact("Shear"))
                Timing.waitCondition(() -> inventory.getItems().length != inventory_cache.length || myPlayer().isMoving() || myPlayer().isAnimating(), 150, random(2000, 2500));
        } else {
            if (walkUtils.walkToArea(SS_QuestNPC.SHEEP.getNPCArea(), () -> {
                sheep = npcs.closest(SS_QuestNPC.SHEEP.getNPCArea(), SS_QuestNPC.SHEEP.getNPCName());
                return sheep != null && sheep.isVisible() && map.canReach(sheep);
            })) {
                Timing.waitCondition(() -> npcs.closest(SS_QuestNPC.SHEEP.getNPCArea(), SS_QuestNPC.SHEEP.getNPCName()) != null, 150, random(2000, 2500));
            }
        }
    }

    @Override
    public String toString() {
        return "Shearing sheep";
    }
}

