package mod.alexndr.machines.init;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.container.MythrilBlastFurnaceContainer;
import mod.alexndr.machines.content.container.MythrilFurnaceContainer;
import mod.alexndr.machines.content.container.MythrilSmokerContainer;
import mod.alexndr.machines.content.container.OnyxBlastFurnaceContainer;
import mod.alexndr.machines.content.container.OnyxFurnaceContainer;
import mod.alexndr.machines.content.container.OnyxSmokerContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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

	public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = 
	        DeferredRegister.create(ForgeRegistries.MENU_TYPES, Machines.MODID);
	
	// mythril furnaces
    public static final RegistryObject<MenuType<MythrilFurnaceContainer>> mythril_furnace 
        = CONTAINER_TYPES.register("mythril_furnace", () -> IForgeMenuType.create((windowId, inv, data) 
                -> new MythrilFurnaceContainer(windowId, inv, data.readBlockPos(), inv.player)));
    
    public static final RegistryObject<MenuType<MythrilBlastFurnaceContainer>> mythril_blast_furnace 
        = CONTAINER_TYPES.register("mythril_blast_furnace", () -> IForgeMenuType.create((windowId, inv, data) 
                -> new MythrilBlastFurnaceContainer(windowId, inv, data.readBlockPos(), inv.player)));

    public static final RegistryObject<MenuType<MythrilSmokerContainer>> mythril_smoker 
        = CONTAINER_TYPES.register("mythril_smoker", () -> IForgeMenuType.create((windowId, inv, data) 
                -> new MythrilSmokerContainer(windowId, inv, data.readBlockPos(), inv.player)));
    
   // onyx furnaces
   public static final RegistryObject<MenuType<OnyxFurnaceContainer>> onyx_furnace 
        = CONTAINER_TYPES.register("onyx_furnace", () -> IForgeMenuType.create((windowId, inv, data) 
                -> new OnyxFurnaceContainer(windowId, inv, data.readBlockPos(), inv.player)));
   
   public static final RegistryObject<MenuType<OnyxBlastFurnaceContainer>> onyx_blast_furnace 
       = CONTAINER_TYPES.register("onyx_blast_furnace", () -> IForgeMenuType.create((windowId, inv, data) 
               -> new OnyxBlastFurnaceContainer(windowId, inv, data.readBlockPos(), inv.player)));

   public static final RegistryObject<MenuType<OnyxSmokerContainer>> onyx_smoker 
       = CONTAINER_TYPES.register("onyx_smoker", () -> IForgeMenuType.create((windowId, inv, data) 
               -> new OnyxSmokerContainer(windowId, inv, data.readBlockPos(), inv.player)));
   

} // end class
