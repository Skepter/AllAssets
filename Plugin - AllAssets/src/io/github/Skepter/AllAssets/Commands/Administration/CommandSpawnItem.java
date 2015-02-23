/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
package io.github.Skepter.AllAssets.Commands.Administration;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Help;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandSpawnItem {

	public CommandSpawnItem(final CommandFramework framework) {
		framework.registerCommands(this);
	}
	
	public static Map<String, ItemStack> items = new HashMap<String, ItemStack>();

	@CommandHandler(name = "spawnitem", aliases = { "si" }, permission = "spawnitem", description = "Spawns a custom item")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		
		switch (args.getArgs().length) {
		case 0:
			printHelp(args.getSender());
			return;
		case 1:
			try {
				player.setItemInHand(items.get(args.getArgs()[0]));
			} catch (Exception e) {
				ErrorUtils.error(player, "That item does not exist!");
			}
			return;
			
		}
		return;
	}

	@CommandHandler(name = "spawnitem.list", permission = "spawnitem", description = "Shows a list of items to spawn")
	public void startAnnouncer(final CommandArgs args) {
		args.getSender().sendMessage(TextUtils.title("Items"));
		for(Entry<String, ItemStack> entry : items.entrySet()) {
			args.getSender().sendMessage(AllAssets.ACCENT_COLOR + entry.getKey());
		}
		return;
	}

	@Help(name = "Spawnitem")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Spawnitem", "Spawns an item");
	}

}
