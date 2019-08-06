package fr.alkadev.smartbot.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TimeParser {

    private static final Map<String, Long> TIME_FORMAT = new HashMap<>();

    static {
        TIME_FORMAT.put("y", 31_536_000_000L);
        TIME_FORMAT.put("M", 2_592_000_000L);
        TIME_FORMAT.put("d", 86_400_000L);
        TIME_FORMAT.put("h", 3_600_000L);
        TIME_FORMAT.put("m", 60_000L);
        TIME_FORMAT.put("s", 1_000L);
        TIME_FORMAT.put("ms", 1L);
    }

    public static long parsePeriod(String parser) {
        Objects.requireNonNull(parser, "Parser cannot be nul");

        char[] chars = parser.toCharArray();

        StringBuilder timeBuilder = new StringBuilder();
        StringBuilder keyBuilder = new StringBuilder();

        long time = 0;

        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i]))
                timeBuilder.append(chars[i]);
            else {
                keyBuilder.append(chars[i]);
                if (i == chars.length - 1 || Character.isDigit(chars[i + 1])) {
                    Long mul = TIME_FORMAT.get(keyBuilder.toString());

                    if (mul == null)
                        return 0;

                    time += (Integer.parseInt(timeBuilder.toString()) * mul);

                    timeBuilder = new StringBuilder();
                    keyBuilder = new StringBuilder();
                }
            }
        }

        if (keyBuilder.length() > 0 || timeBuilder.length() > 0)
            return 0;

        return time;
    }

}
