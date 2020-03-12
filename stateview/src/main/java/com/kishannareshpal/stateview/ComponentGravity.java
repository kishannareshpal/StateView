package com.kishannareshpal.stateview;

public enum ComponentGravity {
    LEFT(0),
    CENTER(1),
    RIGHT(2);

    private int id;
    ComponentGravity(int id) {
        this.id = id;
    }

    static ComponentGravity fromId(int id) {
        for (ComponentGravity componentGravity : values()) {
            if (componentGravity.id == id) return componentGravity;
        }
        throw new IllegalArgumentException("There is no ComponentGravity matching the id: " + id + ". Please check the ComponentGravity values using #values() method to find all available ids.");
    }

    public int getId() {
        return this.id;
    }
}
