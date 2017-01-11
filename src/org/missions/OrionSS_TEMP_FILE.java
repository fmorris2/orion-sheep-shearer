package org.missions;

import org.osbot.rs07.script.ScriptManifest;
import viking.framework.mission.Mission;
import viking.framework.paint.VikingPaint;
import viking.framework.script.VikingScript;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Sphiinx on 1/11/2017.
 */
@ScriptManifest(author = "Sphiinx", name = "Orion Sheep Shearer", info = "Completes Sheep Shearer", version = 0.1, logo = "")
public class OrionSS_TEMP_FILE extends VikingScript {

    @Override
    public Queue<Mission> generateMissions() {
        return new LinkedList<>(Collections.singletonList(new OrionSS(this)));
    }

    @Override
    public VikingPaint<?> getVikingPaint() {
        return null;
    }

    @Override
    public boolean isDevBuild() {
        return false;
    }

}

