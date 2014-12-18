/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
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
package io.github.Skepter.AllAssets.Commands;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.CommandFramework;
import io.github.Skepter.AllAssets.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Config.ConfigHandler;
import io.github.Skepter.AllAssets.Tasks.AnnouncerTask;
import io.github.Skepter.AllAssets.Utils.TextUtils;

import org.bukkit.Bukkit;

public class CommandAnnouncer {

	public CommandAnnouncer(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	private int taskID;

	@CommandHandler(name = "announcer", aliases = { "announce" }, permission = "announcer", description = "Configure the scheduled announcer", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		return; //help page
	}

	@CommandHandler(name = "announcer.start", permission = "announcer", description = "Start the announcer", usage = "Use <command>")
	public void startAnnouncer(final CommandArgs args) {
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(AllAssets.instance(), new AnnouncerTask(), 0, ConfigHandler.instance().config().getInt("announcerTime"));
		return;
	}

	@CommandHandler(name = "announcer.stop", permission = "announcer", description = "Stop the announcer", usage = "Use <command>")
	public void stopAnnouncer(final CommandArgs args) {
		Bukkit.getScheduler().cancelTask(taskID);
		return;
	}

	@CommandHandler(name = "announcer.list", permission = "announcer", description = "List all announcements", usage = "Use <command>")
	public void listAnnouncements(final CommandArgs args) {
		args.getSender().sendMessage(TextUtils.title("Announcer list"));
		for (String key : ConfigHandler.announcer().getKeys()) {
			args.getSender().sendMessage(AllAssets.houseStyleColor + key + " " + ConfigHandler.announcer().getString(key));
		}
	}

	@CommandHandler(name = "announcer.add", permission = "announcer", description = "Add a new announcement", usage = "Use <command>")
	public void addAnnouncement(final CommandArgs args) {
		String message = TextUtils.getMsgStringFromArgs(args.getArgs(), 0, args.getArgs().length);
		setAnnouncer(message);
		args.getSender().sendMessage(AllAssets.title + "Successfully added a new message to the announcer");
	}

	@CommandHandler(name = "announcer.remove", permission = "announcer", description = "Remove an announcement", usage = "Use <command>")
	public void removeAnnouncement(final CommandArgs args) {
		if (TextUtils.isInteger(args.getArgs()[0]))
			ConfigHandler.announcer().removeKey(String.valueOf(args.getArgs()[0]));
	}

	private void setAnnouncer(String data) {
		int ID = 1;
		try {
			ID = ConfigHandler.announcer().getKeys().size() + 1;
		} catch (Exception e) {
			//Catch nothing since if it fails, the ID will default to 1 anyway
		}
		ConfigHandler.announcer().set(String.valueOf(ID), data);
	}

}