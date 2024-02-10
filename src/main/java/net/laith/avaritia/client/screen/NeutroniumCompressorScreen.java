package net.laith.avaritia.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.screenhandler.NeutroniumCompressorScreenHandler;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class NeutroniumCompressorScreen extends AbstractContainerScreen<NeutroniumCompressorScreenHandler> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(AvaritiaMod.MOD_ID, "textures/gui/neutronium_compressor.png");
    public static final ResourceLocation REI_TEXTURE = new ResourceLocation(AvaritiaMod.MOD_ID, "textures/gui/neutronium_compressor_rei.png");
    NeutroniumCompressorScreenHandler screenHandler;

    public NeutroniumCompressorScreen(NeutroniumCompressorScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, Component.literal("Neutronium Compressor"));
        screenHandler = handler;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, 176, 166);
        guiGraphics.blit(TEXTURE, this.leftPos + 90, this.topPos + 35, 176, 16, 16, menu.getScaledProgress());
        showText(guiGraphics);
    }

    private void showText(GuiGraphics guiGraphics) {
        if(menu.isCrafting()) {
            guiGraphics.drawString(font, menu.getProgress() + " / " + menu.getCost(), this.leftPos + 62, this.topPos + 60, 4210752, false);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = (imageWidth - font.width(title)) / 2;
    }
}
