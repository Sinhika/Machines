package alexndr.plugins.machines.conditions;

import java.util.function.BooleanSupplier;

import com.google.gson.JsonObject;

import alexndr.plugins.machines.ModInfo;
import alexndr.plugins.machines.Settings;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

public class FurnaceConditionFactory implements IConditionFactory 
{
	@Override
	public BooleanSupplier parse(JsonContext context, JsonObject json) 
	{
		boolean value = JsonUtils.getBoolean(json , "value", true);
		String key = JsonUtils.getString(json, "type");
		
		if (key.equals(ModInfo.ID + ":mythril_furnace_enabled")) {
			return () -> Settings.mythrilFurnace.isEnabled() == value;
		}
		else if (key.equals(ModInfo.ID + ":onyx_furnace_enabled")) {
			return () -> Settings.onyxFurnace.isEnabled() == value;
		}
		return null;
	}

} // end class
