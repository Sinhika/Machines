package mod.alexndr.machines.config;

import mod.alexndr.simplecorelib.api.config.SimpleConfig;

public class MachinesConfig extends SimpleConfig
{
	public static MachinesConfig INSTANCE = new MachinesConfig();
	
    public static double mythrilFurnaceFuelMultiplier = 2.0;
    public static int onyxFurnaceYieldChance = 33;
    public static int onyxFurnaceYieldAmount = 1;

} // end-class
