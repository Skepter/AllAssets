package io.github.Skepter.Commands;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;

import org.bukkit.entity.Player;

public class CommandBalance {

	public CommandBalance(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "balance", aliases = { "bal" }, permission = "balance", description = "Displays your balance", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		//other balance
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		player.sendMessage(AllAssets.title + "Balance: " + AllAssets.instance().economy.getBalance(player.getName()));
	}
}
