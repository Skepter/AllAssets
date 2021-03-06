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
package io.github.skepter.allassets.commands.economy;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.api.users.User;
import io.github.skepter.allassets.misc.Help;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.ErrorUtils;
import io.github.skepter.allassets.utils.utilclasses.TextUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetBalance {

	public CommandSetBalance(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "setbalance", aliases = { "setbal" }, permission = "setbalance", description = "Sets your balance")
	public void onCommand(final CommandArgs args) {
		//other balance
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			switch (args.getArgs().length) {
				case 0:
					printHelp(player);
					return;
				case 1:
					if (!TextUtils.isInteger(args.getArgs()[0])) {
						ErrorUtils.notAnInteger(player);
						return;
					} else {
						double target = Integer.parseInt(args.getArgs()[0]);
						new User(player).setBalance(target);
						player.sendMessage(Strings.TITLE + "Successfully changed balance to " + AllAssets.instance().economy.getBalance(player));
					}
				case 2:
					final Player target = PlayerGetter.getTarget(player, args.getArgs()[0]);
					if (target != null) {
						double targetBalance = Integer.parseInt(args.getArgs()[0]);
						new User(target).setBalance(targetBalance);
						player.sendMessage(Strings.TITLE + "Successfully changed " + target.getName() + "'s balance to " + AllAssets.instance().economy.getBalance(target));
					}
					return;
			}
		player.sendMessage(Strings.TITLE + "Balance: " + AllAssets.instance().economy.getBalance(player));
	}

	@Help(name = "SetBalance")
	public void printHelp(final CommandSender sender) {
		TextUtils.printHelp(sender, "SetBalance", "/setbalance <balance> - Sets your balance", "/setbalance <player> <balance> - Sets another player's balance");
	}
}
