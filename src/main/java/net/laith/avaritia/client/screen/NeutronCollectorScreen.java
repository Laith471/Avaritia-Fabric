package net.laith.avaritia.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.blockentity.NeutronCollectorBlockEntity;
import net.laith.avaritia.common.screenhandler.NeutronCollectorScreenHandler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.awt.*;

public class NeutronCollectorScreen extends AbstractContainerScreen<NeutronCollectorScreenHandler> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AvaritiaMod.MOD_ID, "textures/gui/neutron_collector.png");
    NeutronCollectorScreenHandler screenHandler;

    public NeutronCollectorScreen(NeutronCollectorScreenHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, Component.literal("Neutron Collector"));
        screenHandler = handler;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, 176, 166);
        String l = "%.2f%%".formatted(100.0F * (float)screenHandler.getProgress() / (float)NeutronCollectorBlockEntity.TIMER);
        guiGraphics.drawString(font, l, this.leftPos + 78, this.topPos + 60, 4210752, false);
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
