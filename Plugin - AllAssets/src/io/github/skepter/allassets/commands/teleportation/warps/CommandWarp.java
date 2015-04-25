/*******************************************************************************
 * Skepter's Licence
 * Copyright © 2015
 *
 * AllAssets, created by Skepter and Tundra
 *
 * You are able to:
 * * View AllAssets' source code on GitHub
 * * Experiment with the code as you wish
 * * Download the .jar files supplied on GitHub for your server
 *
 * You are NOT allowed to:
 * * Sell AllAssets - It is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter and Tundra
 * * Distribute it on any other website
 * * Decompile the code - It's pointless, time consuming and the source code is already on GitHub
 * * Steal the code from GitHub. Just ask and we're more than likely to let you copy some of it
 *
 * You cannot:
 * * Hold us liable for your actions
 ******************************************************************************/
package io.github.skepter.allassets.commands.teleportation.warps;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.serializers.LocationSerializer;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class CommandWarp {

	public CommandWarp(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "warp", permission = "warp", description = "Teleports to a certain warp")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			switch (args.getArgs().length) {
				case 0:
					printHelp(player);
					return;
				case 1:
					ConfigurationSection s = ConfigHandler.warps().getConfigurationSection(args.getArgs()[0].toLowerCase());
					if (s == null) {
						ErrorUtils.warpNotFound(player);
						return;
					} else {
						String warpname = ConfigHandler.warps().getString(args.getArgs()[0].toLowerCase() + ".name");
						String locationString = ConfigHandler.warps().getString(args.getArgs()[0].toLowerCase() + ".loc");
						Location location = LocationSerializer.LocFromString(locationString);
						player.teleport(location);
						player.sendMessage(Strings.TITLE + "Teleported to " + warpname);
						return;
					}
			}
		return;
	}

	@Help(name = "Warp")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Warp", "/warp <warpname> - Teleports to a certain warp");
	}
}