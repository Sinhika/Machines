package mod.alexndr.machines.init;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.container.MythrilBlastFurnaceContainer;
import mod.alexndr.machines.content.container.MythrilFurnaceContainer;
import mod.alexndr.machines.content.container.MythrilSmokerContainer;
import mod.alexndr.machines.content.container.OnyxBlastFurnaceContainer;
import mod.alexndr.machines.content.container.OnyxFurnaceContainer;
import mod.alexndr.machines.content.container.OnyxSmokerContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Holds a list of all our {@link ContainerType}s.
 * Suppliers that create ContainerTypes are added to the DeferredRegister.
 * The DeferredRegister is then added to our mod event bus in our constructor.
 * When the ContainerType Registry Event is fired by Forge and it is time for the mod to
 * register its ContainerTypes, our ContainerTypes are created and registered by the DeferredRegister.
 * The ContainerType Registry Event will always be called after the Block and Item registries are filled.
 * Note: This supports registry overrides.
 *
 * @author Cadiboo
 */
public final class ModContainerTypes {

	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = 
	        new DeferredRegister<>(ForgeRegistries.CONTAINERS, Machines.MODID);
	
	// mythril furnaces
    public static final RegistryObject<ContainerType<MythrilFurnaceContainer>> mythril_furnace 
        = CONTAINER_TYPES.register("mythril_furnace", () -> IForgeContainerType.create(MythrilFurnaceContainer::new));
    
    public static final RegistryObject<ContainerType<MythrilBlastFurnaceContainer>> mythril_blast_furnace 
        = CONTAINER_TYPES.register("mythril_blast_furnace", () -> IForgeContainerType.create(MythrilBlastFurnaceContainer::new));

    public static final RegistryObject<ContainerType<MythrilSmokerContainer>> mythril_smoker 
        = CONTAINER_TYPES.register("mythril_smoker", () -> IForgeContainerType.create(MythrilSmokerContainer::new));
    
   // onyx furnaces
   public static final RegistryObject<ContainerType<OnyxFurnaceContainer>> onyx_furnace 
        = CONTAINER_TYPES.register("onyx_furnace", () -> IForgeContainerType.create(OnyxFurnaceContainer::new));
   
   public static final RegistryObject<ContainerType<OnyxBlastFurnaceContainer>> onyx_blast_furnace 
       = CONTAINER_TYPES.register("onyx_blast_furnace", () -> IForgeContainerType.create(OnyxBlastFurnaceContainer::new));

   public static final RegistryObject<ContainerType<OnyxSmokerContainer>> onyx_smoker 
       = CONTAINER_TYPES.register("onyx_smoker", () -> IForgeContainerType.create(OnyxSmokerContainer::new));
   

} // end class
