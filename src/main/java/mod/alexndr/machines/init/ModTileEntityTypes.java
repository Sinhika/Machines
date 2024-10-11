package mod.alexndr.machines.init;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.block_entity.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Holds a list of all our {@link BlockEntityType}s.
 * Suppliers that create BlockEntityType are added to the DeferredRegister.
 * The DeferredRegister is then added to our mod event bus in our constructor.
 * When the BlockEntityType Registry Event is fired by Forge and it is time for the mod to
 * register its BlockEntityType, our BlockEntityType are created and registered by the DeferredRegister.
 * The BlockEntityType Registry Event will always be called after the Block and Item registries are filled.
 * Note: This supports registry overrides.
 *
 * @author Cadiboo
 */
public final class ModTileEntityTypes {

	public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = 
	        DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Machines.MODID);

	// mythril furnaces
	public static DeferredHolder<BlockEntityType<?>, BlockEntityType<MythrilFurnaceTileEntity>> mythril_furnace =
	        TILE_ENTITY_TYPES.register("mythril_furnace", 
	                () -> BlockEntityType.Builder.of(MythrilFurnaceTileEntity::new, ModBlocks.mythril_furnace.get())
               .build(null));

    public static DeferredHolder<BlockEntityType<?>, BlockEntityType<MythrilBlastFurnaceTileEntity>> mythril_blast_furnace =
            TILE_ENTITY_TYPES.register("mythril_blast_furnace", 
                    () -> BlockEntityType.Builder.of(MythrilBlastFurnaceTileEntity::new, ModBlocks.mythril_blast_furnace.get())
               .build(null));

    public static DeferredHolder<BlockEntityType<?>, BlockEntityType<MythrilSmokerTileEntity>> mythril_smoker =
            TILE_ENTITY_TYPES.register("mythril_smoker", 
                    () -> BlockEntityType.Builder.of(MythrilSmokerTileEntity::new, ModBlocks.mythril_smoker.get())
               .build(null));

    // onyx furnaces
    public static DeferredHolder<BlockEntityType<?>, BlockEntityType<OnyxFurnaceTileEntity>> onyx_furnace =
            TILE_ENTITY_TYPES.register("onyx_furnace", 
                            () -> BlockEntityType.Builder.of(OnyxFurnaceTileEntity::new, ModBlocks.onyx_furnace.get())
             .build(null));

    public static DeferredHolder<BlockEntityType<?>, BlockEntityType<OnyxBlastFurnaceBlockEntity>> onyx_blast_furnace =
            TILE_ENTITY_TYPES.register("onyx_blast_furnace", 
                    () -> BlockEntityType.Builder.of(OnyxBlastFurnaceBlockEntity::new, ModBlocks.onyx_blast_furnace.get())
               .build(null));

    public static DeferredHolder<BlockEntityType<?>, BlockEntityType<OnyxSmokerBlockEntity>> onyx_smoker =
            TILE_ENTITY_TYPES.register("onyx_smoker", 
                    () -> BlockEntityType.Builder.of(OnyxSmokerBlockEntity::new, ModBlocks.onyx_smoker.get())
               .build(null));

} // end class
