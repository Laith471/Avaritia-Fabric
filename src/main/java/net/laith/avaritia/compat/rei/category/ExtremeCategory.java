package net.laith.avaritia.compat.rei.category;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Slot;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.InputIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.laith.avaritia.client.screen.ExtremeCraftingTableScreen;
import net.laith.avaritia.compat.rei.display.extreme.ExtremeCraftingDisplay;
import net.laith.avaritia.compat.rei.ServerREIPlugin;
import net.laith.avaritia.init.ModBlocks;
import net.minecraft.text.Text;

import java.util.List;

public class ExtremeCategory implements DisplayCategory<ExtremeCraftingDisplay<?>>{

    @Override
    public CategoryIdentifier<? extends ExtremeCraftingDisplay<?>> getCategoryIdentifier() {
        return ServerREIPlugin.EXTREME_CRAFTING;
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.EXTREME_CRAFTING_TABLE);
    }

    @Override
    public Text getTitle() {
        return Text.translatable("avaritia.category.rei.extreme_crafting");
    }

    @Override
    public List<Widget> setupDisplay(ExtremeCraftingDisplay<?> display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 81, bounds.getCenterY() - 35);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        List<InputIngredient<EntryStack<?>>> input = display.getInputIngredients(9, 9);
        List<Slot> slots = Lists.newArrayList();
        for (int y = 0; y < 9; y++)
            for (int x = 0; x < 9; x++)
                slots.add(Widgets.createSlot(new Point(startPoint.x + 1 + x * 18, startPoint.y - 64 + y * 18)).markInput());
        for (InputIngredient<EntryStack<?>> ingredient : input) {
            slots.get(ingredient.getIndex()).entries(ingredient.get());
        }
        widgets.add(Widgets.createDrawableWidget((context, mouseX, mouseY, delta) -> {
                    context.drawTexture(ExtremeCraftingTableScreen.REI_TEXTURE, startPoint.x + 42, startPoint.y + 100, 0, 0, 78, 31);
                }));
        widgets.addAll(slots);
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 73, startPoint.y + 109)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        if (display.isShapeless()) {
            widgets.add(Widgets.createShapelessIcon(new Point(startPoint.x + 8, startPoint.y + 127)));
        }
        return widgets;
    }

    @Override
    public int getMaximumDisplaysPerPage() {
        return 1;
    }

    @Override
    public int getDisplayHeight() {
        return 210;
    }

    @Override
    public int getDisplayWidth(ExtremeCraftingDisplay<?> display) {
        return 172;
    }
}
