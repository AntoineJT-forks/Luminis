package fr.alkadev.luminis.polls;

import java.awt.*;
import java.util.Arrays;

public enum PollColor {

    WHITE(Color.WHITE, "WHITE"),
    GREEN(Color.GREEN, "GREEN"),
    RED(Color.RED, "RED"),
    YELLOW(Color.YELLOW, "YELLOW"),
    CYAN(Color.CYAN, "CYAN"),
    GRAY(Color.GRAY, "GRAY"),
    MAGENTA(Color.MAGENTA, "MAGENTA"),
    ORANGE(Color.ORANGE, "ORANGE"),
    BLACK(Color.BLACK, "BLACK"),
    PINK(Color.PINK, "PINK"),
    BLUE(Color.BLUE, "BLUE");

    public final Color color;
    private final String text;

    PollColor(Color color, String text) {
        this.color = color;
        this.text = text;
    }

    public static PollColor fromString(String text) {
        return Arrays.stream(values())
                .filter(pollColor -> pollColor.text.equalsIgnoreCase(text))
                .findAny()
                .orElse(PollColor.WHITE);
    }

}