package org.missions.data;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public class Vars {

    public static Vars vars;

    public static Vars get() {
        return vars == null ? vars = new Vars() : vars;
    }

    public static void reset() {
        vars = null;
    }

}

