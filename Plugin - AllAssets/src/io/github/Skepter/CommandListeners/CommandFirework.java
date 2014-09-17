package io.github.Skepter.CommandListeners;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Commands.CommandFramework;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Misc.FireworkInventories;
import io.github.Skepter.Utils.CustomFireworkBuilder;
import io.github.Skepter.Utils.ErrorUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class CommandFirework implements Listener {

	public CommandFirework(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	private Map<UUID, CustomFireworkBuilder> map = new HashMap<UUID, CustomFireworkBuilder>();

	@CommandHandler(name = "firework", permission = "firework", description = "Creates a custom firework", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		Player player = null;
		try {
			player = args.getPlayer();
		} catch (final Exception e) {
			ErrorUtils.playerOnly(args.getSender());
			return;
		}
		player.openInventory(FireworkInventories.chooseType());
		return;
	}

	@EventHandler
	public void onClick(final InventoryClickEvent event) {
		final Player player = (Player) event.getWhoClicked();
		if (event.getAction().equals(InventoryAction.PICKUP_SOME)) {
			ItemStack item = event.getInventory().getItem(event.getSlot());
			switch (event.getInventory().getName()) {
			case "FireworkBuilder - Choose a firework type":
				if (check(event)) {
					CustomFireworkBuilder builder = new CustomFireworkBuilder(1);
					builder.setType(parseType(item));
					map.put(player.getUniqueId(), builder);
					player.openInventory(FireworkInventories.chooseColor(false));
				}
				break;
			case "FireworkBuilder - Choose a color":
				if (check(event)) {
					CustomFireworkBuilder builder = map.get(player);
					builder.addColor(parseColor(item));
					map.put(player.getUniqueId(), builder);
					player.openInventory(FireworkInventories.chooseColor(true));
				}
				break;
			case "FireworkBuilder - Choose a fade color":
				if (check(event)) {
					CustomFireworkBuilder builder = map.get(player);
					builder.addFade(parseColor(item));
					map.put(player.getUniqueId(), builder);
					player.openInventory(FireworkInventories.chooseFlicker(false));
				}
				break;
			case "FireworkBuilder - Do you want flickering?":
				if (check(event)) {
					CustomFireworkBuilder builder = map.get(player);
					if (item.getType().equals(Material.MAGMA_CREAM))
						builder.addFlicker(true);
					map.put(player.getUniqueId(), builder);
					player.openInventory(FireworkInventories.chooseFlicker(true));
				}
				break;
			case "FireworkBuilder - Do you want a trail?":
				if (check(event)) {
					CustomFireworkBuilder builder = map.get(player);
					if (item.getType().equals(Material.MAGMA_CREAM))
						builder.addTrail(true);
					map.put(player.getUniqueId(), builder);
					player.openInventory(FireworkInventories.choosePower());
				}
				break;
			case "FireworkBuilder - Choose a power size":
				if (check(event)) {
					CustomFireworkBuilder builder = map.get(player);
					builder.setPower(parsePower(item));
					map.remove(player.getUniqueId());
					player.getInventory().addItem(builder.getFirework());
					player.sendMessage(AllAssets.title + "Firework created!");
				}
				break;
			}
		}
	}

	private boolean check(InventoryClickEvent event) {
		if (!event.getAction().equals(InventoryAction.PICKUP_ONE))
			return false;
		if ((event.getSlot() == -999) || (event.getInventory().getItem(event.getSlot()) == null))
			return false;
		return true;
	}

	private int parsePower(ItemStack item) {
		if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			switch (item.getItemMeta().getDisplayName()) {
			case "Power: 0":
				return 0;
			case "Power: 1":
				return 1;
			case "Power: 2":
				return 2;
			case "Power: 3":
				return 3;
			}
		}
		return 0;
	}

	private Type parseType(ItemStack item) {
		if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			switch (item.getItemMeta().getDisplayName()) {
			case "Creeper":
				return Type.CREEPER;
			case "Ball":
				return Type.BALL;
			case "Large Ball":
				return Type.BALL_LARGE;
			case "Burst":
				return Type.BURST;
			case "Star":
				return Type.STAR;
			}
		}
		return Type.BALL;
	}

	private Color parseColor(ItemStack item) {
		if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			switch (item.getItemMeta().getDisplayName()) {
			case "Black":
				return Color.BLACK;
			case "Gray":
				return Color.GRAY;
			case "Silver":
				return Color.SILVER;
			case "Maroon":
				return Color.MAROON;
			case "Navy":
				return Color.NAVY;
			case "Blue":
				return Color.BLUE;
			case "Teal":
				return Color.TEAL;
			case "Aqua":
				return Color.AQUA;
			case "Olive":
				return Color.OLIVE;
			case "Lime":
				return Color.LIME;
			case "Green":
				return Color.GREEN;
			case "Purple":
				return Color.PURPLE;
			case "Fuchsia":
				return Color.FUCHSIA;
			case "Red":
				return Color.RED;
			case "Orange":
				return Color.ORANGE;
			case "Yellow":
				return Color.YELLOW;
			case "White":
				return Color.WHITE;
			}
		}
		return Color.WHITE;
	}
}