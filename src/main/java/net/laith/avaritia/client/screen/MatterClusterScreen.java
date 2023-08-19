package net.laith.avaritia.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.blockentity.NeutronCollectorBlockEntity;
import net.laith.avaritia.common.screenhandler.MatterClusterScreenHandler;
import net.laith.avaritia.common.screenhandler.NeutronCollectorScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MatterClusterScreen extends HandledScreen<MatterClusterScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(AvaritiaMod.MOD_ID, "textures/gui/matter_cluster_gui.png");

    protected boolean isClickOutsideBounds(double mouseX, double mouseY, int left, int top, int button) {
        return mouseX < (double)left || mouseY < (double)top || mouseX >= (double)(left + this.backgroundWidth) || mouseY >= (double)(top + this.backgroundHeight);
    }

    public MatterClusterScreen(MatterClusterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, Text.of("One Chance!"));
        this.backgroundWidth = 184;
        this.backgroundHeight = 204;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        context.drawTexture(TEXTURE, x, y, 0, 0, 184, 204);
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

    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        //super.drawForeground(context, mouseX, mouseY);
    }
}
