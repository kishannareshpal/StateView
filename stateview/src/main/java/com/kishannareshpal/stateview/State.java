/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.kishannareshpal.stateview;

public enum State {
    NORMAL(0),
    ALTERNATE(1);

    private int id;
    State(int id) {
        this.id = id;
    }

    static State fromId(int id) {
        for (State state : values()) {
            if (state.id == id) return state;
        }
        throw new IllegalArgumentException("There is no State matching the id: " + id + ". To check all possible States ids use #values().");
    }

    public int getId() {
        return this.id;
    }
}
