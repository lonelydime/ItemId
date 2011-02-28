package com.lonelydime.ItemId;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijikokun.bukkit.Permissions.Permissions;
import com.nijiko.permissions.PermissionHandler;

public class ItemId extends JavaPlugin{
	public static PermissionHandler Permissions = null;
	public void onDisable() {
		System.out.println("ItemId Disabled");
	}

	public void onEnable() {      
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
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		boolean canUseCommand = true;
		String command = cmd.getName();
		
		if (sender instanceof Player) {
			if (ItemId.Permissions != null) {
				canUseCommand = Permissions.has((Player)sender, "itemid.usecmd");
			}
		}
		
		if (command.equals("itemid") && canUseCommand) {
			if (args.length >= 1) {
			  try {
			    int dataid = Integer.parseInt(args[0]);
			    try {
			    	sender.sendMessage(Material.getMaterial(dataid).toString());
			    }
			    catch (NullPointerException e) {
			    	sender.sendMessage("Item does not exist");
			    }

			  } 
			  catch (NumberFormatException e) {
				  	String datastring = args[0];
				  	if (args.length >= 2) {
				  		datastring = args[0]+"_"+args[1];
				  	}
				  	datastring = datastring.toUpperCase();

				    try {
				    	sender.sendMessage(datastring+": "+Integer.toString(Material.getMaterial(datastring).getId() ) );
				    }
				    catch (NullPointerException n) {
				    	sender.sendMessage("Item does not exist");
				    }
			  }

			}
			else {
				if (sender instanceof Player) {
					Player player = (Player)sender;
					player.sendMessage(player.getItemInHand().getType().name()+": "+Integer.toString(player.getItemInHand().getTypeId()));
				}
				else {
					return true;
				}
			}

		}
		
		return true;
	}
}
