package io.github.Skepter.Commands;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Utils.ErrorUtils;
import io.github.Skepter.Utils.PlayerUtils;

import org.bukkit.entity.Player;

public class CommandGhost {

	public CommandGhost(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "ghost", aliases = { "semivanish" }, permission = "AllInOne.ghost", description = "Allows you to turn into a ghost", usage = "Use <command>")
	public void command(final CommandArgs args) {
		Player player = args.getPlayer();
		if (args.getArgs().length == 0) {
			if (AllInOne.instance().ghostFactory.isGhost(player)) {
				AllInOne.instance().ghostFactory.setGhost(player, false);
				player.sendMessage(AllInOne.instance().title + "Ghost mode disabled");
				return;
			} else {
				AllInOne.instance().ghostFactory.setGhost(player, true);
				player.sendMessage(AllInOne.instance().title + "Ghost mode enabled");
				return;
			}
		} else if (args.getArgs().length == 1) {
			Player target = PlayerUtils.getPlayerFromString(args.getArgs()[0]);
			if (AllInOne.instance().ghostFactory.isGhost(target)) {
				AllInOne.instance().ghostFactory.setGhost(target, false);
				target.sendMessage(AllInOne.instance().title + "Ghost mode disabled");
				return;
			} else {
				AllInOne.instance().ghostFactory.setGhost(target, true);
				target.sendMessage(AllInOne.instance().title + "Ghost mode enabled");
				return;
			}
		} else {
			ErrorUtils.tooManyArguments(player);
			return;
		}
	}
}
