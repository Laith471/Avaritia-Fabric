package net.laith.avaritia.common.blockentity;

import com.google.gson.JsonParser;
import net.laith.avaritia.common.block.NeutroniumCompressorBlock;
import net.laith.avaritia.common.recipe.NeutroniumCompressorRecipe;
import net.laith.avaritia.common.screenhandler.NeutroniumCompressorScreenHandler;
import net.laith.avaritia.init.ModBlockEntities;
import net.laith.avaritia.util.blockentities.NeutroniumHelper;
import net.laith.avaritia.util.inventory.ImplementedSidedInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class NeutroniumCompressorBlockEntity extends BlockEntity implements MenuProvider, ImplementedSidedInventory {
    public final NonNullList<ItemStack> inventory;
    public NeutroniumCompressorRecipe recipe;
    public int progress;
    public int cost;
    public Optional match;
    public Ingredient cachedInput;
    public ItemStack cachedOutput;
    public int cachedCost;
    public ResourceLocation cachedId;
    protected final ContainerData containerData;


    public NeutroniumCompressorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NEUTRONIUM_COMPRESSOR_BLOCK_ENTITY, pos, state);
        this.inventory = NonNullList.withSize(2, ItemStack.EMPTY);
        this.containerData = new ContainerData() {
            @Override
            public int get(int index) {
                switch (index) {
                    case 0:
                        return NeutroniumCompressorBlockEntity.this.progress;
                    case 1:
                        return NeutroniumCompressorBlockEntity.this.cost;
                    default:
                        return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        NeutroniumCompressorBlockEntity.this.progress = value;
                        break;
                    case 1:
                        NeutroniumCompressorBlockEntity.this.cost = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

        };
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new NeutroniumCompressorScreenHandler(i, inventory, this, this.containerData);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, NeutroniumCompressorBlockEntity entity) {
        if (level.isClientSide) {
            return;
        }

        SimpleContainer simpleContainer = new SimpleContainer(entity.getContainerSize());
        for (int i = 0; i < entity.getContainerSize(); i++) {
            simpleContainer.setItem(i, entity.getItem(i));
        }
        if (entity.inventory.get(1).getCount() < 64) {
            if (entity.cachedInput != null && entity.cachedId != null && entity.cachedOutput.getItem() != Items.AIR && entity.cachedCost != 0) {
                // if is true we set the match and the recipe to the cached value
                entity.recipe = new NeutroniumCompressorRecipe(entity.cachedId, entity.cachedInput, entity.cachedCost, entity.cachedOutput);
                entity.match = Optional.of(new NeutroniumCompressorRecipe(entity.cachedId, entity.cachedInput, entity.cachedCost, entity.cachedOutput));
            }

            ItemStack outputStack = entity.getItem(1);
            if (outputStack.isEmpty() && entity.progress == 0) {
                entity.recipe = null; // Reset the current recipe if the output slot is empty
            }

            if (entity.recipe == null) {
                // we check if the recipe is null and if we get a match
                entity.match = level.getRecipeManager().getRecipeFor(NeutroniumCompressorRecipe.Type.INSTANCE, simpleContainer, entity.getLevel());
            }

            if (entity.progress > 0) {
                level.setBlockAndUpdate(blockPos, state.setValue(NeutroniumCompressorBlock.ACTIVE, true));
            }
            if (entity.progress == 0) {
                level.setBlockAndUpdate(blockPos, state.setValue(NeutroniumCompressorBlock.ACTIVE, false));
            }

            // if there is a match and its present it will be true. we take the match from either the cachedRecipe or the new recipe.
            if (entity.match != null && entity.match.isPresent()) {
                //this field will be functional only if we get a match from the new recipe
                entity.recipe = (NeutroniumCompressorRecipe) entity.match.get();
                // here we set the cached value from any recipe
                NeutroniumHelper.setCachedValues(entity, level);
                //and here the same from any recipe
                entity.cost = entity.recipe.getCost();

                if (NeutroniumHelper.checker(simpleContainer, entity, level)) {
                    NeutroniumHelper.shrink(simpleContainer, entity);
                    //we have to save lol :)
                    setChanged(level, blockPos, state);
                    if (entity.progress >= entity.cost) {
                        NeutroniumHelper.craftItem(entity, level);
                        // and here :-)
                        setChanged(level, blockPos, state);
                    }
                }
            }
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        // we check when we load world if there is a match and that will mean that the player has left the world in the middle of the crafting process
        this.progress = tag.getInt("progress");
        this.cost = tag.getInt("cost");
        this.cachedId = new ResourceLocation(tag.getString("cachedId"));
        this.cachedCost = tag.getInt("cachedCost");
        if (tag.contains("cachedInput", Tag.TAG_COMPOUND)) {
            CompoundTag cachedInputTag = tag.getCompound("cachedInput");
            if (cachedInputTag.contains("cachedInputJson", Tag.TAG_STRING)) {
                String cachedInputJson = cachedInputTag.getString("cachedInputJson");
                cachedInput = Ingredient.fromJson(new JsonParser().parse(cachedInputJson));
            }
        }
        if (tag.contains("cachedOutput", Tag.TAG_COMPOUND)) {
            this.cachedOutput = ItemStack.of(tag.getCompound("cachedOutput"));
        }
        // obvious
        ContainerHelper.loadAllItems(tag, this.inventory);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        //here we put the value of the boolean
        //saving the progress and the cost
        tag.putInt("progress", this.progress);
        tag.putInt("cost", this.cost);
        // we have to save them all the time, so we satisfy the player :--)
        if (this.recipe != null) {
            tag.putString("cachedId", this.cachedId.toString());
            tag.putInt("cachedCost", this.cachedCost);

            CompoundTag cachedInputTag = new CompoundTag();
            cachedInputTag.putString("cachedInputJson", cachedInput.toJson().toString());
            tag.put("cachedInput", cachedInputTag);

            if (!this.cachedOutput.isEmpty()) {
                CompoundTag cachedOutputTag = new CompoundTag();
                this.cachedOutput.save(cachedOutputTag);
                tag.put("cachedOutput", cachedOutputTag);
            }
        }


        ContainerHelper.saveAllItems(tag, this.inventory);
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
        Direction localDir = this.getLevel().getBlockState(this.getBlockPos()).getValue(NeutroniumCompressorBlock.FACING);

        if(direction == Direction.DOWN) {
            return false;
        }

        if(direction == Direction.UP) {
            return index == 0;
        }

        return switch (localDir) {
            default ->
                    direction.getOpposite() == Direction.NORTH && index == 0 ||
                            direction.getOpposite() == Direction.EAST && index == 0 ||
                            direction.getOpposite() == Direction.WEST && index == 0 ||
                            direction.getOpposite() == Direction.SOUTH && index == 0;
            case EAST ->
                    direction.getClockWise() == Direction.NORTH && index == 0 ||
                            direction.getClockWise() == Direction.EAST && index == 0 ||
                            direction.getClockWise() == Direction.WEST && index == 0 ||
                            direction.getClockWise() == Direction.SOUTH && index == 0;
            case SOUTH ->
                    direction == Direction.NORTH && index == 0 ||
                            direction == Direction.EAST && index == 0 ||
                            direction == Direction.WEST && index == 0 ||
                            direction == Direction.SOUTH && index == 0;
            case WEST ->
                    direction.getCounterClockWise() == Direction.NORTH && index == 0 ||
                            direction.getCounterClockWise() == Direction.EAST && index == 0 ||
                            direction.getCounterClockWise() == Direction.WEST && index == 0 ||
                            direction.getCounterClockWise() == Direction.SOUTH && index == 0;
        };
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        Direction localDir = this.getLevel().getBlockState(this.getBlockPos()).getValue(NeutroniumCompressorBlock.FACING);

        if (direction == Direction.UP) {
            return false;
        }

        // Down extract 2
        if (direction == Direction.DOWN) {
            return index == 1;
        }

        return switch (localDir) {
            default -> direction.getOpposite() == Direction.EAST && index == 1;

            case EAST ->  direction.getClockWise() == Direction.EAST && index == 1;

            case SOUTH ->     direction == Direction.EAST && index == 1;

            case WEST -> direction.getCounterClockWise() == Direction.EAST && index == 1;
        };
    }
}