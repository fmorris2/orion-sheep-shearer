package org.missions.data;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public class SS_Vars {

    public static SS_Vars vars;

    public static SS_Vars get() {
        return vars == null ? vars = new SS_Vars() : vars;
    }

    public static void reset() {
        vars = null;
    }

}

