package org.missions.tasks;

import org.missions.OrionSS;
import org.missions.data.enums.SS_QuestNPC;
import org.missions.data.enums.SS_QuestObject;
import org.osbot.rs07.api.filter.ActionFilter;
import org.osbot.rs07.api.filter.AreaFilter;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.filter.NameFilter;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;

import viking.api.Timing;
import viking.api.filter.VFilters;
import viking.framework.task.Task;

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
        sheep = getValidSheep();
        if (sheep != null) {
            if (myPlayer().isMoving() || myPlayer().getAnimation() != -1)
                return;

            final Item[] inventory_cache = inventory.getItems();
            script.log(this, false, "Clicking sheep...");
            if (iFact.clickNpc("Shear", sheep).execute())
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

    private NPC getValidSheep() {
        final Filter ACTION_FILTER = VFilters.and(new ActionFilter<NPC>("Shear"), VFilters.not(new ActionFilter<>("Shear"), new ActionFilter<NPC>("Talk-to")));
        final Filter<NPC> NAME_FILTER = new NameFilter<>("Sheep");
        final Filter<NPC> AREA_FILTER = new AreaFilter<>(SS_QuestNPC.SHEEP.getNPCArea());
        final Filter SHEEP_FILTER = VFilters.and(AREA_FILTER, VFilters.and(NAME_FILTER, ACTION_FILTER));

        return npcs.closest(SHEEP_FILTER);
    }

    @Override
    public String toString() {
        return "Shearing sheep";
    }
}

