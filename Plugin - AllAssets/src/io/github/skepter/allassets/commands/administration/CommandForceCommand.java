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
/*******************************************************************************
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.skepter.allassets.commands.administration;

import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandForceCommand {

	public CommandForceCommand(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "forcecommand", aliases = { "sudo" }, permission = "forcecommand", description = "Force a player to run a command")
	public void onCommand(final CommandArgs args) {
		if (args.getArgs().length > 0)
			try {
				final Player target = PlayerGetter.getTarget(args.getSender(), args.getArgs()[0]);
				if (target != null) {
					final String s = TextUtils.join(TextUtils.getMsgFromArgs(args.getArgs(), 1, args.getArgs().length), " ");
					Bukkit.dispatchCommand(target, s);
				}
			} catch (final Exception e) {
				return;
			}
		else
			ErrorUtils.notEnoughArguments(args.getSender());
		return;
	}

}
