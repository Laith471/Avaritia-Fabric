package net.laith.avaritia.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.blockentity.NeutronCollectorBlockEntity;
import net.laith.avaritia.common.screenhandler.NeutronCollectorScreenHandler;
import net.laith.avaritia.common.screenhandler.NeutroniumCompressorScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class NeutroniumCompressorScreen extends HandledScreen<NeutroniumCompressorScreenHandler> {
    public static final Identifier TEXTURE = new Identifier(AvaritiaMod.MOD_ID, "textures/gui/neutronium_compressor.png");
    public static final Identifier REI_TEXTURE = new Identifier(AvaritiaMod.MOD_ID, "textures/gui/neutronium_compressor_rei.png");
    NeutroniumCompressorScreenHandler screenHandler;

    public NeutroniumCompressorScreen(NeutroniumCompressorScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, Text.of("Neutronium Compressor"));
        screenHandler = (NeutroniumCompressorScreenHandler) handler;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        context.drawTexture(TEXTURE, x, y, 0, 0, 176, 166);
        context.drawTexture(TEXTURE, x+ 90, y + 35, 176, 16, 16, handler.getScaledProgress());
        showText(context, delta, mouseX, mouseY);
    }

    private void showText(DrawContext context, float delta, int mouseX, int mouseY) {
        if(handler.isCrafting()) {
            context.drawText(textRenderer, handler.getProgress() + " / " + handler.getCost(), x + 62, y + 60, 4210752, false);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
