package mod.alexndr.machines.init;

import net.minecraft.tileentity.TileEntityType;
import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.MythrilFurnaceTileEntity;
import mod.alexndr.machines.content.OnyxFurnaceTileEntity;
import net.minecraftforge.fml.RegistryObject;
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

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = 
	        new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Machines.MODID);

	public static RegistryObject<TileEntityType<MythrilFurnaceTileEntity>> mythril_furnace = 
	        TILE_ENTITY_TYPES.register("mythril_furnace", 
	                () -> TileEntityType.Builder.create(MythrilFurnaceTileEntity::new, 
	                                                    ModBlocks.mythril_furnace.get())
               .build(null));

    
    public static RegistryObject<TileEntityType<OnyxFurnaceTileEntity>> onyx_furnace = 
            TILE_ENTITY_TYPES.register("onyx_furnace", 
                            () -> TileEntityType.Builder.create(OnyxFurnaceTileEntity::new, 
                                                                ModBlocks.onyx_furnace.get())
             .build(null));
} // end class
