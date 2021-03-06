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
package io.github.skepter.allassets.commandlisteners;

import io.github.skepter.allassets.AllAssets;
import io.github.skepter.allassets.CommandFramework;
import io.github.skepter.allassets.CommandFramework.CommandArgs;
import io.github.skepter.allassets.CommandFramework.CommandHandler;
import io.github.skepter.allassets.PlayerGetter;
import io.github.skepter.allassets.tasks.DiscoArmorTask;
import io.github.skepter.allassets.utils.DoubleMap;
import io.github.skepter.allassets.utils.Strings;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class CommandDiscoArmor implements Listener {

	private static DoubleMap<UUID, Integer, ItemStack[]> map = new DoubleMap<UUID, Integer, ItemStack[]>();

	public CommandDiscoArmor(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "discoarmor", aliases = { "darmor", "partyarmor", "parmor" }, permission = "discoarmor", description = "Gives you flashing armor")
	public void onCommand(final CommandArgs args) {
		final Player player = PlayerGetter.getPlayer(args);
		if (player != null)
			toggleArmor(player);
		return;
	}
	
	@EventHandler
	public void onRemoveArmor(InventoryClickEvent event) {
		if(event.getSlotType().equals(SlotType.ARMOR) && hasArmor((Player) event.getWhoClicked())) {
			event.setCancelled(true);
		}
	}
	
	/** Checks if the player has disco armor enabled */
	public static boolean hasArmor(final Player player) {
		return map.containsKey(player.getUniqueId());
	}

	public static void toggleArmor(final Player player) {
		if (map.containsKey(player.getUniqueId())) {
			Bukkit.getScheduler().cancelTask(map.getValue1(player.getUniqueId()));
			player.getInventory().setArmorContents(map.getValue2(player.getUniqueId()));
			map.remove(player.getUniqueId());
			player.sendMessage(Strings.TITLE + "Your disco armor was removed");
		} else {
			final BukkitTask i = Bukkit.getScheduler().runTaskTimer(AllAssets.instance(), new DiscoArmorTask(player), 0L, 3L);
			map.put(player.getUniqueId(), i.getTaskId(), player.getInventory().getArmorContents());
			player.sendMessage(Strings.TITLE + "You are now wearing disco armor!");
		}
	}

}
