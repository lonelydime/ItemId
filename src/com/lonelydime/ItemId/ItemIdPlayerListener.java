package com.lonelydime.ItemId;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class ItemIdPlayerListener extends PlayerListener{
	public static ItemId plugin;
	
	public ItemIdPlayerListener(ItemId instance) {
        plugin = instance;
    }
	
	public void onPlayerCommand(PlayerChatEvent event) {
		//Make the message a string.
		String[] split = event.getMessage().split(" ");
		//Get the player that talked.
		Player player = event.getPlayer();
		
		if ((split[0].equalsIgnoreCase("/itemid"))) {
			if (split.length > 1) {
			  try {
			    int dataid = Integer.parseInt(split[1]);
			    try {
			    	player.sendMessage(Material.getMaterial(dataid).toString());
			    }
			    catch (NullPointerException e) {
			    	player.sendMessage("Item does not exist");
			    }

			  } catch (NumberFormatException e) {
				  	String datastring = split[1];
				  	if (split.length > 2) {
				  		datastring = split[1]+"_"+split[2];
				  	}
				  	datastring = datastring.toUpperCase();

				    try {
				    	player.sendMessage(datastring+": "+Integer.toString(Material.getMaterial(datastring).getId() ) );
				    }
				    catch (NullPointerException n) {
				    	player.sendMessage("Item does not exist");
				    }
			  }

			}
			else {
				player.sendMessage(player.getItemInHand().getType().name()+": "+Integer.toString(player.getItemInHand().getTypeId()));
			}
		}
	}
}
