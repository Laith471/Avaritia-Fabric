package net.laith.avaritia.util;

import net.minecraft.util.Formatting;
import net.minecraft.util.Util;

public class TextUtil {

    private static final Formatting[] fabulousness = new Formatting[]{Formatting.RED, Formatting.GOLD, Formatting.YELLOW, Formatting.GREEN, Formatting.AQUA, Formatting.BLUE, Formatting.LIGHT_PURPLE};
    private static final Formatting[] sanic = new Formatting[]{Formatting.BLUE, Formatting.BLUE, Formatting.BLUE, Formatting.BLUE, Formatting.WHITE, Formatting.BLUE, Formatting.WHITE, Formatting.WHITE, Formatting.BLUE, Formatting.WHITE,
            Formatting.WHITE, Formatting.BLUE, Formatting.RED, Formatting.WHITE, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY,
            Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY};

    public static String makeFabulous(String input) {
        return ludicrousFormatting(input, fabulousness, 80.0, 1, 1);
    }

    public static String makeSANIC(String input) {
        return ludicrousFormatting(input, sanic, 50.0, 2, 1);
    }

    public static String ludicrousFormatting(String input, Formatting[] colours, double delay, int step, int posstep) {
        StringBuilder sb = new StringBuilder(input.length() * 3);
        if (delay <= 0) {
            delay = 0.001;
        }

        int offset = (int) Math.floor(Util.getMeasuringTimeMs() / delay) % colours.length;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            int col = ((i * posstep) + colours.length - offset) % colours.length;

            sb.append(colours[col].toString());
            sb.append(c);
        }

        return sb.toString();
    }


}