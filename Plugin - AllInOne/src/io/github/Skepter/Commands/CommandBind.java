package io.github.Skepter.Commands;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandBind implements Listener {

	public CommandBind(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "bind", permission = "AllInOne.bind", description = "Binds a command to an item", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		// Player player = args.getPlayer();
		/* Display help */
		// if.......... yeah that kinda stuff return;
	}

	@CommandHandler(name = "bind.add", permission = "AllInOne.bind", description = "Adds a command to the binded item", usage = "Use <command>")
	public void addBind(final CommandArgs args) {
		final Player player = args.getPlayer();

		final ItemStack item = player.getItemInHand();
		final ItemMeta meta = item.getItemMeta();
		if (!meta.hasLore()) {
			final List<String> lore = new ArrayList<String>();
			meta.setLore(lore);
		}
		final List<String> lore = meta.getLore();
		final String[] command = TextUtils.getMsgFromArgs(args.getArgs(), 0, args.getArgs().length);
		final String s = "/" + TextUtils.join(command, " ");
		lore.add(s);
		meta.setLore(lore);
		item.setItemMeta(meta);
		player.sendMessage(AllInOne.instance().title + "Successfully added " + s + " to your item!");
		return;
	}

	@CommandHandler(name = "bind.remove", permission = "AllInOne.bind", description = "Removes a command to the binded item", usage = "Use <command>")
	public void removeBind(final CommandArgs args) {
		final Player player = args.getPlayer();
		final ItemStack item = player.getItemInHand();
		final ItemMeta meta = item.getItemMeta();
		final List<String> lore = meta.getLore();
		lore.remove((Integer.parseInt(args.getArgs()[0]) - 1)); //put check here & debug on error
		meta.setLore(lore);
		item.setItemMeta(meta);
		player.sendMessage(AllInOne.instance().title + "Successfully removed " + " to your item!");
		return;
	}

	@EventHandler
	public void onInteract(final PlayerInteractEvent event) {
		if (ConfigHandler.config().getBoolean("bindRight")) {
			if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				performAction(event.getPlayer());
			}
		} else {
			if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				performAction(event.getPlayer());
			}
		}
	}

	private void performAction(final Player player) {
		if (player.getItemInHand().hasItemMeta()) {
			if(player.getItemInHand().getItemMeta().hasLore()) {
				for (final String s : player.getItemInHand().getItemMeta().getLore()) {
					if (s.startsWith("/")) {
						player.performCommand(s.substring(1));
					}
				}
			}
		}
	}
}
