package io.github.Skepter.AllAssets.CommandListeners;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.Commands.CommandFramework;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.AllAssets.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.AllAssets.Config.ConfigHandler;
import io.github.Skepter.AllAssets.Utils.ErrorUtils;
import io.github.Skepter.AllAssets.Utils.ItemUtils;
import io.github.Skepter.AllAssets.Utils.TextUtils;

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

	@CommandHandler(name = "bind", permission = "bind", description = "Binds a command to an item", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		player.sendMessage(TextUtils.title("Bind help"));
		player.sendMessage(AllAssets.houseStyleColor + "/bind add <command> - adds a command to the binded item");
		player.sendMessage(AllAssets.houseStyleColor + "/bind remove <number> - removes a command to the binded item");
		return;
	}

	@CommandHandler(name = "bind.add", permission = "bind", description = "Adds a command to the binded item", usage = "Use <command>")
	public void addBind(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		final ItemStack item = player.getItemInHand();
		final ItemMeta meta = item.getItemMeta();
		if (!meta.hasLore())
			meta.setLore(new ArrayList<String>());
		final List<String> lore = meta.getLore();
		final String[] command = TextUtils.getMsgFromArgs(args.getArgs(), 0, args.getArgs().length);
		final String s = "/" + TextUtils.join(command, " ");
		lore.add(s);
		meta.setLore(lore);
		item.setItemMeta(meta);
		try {
			ItemUtils.addGlow(item);
		} catch (final Exception e) {
		}
		player.sendMessage(AllAssets.title + "Successfully added " + s + "to your item!");
		return;
	}

	@CommandHandler(name = "bind.remove", permission = "bind", description = "Removes a command to the binded item", usage = "Use <command>")
	public void removeBind(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		final ItemStack item = player.getItemInHand();
		final ItemMeta meta = item.getItemMeta();
		final List<String> lore = meta.getLore();
		if (!TextUtils.isInteger(args.getArgs()[0])) {
			ErrorUtils.notAnInteger(player);
			return;
		}
		final String s = lore.get(Integer.parseInt(args.getArgs()[0]) - 1);
		lore.remove((Integer.parseInt(args.getArgs()[0]) - 1));
		meta.setLore(lore);
		item.setItemMeta(meta);
		if (!containsCommand(item))
			ItemUtils.removeGlow(item);
		player.sendMessage(AllAssets.title + "Successfully removed " + s + "from your item!");
		return;
	}

	@EventHandler
	public void playerInteract(final PlayerInteractEvent event) {
		if (ConfigHandler.instance().config().getBoolean("bindRight"))
			if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				performAction(event.getPlayer());
			else if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				performAction(event.getPlayer());
	}

	private boolean containsCommand(final ItemStack itemStack) {
		if (itemStack.hasItemMeta())
			if (itemStack.getItemMeta().hasLore())
				for (final String s : itemStack.getItemMeta().getLore())
					if (s.startsWith("/"))
						return true;
		return false;
	}

	private void performAction(final Player player) {
		if (player.getItemInHand().hasItemMeta())
			if (player.getItemInHand().getItemMeta().hasLore())
				for (final String s : player.getItemInHand().getItemMeta().getLore())
					if (s.startsWith("/"))
						player.performCommand(s.substring(1));
	}
}