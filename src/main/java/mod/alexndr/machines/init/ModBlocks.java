package mod.alexndr.machines.init;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.block.MythrilBlastFurnaceBlock;
import mod.alexndr.machines.content.block.MythrilFurnaceBlock;
import mod.alexndr.machines.content.block.MythrilSmokerBlock;
import mod.alexndr.machines.content.block.OnyxBlastFurnaceBlock;
import mod.alexndr.machines.content.block.OnyxFurnaceBlock;
import mod.alexndr.machines.content.block.OnyxSmokerBlock;
import mod.alexndr.simplecorelib.helpers.LightUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

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
    public static final DeferredRegister<Block> BLOCKS = 
            DeferredRegister.create(ForgeRegistries.BLOCKS, Machines.MODID);
    
    // Furnaces
    public static final RegistryObject<OnyxFurnaceBlock> onyx_furnace = BLOCKS.register("onyx_furnace",
            () -> new OnyxFurnaceBlock(Block.Properties.of(Material.STONE)
                    .strength(3.5F, 12.0F).requiresCorrectToolForDrops()
                    .lightLevel(LightUtils.setSwitchedLight(BlockStateProperties.LIT, 13))));    
    public static final RegistryObject<MythrilFurnaceBlock> mythril_furnace = BLOCKS.register("mythril_furnace",
            () -> new MythrilFurnaceBlock(Block.Properties.of(Material.STONE)
                    .strength(3.5F, 12.0F).requiresCorrectToolForDrops()
                    .lightLevel(LightUtils.setSwitchedLight(BlockStateProperties.LIT, 13))));
    
    // blast furnaces
    public static final RegistryObject<MythrilBlastFurnaceBlock> mythril_blast_furnace = 
            BLOCKS.register("mythril_blast_furnace",  
                            () -> new MythrilBlastFurnaceBlock(Block.Properties.of(Material.STONE)
                                                               .strength(3.5F, 12.0F).requiresCorrectToolForDrops()
                                                               .lightLevel(LightUtils.setSwitchedLight(BlockStateProperties.LIT, 13))));
    public static final RegistryObject<OnyxBlastFurnaceBlock> onyx_blast_furnace = 
            BLOCKS.register("onyx_blast_furnace",  
                            () -> new OnyxBlastFurnaceBlock(Block.Properties.of(Material.STONE)
                                                               .strength(3.5F, 12.0F).requiresCorrectToolForDrops()
                                                               .lightLevel(LightUtils.setSwitchedLight(BlockStateProperties.LIT, 13))));
    
    // smokers
    public static final RegistryObject<MythrilSmokerBlock> mythril_smoker = 
            BLOCKS.register("mythril_smoker",  
                            () -> new MythrilSmokerBlock(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops()
                                                               .strength(3.5F, 12.0F)
                                                       .lightLevel(LightUtils.setSwitchedLight(BlockStateProperties.LIT, 13))));
    public static final RegistryObject<OnyxSmokerBlock> onyx_smoker = 
            BLOCKS.register("onyx_smoker",  
                            () -> new OnyxSmokerBlock(Block.Properties.of(Material.STONE)
                                                               .strength(3.5F, 12.0F).requiresCorrectToolForDrops()
                                                       .lightLevel(LightUtils.setSwitchedLight(BlockStateProperties.LIT, 13))));
} // end class
