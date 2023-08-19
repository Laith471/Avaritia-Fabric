package net.laith.avaritia.common.blockentity;

import com.google.gson.JsonParser;
import net.fabricmc.fabric.api.util.NbtType;
import net.laith.avaritia.common.block.NeutroniumCompressorBlock;
import net.laith.avaritia.common.recipe.NeutroniumCompressorRecipe;
import net.laith.avaritia.common.screenhandler.NeutroniumCompressorScreenHandler;
import net.laith.avaritia.init.ModBlockEntities;
import net.laith.avaritia.util.blockentities.NeutroniumHelper;
import net.laith.avaritia.util.inventory.ImplementedSidedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class NeutroniumCompressorBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedSidedInventory {
    public final DefaultedList<ItemStack> inventory;
    public NeutroniumCompressorRecipe recipe;
    public int progress;
    public int cost;
    public Optional match;
    public Ingredient cachedInput;
    public ItemStack cachedOutput;
    public int cachedCost;
    public Identifier cachedId;
    protected final PropertyDelegate propertyDelegate;


    public NeutroniumCompressorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NEUTRONIUM_COMPRESSOR_BLOCK_ENTITY, pos, state);
        this.inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
        this.propertyDelegate = new PropertyDelegate() {
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
            public int size() {
                return 2;
            }

        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }


    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new NeutroniumCompressorScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public static void tick(World world, BlockPos blockPos, BlockState state, NeutroniumCompressorBlockEntity entity) {
        if (world.isClient) {
            return;
        }

        SimpleInventory simpleInventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            simpleInventory.setStack(i, entity.getStack(i));
        }
        if (entity.inventory.get(1).getCount() < 64) {
            if (entity.cachedInput != null && entity.cachedId != null && entity.cachedOutput.getItem() != Items.AIR && entity.cachedCost != 0) {
                // if is true we set the match and the recipe to the cached value
                entity.recipe = new NeutroniumCompressorRecipe(entity.cachedId, entity.cachedInput, entity.cachedCost, entity.cachedOutput);
                entity.match = Optional.of(new NeutroniumCompressorRecipe(entity.cachedId, entity.cachedInput, entity.cachedCost, entity.cachedOutput));
            }

            ItemStack outputStack = entity.getStack(1);
            if (outputStack.isEmpty() && entity.progress == 0) {
                entity.recipe = null; // Reset the current recipe if the output slot is empty
            }

            if (entity.recipe == null) {
                // we check if the recipe is null and if we get a match
                entity.match = world.getRecipeManager().getFirstMatch(NeutroniumCompressorRecipe.Type.INSTANCE, simpleInventory, entity.getWorld());
            }

            if (entity.progress > 0) {
                world.setBlockState(blockPos, state.with(NeutroniumCompressorBlock.ACTIVE, true));
            }
            if (entity.progress == 0) {
                world.setBlockState(blockPos, state.with(NeutroniumCompressorBlock.ACTIVE, false));
            }

            // if there is a match and its present it will be true. we take the match from either the cachedRecipe or the new recipe.
            if (entity.match != null && entity.match.isPresent()) {
                //this field will be functional only if we get a match from the new recipe
                entity.recipe = (NeutroniumCompressorRecipe) entity.match.get();
                // here we set the cached value from any recipe
                NeutroniumHelper.setCachedValues(entity, world);
                //and here the same from any recipe
                entity.cost = entity.recipe.getCost();

                if (NeutroniumHelper.checker(simpleInventory, entity, world)) {
                    NeutroniumHelper.shrink(simpleInventory, entity);
                    //we have to save lol :)
                    markDirty(world, blockPos, state);
                    if (entity.progress >= entity.cost) {
                        NeutroniumHelper.craftItem(entity, world);
                        // and here :-)
                        markDirty(world, blockPos, state);
                    }
                }
            }
        }
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        // we check when we load world if there is a match and that will mean that the player has left the world in the middle of the crafting process
        this.progress = nbt.getInt("progress");
        this.cost = nbt.getInt("cost");
        this.cachedId = new Identifier(nbt.getString("cachedId"));
        this.cachedCost = nbt.getInt("cachedCost");
        if (nbt.contains("cachedInput", NbtType.COMPOUND)) {
            NbtCompound cachedInputNbt = nbt.getCompound("cachedInput");
            if (cachedInputNbt.contains("cachedInputJson", NbtType.STRING)) {
                String cachedInputJson = cachedInputNbt.getString("cachedInputJson");
                cachedInput = Ingredient.fromJson(new JsonParser().parse(cachedInputJson));
            }
        }
        if (nbt.contains("cachedOutput", NbtType.COMPOUND)) {
            this.cachedOutput = ItemStack.fromNbt(nbt.getCompound("cachedOutput"));
        }
        // obvious
        Inventories.readNbt(nbt, this.inventory);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        //here we put the value of the boolean
        //saving the progress and the cost
        nbt.putInt("progress", this.progress);
        nbt.putInt("cost", this.cost);
        // we have to save them all the time, so we satisfy the player :--)
        if (this.recipe != null) {
            nbt.putString("cachedId", this.cachedId.toString());
            nbt.putInt("cachedCost", this.cachedCost);

            NbtCompound cachedInputNbt = new NbtCompound();
            cachedInputNbt.putString("cachedInputJson", cachedInput.toJson().toString());
            nbt.put("cachedInput", cachedInputNbt);

            if (!this.cachedOutput.isEmpty()) {
                NbtCompound cachedOutputNbt = new NbtCompound();
                this.cachedOutput.writeNbt(cachedOutputNbt);
                nbt.put("cachedOutput", cachedOutputNbt);
            }
        }


        Inventories.writeNbt(nbt, this.inventory);
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {

        Direction localDir = this.getWorld().getBlockState(this.pos).get(NeutroniumCompressorBlock.FACING);

        if(side == Direction.DOWN) {
            return false;
        }

        if(side == Direction.UP) {
            return slot == 0;
        }

        return switch (localDir) {
            default ->
                    side.getOpposite() == Direction.NORTH && slot == 0 ||
                            side.getOpposite() == Direction.EAST && slot == 0 ||
                            side.getOpposite() == Direction.WEST && slot == 0 ||
                            side.getOpposite() == Direction.SOUTH && slot == 0;
            case EAST ->
                    side.rotateYClockwise() == Direction.NORTH && slot == 0 ||
                            side.rotateYClockwise() == Direction.EAST && slot == 0 ||
                            side.rotateYClockwise() == Direction.WEST && slot == 0 ||
                            side.rotateYClockwise() == Direction.SOUTH && slot == 0;
            case SOUTH ->
                    side == Direction.NORTH && slot == 0 ||
                            side == Direction.EAST && slot == 0 ||
                            side == Direction.WEST && slot == 0 ||
                            side == Direction.SOUTH && slot == 0;
            case WEST ->
                    side.rotateYCounterclockwise() == Direction.NORTH && slot == 0 ||
                            side.rotateYCounterclockwise() == Direction.EAST && slot == 0 ||
                            side.rotateYCounterclockwise() == Direction.WEST && slot == 0 ||
                            side.rotateYCounterclockwise() == Direction.SOUTH && slot == 0;
        };
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        Direction localDir = this.getWorld().getBlockState(this.pos).get(NeutroniumCompressorBlock.FACING);

        if (side == Direction.UP) {
            return false;
        }

        // Down extract 2
        if (side == Direction.DOWN) {
            return slot == 1;
        }

        return switch (localDir) {
            default -> side.getOpposite() == Direction.EAST && slot == 1;

            case EAST ->  side.rotateYClockwise() == Direction.EAST && slot == 1;

            case SOUTH ->     side == Direction.EAST && slot == 1;

            case WEST -> side.rotateYCounterclockwise() == Direction.EAST && slot == 1;
        };
    }
}