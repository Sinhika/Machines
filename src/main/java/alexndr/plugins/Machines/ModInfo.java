package alexndr.plugins.Machines;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author AleXndrTheGr8st
 */
public class ModInfo {
	public static final String ID = "machines";
	public static final String NAME = "Machines";
	public static final String VERSION = "@MODVERSION@";
	public static final String DESCRIPTION = "A plugin for SimpleCore API that adds a number of different machines.";
    public static final String DEPENDENCIES 
    = "required-after:simplecore@[1.5.2.1,);required-after:simpleores;after:jei@[4.5.0.0,)";
    public static final String ACCEPTED_VERSIONS = "[1.11,1.11.2]";
	public static final List<String> AUTHORS = Lists.newArrayList("AleXndrTheGr8st","Sinhika");
	public static final String CREDITS = "Created by AleXndrTheGr8st; maintained by Sinhika";
	public static final String LOGO = "/assets/machines/logos/machines.png";
	public static final String PARENT = "";
	public static final boolean USEDEPENDENCYINFO = true;
    public static final String VERSIONURL = "https://raw.githubusercontent.com/Sinhika/Machines/1.11/update.json";
    public static final String URL = "http://minecraft.curseforge.com/projects/machines";
}
