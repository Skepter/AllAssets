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
package io.github.Skepter.AllAssets.Commands.Administration;

import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.PlayerGetter;
import io.github.Skepter.AllAssets.API.Utils.Sphere;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Utils.UtilClasses.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.UtilClasses.TextUtils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class CommandExtinguish {

	public CommandExtinguish(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "extinguish", aliases = { "ext" }, permission = "extinguish", description = "Extinguishes fires")
	public void command(final CommandArgs args) {
		Player player = PlayerGetter.getPlayer(args);
		if (player != null) {
			switch (args.getArgs().length) {
			case 0:
				Sphere sphere = new Sphere(player.getLocation(), 120, Material.FIRE);
				for (Block b : sphere.getBlocks())
					b.setType(Material.AIR);
				return;
			case 1:
				if (TextUtils.isInteger(args.getArgs()[0])) {
					Sphere sphereWithCustomRadius = new Sphere(player.getLocation(), Integer.parseInt(args.getArgs()[0]), Material.FIRE);
					for (Block b : sphereWithCustomRadius.getBlocks())
						b.setType(Material.AIR);
				} else
					ErrorUtils.notAnInteger(args.getSender());
				return;
			}
			ErrorUtils.tooManyArguments(player);
		}
		return;
	}
}
