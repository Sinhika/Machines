package mod.alexndr.machines.init;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.block.*;
import mod.alexndr.simplecorelib.api.helpers.LightUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;


/**
 * Holds a list of all our {@link Block}s.
 * Suppliers that create Blocks are added to the DeferredRegister.
 * The DeferredRegister is then added to our mod event bus in our constructor.
 * When the Block Registry Event is fired by Forge and it is time for the mod to
 * register its Blocks, our Blocks are created and registered by the DeferredRegister.
 * The Block Registry Event will always be called before the Item registry is filled.
 * Note: This supports registry overrides.
 *
 * @author Sinhika, notes by Cadiboo
 */
public final class ModBlocks
{
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Machines.MODID);
    
    // Furnaces
    public static final DeferredBlock<OnyxFurnaceBlock> onyx_furnace = BLOCKS.register("onyx_furnace",
            () -> new OnyxFurnaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL)
                    .strength(3.5F, 12.0F).requiresCorrectToolForDrops()
                    .lightLevel(LightUtils.setSwitchedLight(BlockStateProperties.LIT, 13))));
    
    public static final DeferredBlock<MythrilFurnaceBlock> mythril_furnace = BLOCKS.register("mythril_furnace",
            () -> new MythrilFurnaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE)
                    .strength(3.5F, 12.0F).requiresCorrectToolForDrops()
                    .lightLevel(LightUtils.setSwitchedLight(BlockStateProperties.LIT, 13))));
    
    // blast furnaces
    public static final DeferredBlock<MythrilBlastFurnaceBlock> mythril_blast_furnace = 
            BLOCKS.register("mythril_blast_furnace",  
                            () -> new MythrilBlastFurnaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE)
                                                               .strength(3.5F, 12.0F).requiresCorrectToolForDrops()
                                                               .lightLevel(LightUtils.setSwitchedLight(BlockStateProperties.LIT, 13))));
    public static final DeferredBlock<OnyxBlastFurnaceBlock> onyx_blast_furnace = 
            BLOCKS.register("onyx_blast_furnace",  
                            () -> new OnyxBlastFurnaceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL)
                                                               .strength(3.5F, 12.0F).requiresCorrectToolForDrops()
                                                               .lightLevel(LightUtils.setSwitchedLight(BlockStateProperties.LIT, 13))));
    
    // smokers
    public static final DeferredBlock<MythrilSmokerBlock> mythril_smoker = 
            BLOCKS.register("mythril_smoker",  
                            () -> new MythrilSmokerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).requiresCorrectToolForDrops()
                                                               .strength(3.5F, 12.0F)
                                                       .lightLevel(LightUtils.setSwitchedLight(BlockStateProperties.LIT, 13))));
    public static final DeferredBlock<OnyxSmokerBlock> onyx_smoker = 
            BLOCKS.register("onyx_smoker",  
                            () -> new OnyxSmokerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL)
                                                               .strength(3.5F, 12.0F).requiresCorrectToolForDrops()
                                                       .lightLevel(LightUtils.setSwitchedLight(BlockStateProperties.LIT, 13))));
} // end class
