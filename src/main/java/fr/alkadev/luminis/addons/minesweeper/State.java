package fr.alkadev.luminis.addons.minesweeper;

import java.util.Arrays;

public enum State {

    ZERO(0, "||:zero:|| "),
    ONE(1, "||:one:|| "),
    TWO(2, "||:two:|| "),
    THREE(3, "||:three:|| "),
    FOUR(4, "||:four:|| "),
    FIVE(5, "||:five:|| "),
    SIX(6, "||:six:|| "),
    SEVEN(7, "||:seven:|| "),
    EIGHT(8, "||:eight:|| "),
    BOMB(-1,"||:bomb:|| ");

    private final int id;
    private final String emote;

    State(int id, String emote) {
        this.id = id;
        this.emote = emote;
    }

    // This method has a bad impact on performances use fromBombCount() instead
    @Deprecated
    public static State from(int id) {
        return Arrays.stream(values())
                .filter(state -> state.id == id)
                .findAny()
                .orElse(State.BOMB);
    }

    public static State fromBombCount(int ordinal) {
        final State[] values = values();
        return (ordinal > 0 && ordinal < values.length) ? values[ordinal] : BOMB;
    }

    @Override
    public String toString() {
        return this.emote;
    }
}
