/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 * 
 * AllAssets, created by Skepter 
 * 
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 * 
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter 
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 * 
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.utils.utilclasses;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class PluginUtils {

	public static String getAuthors(Plugin plugin) {
		String authors = TextUtils.listToString(plugin.getDescription().getAuthors());
		if(authors.length() == 0)
			authors = "undefined";
		return authors;
	}
	
	public static String getAuthors(PluginDescriptionFile plugin) {
		String authors = TextUtils.listToString(plugin.getAuthors());
		if(authors.length() == 0)
			authors = "undefined";
		return authors;
	}
	
}
