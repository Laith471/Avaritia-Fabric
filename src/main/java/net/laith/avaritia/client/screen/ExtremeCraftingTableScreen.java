package net.laith.avaritia.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.screenhandler.ExtremeCraftingTableScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ExtremeCraftingTableScreen extends HandledScreen<ExtremeCraftingTableScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(AvaritiaMod.MOD_ID, "textures/gui/extreme_crafting.png");
    public static final Identifier REI_TEXTURE = new Identifier(AvaritiaMod.MOD_ID, "textures/gui/rei_result_slot.png");

    public ExtremeCraftingTableScreen(ExtremeCraftingTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, Text.of(""));
        this.backgroundWidth = 238;
        this.backgroundHeight = 256;
    }


    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        context.drawTexture(TEXTURE, x, y, 0, 0, 238, 256);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        //super.drawForeground(context, mouseX, mouseY);
    }




    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

}