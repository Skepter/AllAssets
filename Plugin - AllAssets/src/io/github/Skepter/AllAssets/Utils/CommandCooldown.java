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
package io.github.Skepter.AllAssets.Utils;

import io.github.Skepter.AllAssets.AllAssets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandCooldown {

	public static List<UUID> cooldownPlayers = new ArrayList<UUID>();
	public static Map<UUID, Long> cooldownTimeMap = new HashMap<UUID, Long>();

	public CommandCooldown(final Player player, final long time) {
		cooldownPlayers.add(player.getUniqueId());
		cooldownTimeMap.put(player.getUniqueId(), (System.currentTimeMillis() / 1000) + time);
		new BukkitRunnable() {
			@Override
			public void run() {
				cooldownPlayers.remove(player.getUniqueId());
			}
		}.runTaskLater(AllAssets.instance(), time * 20);
	}

	public static boolean isOnCooldown(final Player player) {
		return cooldownPlayers.contains(player.getUniqueId()) ? true : false;
	}

}
