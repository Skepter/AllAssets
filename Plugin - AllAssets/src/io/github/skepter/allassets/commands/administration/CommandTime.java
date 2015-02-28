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
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.CommandFramework.Completer;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class CommandTime {

	public CommandTime(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "time", permission = "time", description = "Sets the world time")
	public void onCommand(final CommandArgs args) {
		switch (args.getArgs().length) {
		case 0:
			ErrorUtils.notEnoughArguments(args.getSender());
		case 1:
			int time = 0;
			try {
				if (!TextUtils.isInteger(args.getArgs()[0]))
					ErrorUtils.notAnInteger(args.getSender());
				time = Integer.parseInt(args.getArgs()[0]);
				args.getSender().sendMessage(Strings.TITLE + "Time set to " + args.getArgs()[0]);
				return;
			} catch (final NumberFormatException e) {
				switch (args.getArgs()[0].toLowerCase()) {
				case "day":
					time = 1000;
					args.getSender().sendMessage(Strings.TITLE + "Time set to day");
					break;
				case "midday":
					time = 6000;
					args.getSender().sendMessage(Strings.TITLE + "Time set to midday");
					break;
				case "night":
					time = 14000;
					args.getSender().sendMessage(Strings.TITLE + "Time set to night");
					break;
				case "midnight":
					time = 18000;
					args.getSender().sendMessage(Strings.TITLE + "Time set to midnight");
					break;
				default:
					ErrorUtils.error(args.getSender(), "Couldn't set the time!");
					return;
				}
			}
			for (final World world : Bukkit.getWorlds())
				world.setTime(time);
			return;
		}
		return;
	}

	@CommandHandler(name = "day", permission = "time", description = "Sets the time to day", isListed = false)
	public void onCommandDay(final CommandArgs args) {
		for (final World world : Bukkit.getWorlds())
			world.setTime(1000);
		args.getSender().sendMessage(Strings.TITLE + "Time set to day");
	}

	@CommandHandler(name = "midday", permission = "time", description = "Sets the time to midday", isListed = false)
	public void onCommandMidday(final CommandArgs args) {
		for (final World world : Bukkit.getWorlds())
			world.setTime(6000);
		args.getSender().sendMessage(Strings.TITLE + "Time set to midday");
	}

	@CommandHandler(name = "night", permission = "time", description = "Sets the time to night", isListed = false)
	public void onCommandNight(final CommandArgs args) {
		for (final World world : Bukkit.getWorlds())
			world.setTime(14000);
		args.getSender().sendMessage(Strings.TITLE + "Time set to night");
	}

	@CommandHandler(name = "midnight", permission = "time", description = "Sets the time to midnight", isListed = false)
	public void onCommandMidnight(final CommandArgs args) {
		for (final World world : Bukkit.getWorlds())
			world.setTime(18000);
		args.getSender().sendMessage(Strings.TITLE + "Time set to midnight");
	}

	@Completer(name = "time")
	public List<String> timeCompleter(final CommandArgs args) {
		final List<String> list = new ArrayList<String>();
		list.add("day");
		list.add("midday");
		list.add("night");
		list.add("midnight");
		return list;
	}

}