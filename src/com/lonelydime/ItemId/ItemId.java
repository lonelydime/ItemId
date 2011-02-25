package com.lonelydime.ItemId;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemId extends JavaPlugin{
	private final ItemIdPlayerListener playerListener = new ItemIdPlayerListener(this);

	public void onDisable() {
		System.out.println("ItemId Disabled");
	}

	public void onEnable() {
        //Create the pluginmanage pm.
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_COMMAND, playerListener, Priority.Normal, this);
        
        //Get the infomation from the yml file.
        PluginDescriptionFile pdfFile = this.getDescription();
        //Print that the plugin has been enabled!
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " by lonelydime is enabled!" );
	}
}
