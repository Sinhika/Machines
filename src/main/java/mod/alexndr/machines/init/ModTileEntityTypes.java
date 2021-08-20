package mod.alexndr.machines.init;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.tile.MythrilBlastFurnaceTileEntity;
import mod.alexndr.machines.content.tile.MythrilFurnaceTileEntity;
import mod.alexndr.machines.content.tile.MythrilSmokerTileEntity;
import mod.alexndr.machines.content.tile.OnyxBlastFurnaceTileEntity;
import mod.alexndr.machines.content.tile.OnyxFurnaceTileEntity;
import mod.alexndr.machines.content.tile.OnyxSmokerTileEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Holds a list of all our {@link TileEntityType}s.
 * Suppliers that create TileEntityTypes are added to the DeferredRegister.
 * The DeferredRegister is then added to our mod event bus in our constructor.
 * When the TileEntityType Registry Event is fired by Forge and it is time for the mod to
 * register its TileEntityTypes, our TileEntityTypes are created and registered by the DeferredRegister.
 * The TileEntityType Registry Event will always be called after the Block and Item registries are filled.
 * Note: This supports registry overrides.
 *
 * @author Cadiboo
 */
public final class ModTileEntityTypes {

	public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = 
	        DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Machines.MODID);

	// mythril furnaces
	public static RegistryObject<BlockEntityType<MythrilFurnaceTileEntity>> mythril_furnace = 
	        TILE_ENTITY_TYPES.register("mythril_furnace", 
	                () -> BlockEntityType.Builder.of(MythrilFurnaceTileEntity::new, 
	                                                    ModBlocks.mythril_furnace.get())
               .build(null));

    public static RegistryObject<BlockEntityType<MythrilBlastFurnaceTileEntity>> mythril_blast_furnace = 
            TILE_ENTITY_TYPES.register("mythril_blast_furnace", 
                    () -> BlockEntityType.Builder.of(MythrilBlastFurnaceTileEntity::new, 
                                                        ModBlocks.mythril_blast_furnace.get())
               .build(null));

    public static RegistryObject<BlockEntityType<MythrilSmokerTileEntity>> mythril_smoker = 
            TILE_ENTITY_TYPES.register("mythril_smoker", 
                    () -> BlockEntityType.Builder.of(MythrilSmokerTileEntity::new, 
                                                        ModBlocks.mythril_smoker.get())
               .build(null));

    // onyx furnaces
    public static RegistryObject<BlockEntityType<OnyxFurnaceTileEntity>> onyx_furnace = 
            TILE_ENTITY_TYPES.register("onyx_furnace", 
                            () -> BlockEntityType.Builder.of(OnyxFurnaceTileEntity::new, 
                                                                ModBlocks.onyx_furnace.get())
             .build(null));

    public static RegistryObject<BlockEntityType<OnyxBlastFurnaceTileEntity>> onyx_blast_furnace = 
            TILE_ENTITY_TYPES.register("onyx_blast_furnace", 
                    () -> BlockEntityType.Builder.of(OnyxBlastFurnaceTileEntity::new, 
                                                        ModBlocks.onyx_blast_furnace.get())
               .build(null));

    public static RegistryObject<BlockEntityType<OnyxSmokerTileEntity>> onyx_smoker = 
            TILE_ENTITY_TYPES.register("onyx_smoker", 
                    () -> BlockEntityType.Builder.of(OnyxSmokerTileEntity::new, 
                                                        ModBlocks.onyx_smoker.get())
               .build(null));

} // end class
