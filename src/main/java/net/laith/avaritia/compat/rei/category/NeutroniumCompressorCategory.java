package net.laith.avaritia.compat.rei.category;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Label;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.InputIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.laith.avaritia.client.screen.ExtremeCraftingTableScreen;
import net.laith.avaritia.client.screen.NeutroniumCompressorScreen;
import net.laith.avaritia.compat.rei.ServerREIPlugin;
import net.laith.avaritia.compat.rei.display.NeutroniumCompressorDisplay;
import net.laith.avaritia.init.ModBlocks;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.List;

public class NeutroniumCompressorCategory implements DisplayCategory<NeutroniumCompressorDisplay> {
    @Override
    public CategoryIdentifier<? extends NeutroniumCompressorDisplay> getCategoryIdentifier() {
        return ServerREIPlugin.NEUTRONIUM_COMPRESSOR;
    }

    @Override
    public Text getTitle() {
        return Text.translatable("avaritia.category.rei.neutronium_compressor");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.NEUTRONIUM_COMPRESSOR);
    }

    @Override
    public List<Widget> setupDisplay(NeutroniumCompressorDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 81, bounds.getCenterY() - 35);
        List<Widget> widgets = Lists.newArrayList();

        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createTexturedWidget(NeutroniumCompressorScreen.REI_TEXTURE, startPoint.x + 25, startPoint.y + 15, 0, 0, 112, 40));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 32, startPoint.y + 27)).entries(display.getInputEntries().get(0)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 110, startPoint.y + 27)).entries(display.getOutputEntries().get(0)).markOutput().disableBackground());

        widgets.add(Widgets.createDrawableWidget((context, mouseX, mouseY, delta) -> {
            context.drawTexture(NeutroniumCompressorScreen.TEXTURE, startPoint.x + 83, startPoint.y + 27, 176, 16, 16, (int) ((System.currentTimeMillis() / display.getCost()) % 15 ));
        }));

        widgets.add(Widgets.createLabel(new Point(startPoint.x + 55, startPoint.y + 39), Text.of(String.valueOf(display.getCost()))));
        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 50;
    }

    @Override
    public int getDisplayWidth(NeutroniumCompressorDisplay display) {
        return 140;
    }

    @Override
    public int getMaximumDisplaysPerPage() {
        return 3;
    }
}
