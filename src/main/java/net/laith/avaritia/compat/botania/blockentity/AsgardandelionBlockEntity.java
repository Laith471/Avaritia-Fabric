package net.laith.avaritia.compat.botania.blockentity;

import net.laith.avaritia.compat.botania.Botania;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.block_entity.GeneratingFlowerBlockEntity;
import vazkii.botania.api.block_entity.RadiusDescriptor;

public class AsgardandelionBlockEntity extends GeneratingFlowerBlockEntity {

    public AsgardandelionBlockEntity(BlockPos pos, BlockState state) {
        super(Botania.ASGARDANDELION_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void tickFlower() {
        super.tickFlower();
        addMana(Integer.MAX_VALUE);
    }

    @Override
    public int getMaxMana() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getColor() {
        return 0x11FF00;
    }

    @Override
    public @Nullable RadiusDescriptor getRadius() {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), 3);
    }
}
