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
package io.github.skepter.allassets.commands.other;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSuffix {

	public CommandSuffix(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "suffix", permission = "suffix", description = "Sets your suffix")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			switch (args.getArgs().length) {
				case 0:
					printHelp(player);
					return;
				default:
					String suffix = TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length);
					if (args.getArgs()[0].equalsIgnoreCase("remove")) {
						AllAssets.instance().chat.setPlayerSuffix(player, "");
						player.sendMessage(Strings.TITLE + "Removed your suffix");
					} else {
						AllAssets.instance().chat.setPlayerSuffix(player, suffix);
						player.sendMessage(Strings.TITLE + "Set suffix to " + ChatColor.translateAlternateColorCodes('&', suffix));
					}
					return;
			}
		return;
	}

	@Help(name = "Suffix")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "Suffix", "/suffix <suffix> - Sets your suffix", "/suffix remove - Removes your suffix");
	}
}
