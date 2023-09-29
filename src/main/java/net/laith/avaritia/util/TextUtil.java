package net.laith.avaritia.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;

import java.util.List;

public class TextUtil {

    private static final Formatting[] fabulousness = new Formatting[]{Formatting.RED, Formatting.GOLD, Formatting.YELLOW, Formatting.GREEN, Formatting.AQUA, Formatting.BLUE, Formatting.LIGHT_PURPLE};
    private static final Formatting[] sanic = new Formatting[]{Formatting.YELLOW, Formatting.YELLOW, Formatting.YELLOW, Formatting.YELLOW, Formatting.WHITE, Formatting.YELLOW, Formatting.WHITE, Formatting.WHITE, Formatting.YELLOW, Formatting.WHITE,
            Formatting.WHITE, Formatting.YELLOW, Formatting.RED, Formatting.WHITE, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY,
            Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY, Formatting.GRAY};
    private static final Formatting[] demonic = new Formatting[] {Formatting.BLACK, Formatting.DARK_RED, Formatting.RED,  Formatting.YELLOW, Formatting.RED, Formatting.DARK_RED};
    private static final Formatting[] angelic = new Formatting[] {Formatting.DARK_PURPLE, Formatting.LIGHT_PURPLE, Formatting.DARK_AQUA,  Formatting.AQUA, Formatting.YELLOW, Formatting.WHITE};
    private static final Formatting[] fairiec = new Formatting[] {Formatting.DARK_PURPLE, Formatting.BLUE, Formatting.AQUA,  Formatting.DARK_AQUA, Formatting.DARK_BLUE, Formatting.BLUE};


    public static String makeFabulous(String input) {
        return ludicrousFormatting(input, fabulousness, 80.0,  1);
    }

    public static String makeFairiec(String input) {
        return ludicrousFormatting(input, fairiec, 80.0,  1);
    }

    public static String makeDemonic(String input) {
        return ludicrousFormatting(input, demonic, 80,  1);
    }

    public static String makeAngelic(String input) {
        return ludicrousFormatting(input, angelic, 80,  1);
    }

    public static String makeSANIC(String input) {
        return ludicrousFormatting(input, sanic, 50.0,  1);
    }

    public static String ludicrousFormatting(String input, Formatting[] colours, double delay, int posstep) {
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


    public static void armorTooltip(ItemStack stack, Item item, String SF, String SF1, List<Text> list) {
        if (stack.getItem() == item) {
            int s = 0;
            if(stack.hasEnchantments()) {
                List a = stack.getEnchantments().stream().toList();
                s = a.size();
            }
            list.add(3 + s,  Text.of(SF + Formatting.BLUE + " " + SF1));
            return;
        }
    }
}