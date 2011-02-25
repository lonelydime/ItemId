package com.lonelydime.ItemId;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijikokun.bukkit.Permissions.Permissions;
import com.nijiko.permissions.PermissionHandler;

public class ItemId extends JavaPlugin{
	private final ItemIdPlayerListener playerListener = new ItemIdPlayerListener(this);
	public static PermissionHandler Permissions = null;
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
        
        setupPermissions();
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " by lonelydime is enabled!" );
	}
	
	public void setupPermissions() {
		Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");

		if(Permissions == null) {
		    if(test != null) {
		    	Permissions = ((Permissions)test).getHandler();
		    }
		}
	}
}
