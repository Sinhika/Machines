package mod.alexndr.machines.init;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.block.MythrilBlastFurnaceBlock;
import mod.alexndr.machines.content.block.MythrilFurnaceBlock;
import mod.alexndr.machines.content.block.OnyxFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
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
            new DeferredRegister<>(ForgeRegistries.BLOCKS, Machines.MODID);
    
    // Furnaces
    public static final RegistryObject<OnyxFurnaceBlock> onyx_furnace = BLOCKS.register("onyx_furnace",
            () -> new OnyxFurnaceBlock(Block.Properties.create(Material.ROCK)
                    .hardnessAndResistance(3.5F, 12.0F)
                    .lightValue(13).harvestTool(ToolType.PICKAXE)));    
    public static final RegistryObject<MythrilFurnaceBlock> mythril_furnace = BLOCKS.register("mythril_furnace",
            () -> new MythrilFurnaceBlock(Block.Properties.create(Material.ROCK)
                    .hardnessAndResistance(3.5F, 12.0F)
                    .lightValue(13).harvestTool(ToolType.PICKAXE)));
    
    public static final RegistryObject<MythrilBlastFurnaceBlock> mythril_blast_furnace = 
            BLOCKS.register("mythril_blast_furnace",  
                            () -> new MythrilBlastFurnaceBlock(Block.Properties.create(Material.ROCK)
                                                               .hardnessAndResistance(3.5F, 12.0F)
                                                       .lightValue(13).harvestTool(ToolType.PICKAXE)));
} // end class
