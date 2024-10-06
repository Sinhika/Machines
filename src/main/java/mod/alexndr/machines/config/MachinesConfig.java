package mod.alexndr.machines.config;

import mod.alexndr.machines.Machines;
import mod.alexndr.simplecorelib.api.config.SimpleConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

public class MachinesConfig extends SimpleConfig
{
    private static final ModConfigSpec.Builder builder = new ModConfigSpec.Builder();


    static final ModConfigSpec.DoubleValue serverMythrilFurnaceFuelMultiplier;
    static final ModConfigSpec.IntValue serverOnyxFurnaceYieldChance;
    static final ModConfigSpec.IntValue serverOnyxFurnaceYieldAmount;

    static {
        builder.push("general");
        serverMythrilFurnaceFuelMultiplier = builder
                .comment("Multiplier for fuel duration in mythril furnaces")
                .translation(Machines.MODID + "config.mythrilFurnaceFuelMultiplier")
                .defineInRange("mythrilFurnaceFuelMultiplier", 2.0, 1.0, 99.9);
        serverOnyxFurnaceYieldChance = builder
                .comment("Per cent (%) chance that onyx furnaces will yield extra output")
                .translation(Machines.MODID + "config.onyxFurnaceYieldChance")
                .defineInRange("onyxFurnaceYieldChance", 33, 0, 100);
        serverOnyxFurnaceYieldAmount = builder
                .comment("Number of additional items yielded by onyx furnaces")
                .translation(Machines.MODID + "config.onyxFurnaceYieldAmount")
                .defineInRange("onyxFurnaceYieldAmount", 1, 0, 64);
        builder.pop();
    }

    public static final ModConfigSpec SPEC = builder.build();

    public static double mythrilFurnaceFuelMultiplier = 2.0;
    public static int onyxFurnaceYieldChance = 33;
    public static int onyxFurnaceYieldAmount = 1;

    public static void onLoad(final ModConfigEvent event)
    {
        mythrilFurnaceFuelMultiplier = serverMythrilFurnaceFuelMultiplier.get();
        onyxFurnaceYieldChance = serverOnyxFurnaceYieldChance.get();
        onyxFurnaceYieldAmount = serverOnyxFurnaceYieldAmount.get();
    }
} // end-class
