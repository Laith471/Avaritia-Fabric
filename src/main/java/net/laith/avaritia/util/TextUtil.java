package net.laith.avaritia.util;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class TextUtil {

    private static final ChatFormatting[] fabulousness = new ChatFormatting[]{ChatFormatting.RED, ChatFormatting.GOLD, ChatFormatting.YELLOW, ChatFormatting.GREEN, ChatFormatting.AQUA, ChatFormatting.BLUE, ChatFormatting.LIGHT_PURPLE};
    private static final ChatFormatting[] sanic = new ChatFormatting[]{ChatFormatting.YELLOW, ChatFormatting.YELLOW, ChatFormatting.YELLOW, ChatFormatting.YELLOW, ChatFormatting.WHITE, ChatFormatting.YELLOW, ChatFormatting.WHITE, ChatFormatting.WHITE, ChatFormatting.YELLOW, ChatFormatting.WHITE,
            ChatFormatting.WHITE, ChatFormatting.YELLOW, ChatFormatting.RED, ChatFormatting.WHITE, ChatFormatting.GRAY, ChatFormatting.GRAY, ChatFormatting.GRAY, ChatFormatting.GRAY, ChatFormatting.GRAY, ChatFormatting.GRAY, ChatFormatting.GRAY, ChatFormatting.GRAY, ChatFormatting.GRAY, ChatFormatting.GRAY,
            ChatFormatting.GRAY, ChatFormatting.GRAY, ChatFormatting.GRAY, ChatFormatting.GRAY, ChatFormatting.GRAY, ChatFormatting.GRAY};
    private static final ChatFormatting[] demonic = new ChatFormatting[] {ChatFormatting.BLACK, ChatFormatting.DARK_RED, ChatFormatting.RED,  ChatFormatting.YELLOW, ChatFormatting.RED, ChatFormatting.DARK_RED};
    private static final ChatFormatting[] angelic = new ChatFormatting[] {ChatFormatting.DARK_PURPLE, ChatFormatting.LIGHT_PURPLE, ChatFormatting.DARK_AQUA,  ChatFormatting.AQUA, ChatFormatting.YELLOW, ChatFormatting.WHITE};
    private static final ChatFormatting[] fairiec = new ChatFormatting[] {ChatFormatting.DARK_PURPLE, ChatFormatting.BLUE, ChatFormatting.AQUA,  ChatFormatting.DARK_AQUA, ChatFormatting.DARK_BLUE, ChatFormatting.BLUE};


    public static String makeFabulous(String input) {
        return ludicrousChatFormatting(input, fabulousness, 80.0,  1);
    }

    public static String makeFairiec(String input) {
        return ludicrousChatFormatting(input, fairiec, 80.0,  1);
    }

    public static String makeDemonic(String input) {
        return ludicrousChatFormatting(input, demonic, 80,  1);
    }

    public static String makeAngelic(String input) {
        return ludicrousChatFormatting(input, angelic, 80,  1);
    }

    public static String makeSANIC(String input) {
        return ludicrousChatFormatting(input, sanic, 50.0,  1);
    }

    public static String ludicrousChatFormatting(String input, ChatFormatting[] colours, double delay, int posstep) {
        StringBuilder sb = new StringBuilder(input.length() * 3);
        if (delay <= 0) {
            delay = 0.001;
        }

        int offset = (int) Math.floor(Util.getMillis() / delay) % colours.length;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            int col = ((i * posstep) + colours.length - offset) % colours.length;

            sb.append(colours[col].toString());
            sb.append(c);
        }

        return sb.toString();
    }


    public static void armorTooltip(ItemStack stack, Item item, String SF, String SF1, List<Component> list) {
        if (stack.getItem() == item) {
            int s = 0;
            if(stack.isEnchanted()) {
                List a = stack.getEnchantmentTags().stream().toList();
                s = a.size();
            }
            list.add(3 + s,  Component.nullToEmpty(SF + ChatFormatting.BLUE + " " + SF1));
            return;
        }
    }
}