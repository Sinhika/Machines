package mod.alexndr.machines.datagen;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import mod.alexndr.machines.Machines;
import mod.alexndr.simplecorelib.api.datagen.SimpleLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * bundles up the GatherDataEvent handler and all the necessary data providers for
 * data generation.
 * @author Sinhika
 */
@EventBusSubscriber(modid = Machines.MODID, bus = MOD)
public class MachinesDataGenerator
{
    /**
     * GatherDataEvent handler.
     * @param event the GatherDataEvent.
     */
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();		
        
        // server-side
        ModBlockTags blockTagsProvider = new ModBlockTags(packOutput, lookupProvider, existingFileHelper);
      	gen.addProvider(event.includeServer(), blockTagsProvider);
    	gen.addProvider(event.includeServer(), 
    			new SimpleLootTableProvider(packOutput, List.of(
    					new LootTableProvider.SubProviderEntry(MachinesBlockLootSubprovider::new, LootContextParamSets.BLOCK))));
    	
      	// client-side
       	gen.addProvider(event.includeClient(), new MachinesBlockStateProvider(packOutput, existingFileHelper));
        
    }
} // end class
