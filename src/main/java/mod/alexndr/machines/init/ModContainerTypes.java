package mod.alexndr.machines.init;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.container.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

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
	        DeferredRegister.create(Registries.MENU, Machines.MODID);
	
	// mythril furnaces
    public static final Supplier<MenuType<MythrilFurnaceContainer>> mythril_furnace 
        = CONTAINER_TYPES.register("mythril_furnace", () -> new MenuType<>(
                MythrilFurnaceContainer::new, FeatureFlags.DEFAULT_FLAGS));
    
    public static final Supplier<MenuType<MythrilBlastFurnaceContainer>> mythril_blast_furnace 
        = CONTAINER_TYPES.register("mythril_blast_furnace",() -> new MenuType<>(
                MythrilBlastFurnaceContainer::new, FeatureFlags.DEFAULT_FLAGS));

    public static final Supplier<MenuType<MythrilSmokerContainer>> mythril_smoker 
        = CONTAINER_TYPES.register("mythril_smoker", () -> new MenuType<>(
            MythrilSmokerContainer::new, FeatureFlags.DEFAULT_FLAGS));
    
   // onyx furnaces
   public static final Supplier<MenuType<OnyxFurnaceContainer>> onyx_furnace
        = CONTAINER_TYPES.register("onyx_furnace", () -> new MenuType<>(
           OnyxFurnaceContainer::new, FeatureFlags.DEFAULT_FLAGS));
   
   public static final Supplier<MenuType<OnyxBlastFurnaceContainer>> onyx_blast_furnace 
       = CONTAINER_TYPES.register("onyx_blast_furnace", () -> new MenuType<>(
           OnyxBlastFurnaceContainer::new, FeatureFlags.DEFAULT_FLAGS));

   public static final Supplier<MenuType<OnyxSmokerContainer>> onyx_smoker 
       = CONTAINER_TYPES.register("onyx_smoker", () -> new MenuType<>(
           OnyxSmokerContainer::new, FeatureFlags.DEFAULT_FLAGS));
   

} // end class
