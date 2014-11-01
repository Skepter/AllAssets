package io.github.Skepter.AllAssets.Listeners;

import io.github.Skepter.AllAssets.AllAssets;
import io.github.Skepter.AllAssets.API.LogEvent.LogType;
import io.github.Skepter.AllAssets.API.User;
import io.github.Skepter.AllAssets.Commands.CommandLog;
import io.github.Skepter.AllAssets.Config.ConfigHandler;
import io.github.Skepter.AllAssets.Config.UUIDData;
import io.github.Skepter.AllAssets.Misc.NotificationsBoard;
import io.github.Skepter.AllAssets.Serializers.InventorySerializer;
import io.github.Skepter.AllAssets.Tasks.AnyLeashTask;
import io.github.Skepter.AllAssets.Tasks.InstantRespawnTask;
import io.github.Skepter.AllAssets.Utils.FireworkUtils;
import io.github.Skepter.AllAssets.Utils.MathUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements Listener {

	//ensure that if the plugin was added WHEN the player is ALREADY online
	//some data will not be initialized, so ensure that data is fixed and run for each player online.

	@EventHandler
	public void playerJoin(final PlayerJoinEvent event) {
		AllAssets.instance().getLogger().info(event.getPlayer().getName() + "'s UUID is: " + event.getPlayer().getUniqueId().toString());

		UUIDData.setData(event.getPlayer());

		final User user = new User(event.getPlayer());
		user.setJoinCount(user.getJoinCount() + 1);
		if (!user.IPs().contains(event.getPlayer().getAddress().getHostName())) {
			final List<String> ips = user.IPs();
			if (!ips.isEmpty())
				CommandLog.addLog(event.getPlayer().getName() + " joined with a new IP", LogType.OTHER);
			ips.add(event.getPlayer().getAddress().getHostName());
			user.setIPs(ips);
		}

		/* Sometimes needs a boost before the ping actually comes */
		user.getPing();

		AllAssets.instance().tempTimeMap.put(event.getPlayer().getUniqueId(), System.currentTimeMillis());

		if (ConfigHandler.instance().features().getBoolean("JoinActions")) {
			if (ConfigHandler.instance().features().getBoolean("UniquePlayers"))
				event.getPlayer().sendMessage(AllAssets.title + Bukkit.getOfflinePlayers().length + " unique players have joined this server");
			if (ConfigHandler.instance().features().getBoolean("TotalTime"))
				event.getPlayer().sendMessage(AllAssets.title + "Total time played: " + MathUtils.formatDate(user.getTotalTimePlayed()));
			if (ConfigHandler.instance().features().getBoolean("FireworkOnJoin"))
				for (int i = 0; i < new Random().nextInt(5); i++)
					FireworkUtils.spawnRandomFirework(event.getPlayer().getLocation());
			if (ConfigHandler.instance().features().getBoolean("CommandsOnJoin"))
				for (String string : ConfigHandler.instance().config().getStringList("commandsOnJoin"))
					Bukkit.dispatchCommand(event.getPlayer(), string.replace("{PLAYERNAME}", event.getPlayer().getName()));
		}

		AllAssets.instance().ghostFactory.addPlayer(event.getPlayer());

		new NotificationsBoard(event.getPlayer()).updateBoard();
		//TODO - see NotificationsBoard
	}

	@EventHandler
	public void playerLeave(final PlayerQuitEvent event) {
		event.getPlayer().resetPlayerTime();
		event.getPlayer().resetPlayerWeather();
		final User user = new User(event.getPlayer());
		user.setTimeSinceLastPlay(System.currentTimeMillis());
		if(AllAssets.instance().tempTimeMap.containsKey(event.getPlayer().getUniqueId())) {
			user.setTotalTimePlayed(user.getTotalTimePlayed() + (System.currentTimeMillis() - AllAssets.instance().tempTimeMap.get(event.getPlayer().getUniqueId())));
			AllAssets.instance().tempTimeMap.remove(event.getPlayer().getUniqueId());
		} else {
			//error! it should be there because it was added on player join...
			//this shouldn't be possible so -.- yeah
		}
	}

	@EventHandler
	public void playerPlaceBlockOnHead(final InventoryClickEvent event) {
		if (((event.isLeftClick() || event.isRightClick()) && event.getAction().equals(InventoryAction.PLACE_ONE)) || event.getAction().equals(InventoryAction.PLACE_ALL) || event.getAction().equals(InventoryAction.PLACE_SOME))
			if ((event.getSlot() == 39) && event.getInventory().getType().equals(InventoryType.CRAFTING) && ConfigHandler.instance().features().getBoolean("BlockHeads")) {
				event.getWhoClicked().getInventory().setHelmet(event.getCursor());
				new BukkitRunnable() {
					@Override
					public void run() {
						event.getWhoClicked().setItemOnCursor(null);
					}
				}.runTaskLater(AllAssets.instance(), 1L);
			}
	}

	@EventHandler
	public void playerTeleport(final PlayerTeleportEvent event) {
		final User user = new User(event.getPlayer());
		user.setLastLoc(event.getFrom());
	}

	@EventHandler
	public void playerDeath(final PlayerDeathEvent event) {
		final User user = new User(event.getEntity());
		user.setLastLoc(event.getEntity().getLocation());

		final Inventory inv = event.getEntity().getInventory();
		user.setLastInventory(InventorySerializer.toString(inv));

		if (ConfigHandler.instance().features().getBoolean("InstantDeathRespawn")) {
			final Player p = event.getEntity();
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new InstantRespawnTask(p), 1L);
		}

		if (ConfigHandler.instance().features().getBoolean("DeathCount")) {
			user.setDeathCount(user.getDeathCount() + 1);
			user.getPlayer().sendMessage(AllAssets.title + "You have died " + user.getDeathCount() + " times!");
		}

		if (ConfigHandler.instance().features().getBoolean("DeathSigns")) {
			final Location loc = event.getEntity().getLocation();
			loc.getBlock().setType(Material.AIR);
			loc.getBlock().setType(Material.SIGN_POST);
			final Sign sign = (Sign) loc.getBlock().getState();
			sign.setLine(0, event.getEntity().getName());
			sign.setLine(1, "Died here on");
			sign.setLine(2, new SimpleDateFormat("MMM W").format(new Date()));
			sign.setLine(3, new SimpleDateFormat("hh:mm a").format(new Date()));
			sign.update();

			final BlockPlaceEvent blockEvent = new BlockPlaceEvent(sign.getBlock(), sign.getBlock().getState(), sign.getBlock().getRelative(BlockFace.DOWN), null, user.getPlayer(), false);
			if (blockEvent.canBuild())
				blockEvent.setCancelled(true);
			Bukkit.getServer().getPluginManager().callEvent(blockEvent);
		}
	}

	@EventHandler
	public void playerSwitchItemInHand(final PlayerItemHeldEvent event) {
		final Player player = event.getPlayer();
		final ItemStack i = player.getInventory().getItem(event.getNewSlot());
		if ((i == null) || (i.getType() == Material.AIR))
			return;
		//Work on this TODO
		//		final Set<Entry<Enchantment, Integer>> entrySet = i.getEnchantments().entrySet();
		//		for (final Entry<Enchantment, Integer> e : entrySet)
		//			if (e.getValue() > 5)
		//				if (!player.hasPermission("AllAssets.illegalitems")) {
		//					player.setItemInHand(null);
		//					CommandLog.addOtherLog(ChatColor.BLUE + player.getName() + ChatColor.WHITE + " had an illegal item!");
		//				}
	}

	@EventHandler
	public void playerMove(final PlayerMoveEvent event) {
		if (ConfigHandler.instance().features().getBoolean("FlyBreakSpeedModifier"))
			if (event.getPlayer().isFlying() && event.getPlayer().getAllowFlight() && !event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100000, 18));
			else
				event.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
		//Nav Just testing - I wanna see this
		new NotificationsBoard(event.getPlayer()).updateBoard();
	}

	@EventHandler
	public void playerUseEnderpearl(final PlayerInteractEvent event) {
		if (ConfigHandler.instance().features().getBoolean("CreativeEnderpearl"))
			if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE) && (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && event.getPlayer().getItemInHand().getType().equals(Material.ENDER_PEARL))
				event.getPlayer().launchProjectile(EnderPearl.class);
	}

	@EventHandler
	public void playerAddLeash(final PlayerInteractEntityEvent event) {
		if (ConfigHandler.instance().features().getBoolean("AnyLeash"))
			if (event.getPlayer().getItemInHand().getType().equals(Material.LEASH) && event.getPlayer().hasPermission("AllAssets.anyleash") && (event.getRightClicked() instanceof LivingEntity)) {
				event.setCancelled(true);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(AllAssets.instance(), new AnyLeashTask(event.getPlayer(), event.getRightClicked()), 1L);
			}
	}
}