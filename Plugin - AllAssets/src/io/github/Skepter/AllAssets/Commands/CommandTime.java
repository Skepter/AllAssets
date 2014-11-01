package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Commands.CommandFramework.Completer;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class CommandTime {

	public CommandTime(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "time", permission = "time", description = "Sets the world time", usage = "Use <command>")
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
				args.getSender().sendMessage(AllAssets.title + "Time set to " + args.getArgs()[0]);
				return;
			} catch (final NumberFormatException e) {
				switch (args.getArgs()[0].toLowerCase()) {
				case "day":
					time = 1000;
					args.getSender().sendMessage(AllAssets.title + "Time set to day");
					break;
				case "midday":
					time = 6000;
					args.getSender().sendMessage(AllAssets.title + "Time set to midday");
					break;
				case "night":
					time = 14000;
					args.getSender().sendMessage(AllAssets.title + "Time set to night");
					break;
				case "midnight":
					time = 18000;
					args.getSender().sendMessage(AllAssets.title + "Time set to midnight");
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

	@CommandHandler(name = "day", permission = "time", description = "Sets the time to day", usage = "Use <command>", isListed = false)
	public void onCommandDay(final CommandArgs args) {
		for (final World world : Bukkit.getWorlds())
			world.setTime(1000);
		args.getSender().sendMessage(AllAssets.title + "Time set to day");
	}

	@CommandHandler(name = "midday", permission = "time", description = "Sets the time to midday", usage = "Use <command>", isListed = false)
	public void onCommandMidday(final CommandArgs args) {
		for (final World world : Bukkit.getWorlds())
			world.setTime(6000);
		args.getSender().sendMessage(AllAssets.title + "Time set to midday");
	}

	@CommandHandler(name = "night", permission = "time", description = "Sets the time to night", usage = "Use <command>", isListed = false)
	public void onCommandNight(final CommandArgs args) {
		for (final World world : Bukkit.getWorlds())
			world.setTime(14000);
		args.getSender().sendMessage(AllAssets.title + "Time set to night");
	}

	@CommandHandler(name = "midnight", permission = "time", description = "Sets the time to midnight", usage = "Use <command>", isListed = false)
	public void onCommandMidnight(final CommandArgs args) {
		for (final World world : Bukkit.getWorlds())
			world.setTime(18000);
		args.getSender().sendMessage(AllAssets.title + "Time set to midnight");
	}

	@Completer(name = "time")
	public List<String> testCompleter(final CommandArgs args) {
		final List<String> list = new ArrayList<String>();
		list.add("day");
		list.add("midday");
		list.add("night");
		list.add("midnight");
		return list;
	}

}