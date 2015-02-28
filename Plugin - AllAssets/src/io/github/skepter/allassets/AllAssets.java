/*******************************************************************************
 * Skepter's licence
 * Copyright 2015 
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
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. The only exception is the BukkitDev staff who I have allowed permission to decompile (to search for malicious code)
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
package io.github.skepter.allassets;

import io.github.skepter.allassets.api.User;
import io.github.skepter.allassets.commandlisteners.CommandAFK;
import io.github.skepter.allassets.commandlisteners.CommandBind;
import io.github.skepter.allassets.commandlisteners.CommandCommandBlock;
import io.github.skepter.allassets.commandlisteners.CommandEnchant;
import io.github.skepter.allassets.commandlisteners.CommandFileBrowser;
import io.github.skepter.allassets.commandlisteners.CommandFileEditor;
import io.github.skepter.allassets.commandlisteners.CommandFirework;
import io.github.skepter.allassets.commandlisteners.CommandFriend;
import io.github.skepter.allassets.commandlisteners.CommandGod;
import io.github.skepter.allassets.commandlisteners.CommandSilence;
import io.github.skepter.allassets.commandlisteners.CommandStaffChat;
import io.github.skepter.allassets.commands.CommandClear;
import io.github.skepter.allassets.commands.CommandEnderchest;
import io.github.skepter.allassets.commands.CommandGhost;
import io.github.skepter.allassets.commands.CommandGrief;
import io.github.skepter.allassets.commands.CommandHelp;
import io.github.skepter.allassets.commands.CommandMore;
import io.github.skepter.allassets.commands.CommandNear;
import io.github.skepter.allassets.commands.CommandPTime;
import io.github.skepter.allassets.commands.CommandPWeather;
import io.github.skepter.allassets.commands.CommandRename;
import io.github.skepter.allassets.commands.CommandWorkbench;
import io.github.skepter.allassets.commands.administration.CommandAllAssets;
import io.github.skepter.allassets.commands.administration.CommandAnnouncer;
import io.github.skepter.allassets.commands.administration.CommandBackup;
import io.github.skepter.allassets.commands.administration.CommandButcher;
import io.github.skepter.allassets.commands.administration.CommandChestSearch;
import io.github.skepter.allassets.commands.administration.CommandClearchat;
import io.github.skepter.allassets.commands.administration.CommandConsoleLog;
import io.github.skepter.allassets.commands.administration.CommandDisable;
import io.github.skepter.allassets.commands.administration.CommandEnable;
import io.github.skepter.allassets.commands.administration.CommandExtinguish;
import io.github.skepter.allassets.commands.administration.CommandFly;
import io.github.skepter.allassets.commands.administration.CommandForceChat;
import io.github.skepter.allassets.commands.administration.CommandForceCommand;
import io.github.skepter.allassets.commands.administration.CommandGamemode;
import io.github.skepter.allassets.commands.administration.CommandInventory;
import io.github.skepter.allassets.commands.administration.CommandLog;
import io.github.skepter.allassets.commands.administration.CommandNMSGod;
import io.github.skepter.allassets.commands.administration.CommandOplist;
import io.github.skepter.allassets.commands.administration.CommandRemove;
import io.github.skepter.allassets.commands.administration.CommandSignEdit;
import io.github.skepter.allassets.commands.administration.CommandSpawnItem;
import io.github.skepter.allassets.commands.administration.CommandSpawnMob;
import io.github.skepter.allassets.commands.administration.CommandTime;
import io.github.skepter.allassets.commands.administration.CommandWeather;
import io.github.skepter.allassets.commands.cosmetics.CommandGlow;
import io.github.skepter.allassets.commands.cosmetics.CommandHead;
import io.github.skepter.allassets.commands.debug.CommandBatch;
import io.github.skepter.allassets.commands.debug.CommandDebug;
import io.github.skepter.allassets.commands.debug.CommandPing;
import io.github.skepter.allassets.commands.economy.CommandBalance;
import io.github.skepter.allassets.commands.economy.CommandBalancetop;
import io.github.skepter.allassets.commands.fun.CommandDiscoArmor;
import io.github.skepter.allassets.commands.fun.CommandFakeDeop;
import io.github.skepter.allassets.commands.fun.CommandFakeOp;
import io.github.skepter.allassets.commands.fun.CommandLaunch;
import io.github.skepter.allassets.commands.teleportation.CommandBack;
import io.github.skepter.allassets.commands.teleportation.CommandSetSpawn;
import io.github.skepter.allassets.commands.teleportation.CommandSpawn;
import io.github.skepter.allassets.commands.teleportation.CommandTp;
import io.github.skepter.allassets.commands.teleportation.CommandTphere;
import io.github.skepter.allassets.commands.teleportation.CommandWorlds;
import io.github.skepter.allassets.config.ConfigHandler;
import io.github.skepter.allassets.config.PlayerData;
import io.github.skepter.allassets.config.UUIDData;
import io.github.skepter.allassets.libs.ComphenixsGhostFactory;
import io.github.skepter.allassets.listeners.AnvilListener;
import io.github.skepter.allassets.listeners.ChatListener;
import io.github.skepter.allassets.listeners.CommandCooldownListener;
import io.github.skepter.allassets.listeners.ConsoleSayListener;
import io.github.skepter.allassets.listeners.CustomUnknownCommandListener;
import io.github.skepter.allassets.listeners.LogListener;
import io.github.skepter.allassets.listeners.MultiCommandListener;
import io.github.skepter.allassets.listeners.PlayerListener;
import io.github.skepter.allassets.listeners.PluginsCommandListener;
import io.github.skepter.allassets.listeners.ReloadCommandListener;
import io.github.skepter.allassets.listeners.ServerListingListener;
import io.github.skepter.allassets.listeners.SignListener;
import io.github.skepter.allassets.listeners.SkeletonArrowListener;
import io.github.skepter.allassets.listeners.StopCommandListener;
import io.github.skepter.allassets.misc.EnchantGlow;
import io.github.skepter.allassets.misc.NotificationsBoard;
import io.github.skepter.allassets.reflection.VaultReflection;
import io.github.skepter.allassets.tasks.TPS;
import io.github.skepter.allassets.utils.Files;
import io.github.skepter.allassets.utils.Strings;
import io.github.skepter.allassets.utils.utilclasses.FileUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/** AllAssets plugin, version 0.65 Alpha
 * 
 * Thanks to (Yes, I give you guys credit here - this couldn't have been done
 * without you and for that I am very grateful for your hard work!):
 * 
 * @BukkitForums Plo124 - IPUtils
 * @BukkitDev AmoebaMan - ReflectionUtil (DarkBlade12)
 * @BukkitDev mkremins - 'Fanciful' Messaging format
 * @BukkitDev Minnymin3 - CommandFramework class
 * @BukkitDev Comphenix - GhostFactory
 * @BukkitDev Logout400 - SimpleConfig, SimpleConfigManager
 * @BukkitDev Desht - ExperienceUtils
 * @BukkitDev DPOHVAR - ReflectionUtils
 * @BukkitDev RainoBot97 - SimpleScoreboard
 * @idkwho - TabText
 * @SpecialThanks EssentialsTeam - Plugin which this idea was based on
 * @SpecialThanks BukkitTeam - Making the entire thing possible
 * 
 * 
 * @authors Skepter, Tundra */

// Explore the ResourceBundle for setting Locale

/*
 * Add more methods to IDReader
 * Move it to API perhaps?
 * Add methods to retrieve the ID as an integer
 * Use it with that other tool ITemNames or something to send debug messages
 * add it to the config 
 * fix up CommandGive because only having 3 variables isn't good enough!
 */

//color customisation -

//normalColor: 9
//emphasisColor: b
//@ajcozzo

//recent players command - like seen, but for recent players
//do YesNo conversation for payments etc. (/pay
//friend list to find friends etc.
//a way to parse PARTS  of a player's name in commands
//play with UUIDs AGAIN - GameProfile OF entity, UserCache, player.uniqueID, UUIDData
//work on documentation - ensure EVERYTHING is REALLY clear.
/* - Things NOT to export when releasing Alpha version - Permissions
 * ExperienceUtils ItemNames MessagePart Fanciful IPUtils Reflections (All of
 * the Libs) TabText Resources Builds */

/*
 * Climb vines
 * check out commandBin
 * recipes command
 * insta-mine command
 * disposal chest
 * custom swords with poison perhaps & arrows perhaps
 * jail
 * redstone light netherrack/pumpkins/glowstone
 *
 */

/*highlight text utility
 * When a player says your username, send you (message) that message,
 * but with your username highlighted.
 * 
 * e.g. Skepter says "Hello amoniuszko20"
 * on amon's screen, the word "amoniuszko20" is in bold yellow (for example)
 * but on Skepter's screen, it's totally normal */
/*
 * A data accessing class which caches all of the data for each player
 * and then saves NEW data.
 * When using /reload (for aa), it then recaches all of the data - hence
 * making super speedy data delivery.
 * When a player leaves, remove them from the data?
 * 
 * clean up configHandler - it's so damn freaking messy!
 */
@SuppressWarnings("deprecation")
public class AllAssets extends JavaPlugin {

	/* The master switch - used for debug purposes*/
	public static boolean masterSwitch = false;
	public Chat chat = null;
	public Economy economy = null;
	/* Other stuff */
	public CommandFramework framework;

	public ComphenixsGhostFactory ghostFactory;
	/* Vault variables */
	public boolean hasVault = false;
	public Permission permission = null;

	public Map<UUID, Long> tempTimeMap;

	public static AllAssets instance() {
		return JavaPlugin.getPlugin(AllAssets.class);
	}

	/** Dev block - runs devvy stuff
	 * 
	 * @param loadTime - true means load at the END, false means load NORMALLY */
	private void dev(final boolean loadTime) {
		if (!loadTime) {
			//			new CommandGive(framework);
			new CommandHelp(framework);
			r(new CommandCommandBlock(framework));
		} else
			new VaultReflection().loadAAEco();
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
		return framework.handleCommand(sender, label, command, args);
	}

	@Override
	public void onDisable() {
		PlayerData.saveAllPlayers();
		CommandConsoleLog.players.clear();
		getServer().getScheduler().cancelTasks(this);

		if (!tempTimeMap.isEmpty())
			try {
				FileUtils.save(tempTimeMap, new File(Files.getStorage(), "tempTimeMap.bin"));
			} catch (final Exception e) {
				e.printStackTrace();
			}

		for (final Player player : Bukkit.getOnlinePlayers())
			if (CommandDiscoArmor.hasArmor(player))
				CommandDiscoArmor.toggleArmor(player);
		EnchantGlow.unLoad();
		getLogger().info(Strings.NO_COLOR_TITLE + getDescription().getVersion() + " has been disabled successfully");
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void onEnable() {
		getLogger().info("+---------------------------------+");
		getLogger().info("Enabling AllAssets version " + getDescription().getVersion());

		/* Some names will be removed - depends on whatever is in the Libs package */
		getLogger().info("AllAssets, created by Skepter and Tundra");
		//getLogger().info("Special thanks to: Plo124, AmoebaMan, mkremins, Minnymin3, Comphenix, Logout400, Desht, DPOHVAR and RainoBot97");

		/* A method of dealing with console errors and stuff ... I hope */
		((org.apache.logging.log4j.core.Logger) org.apache.logging.log4j.LogManager.getRootLogger()).addFilter(new LogListener(this));

		
		this.saveResource("ItemData.csv", true);

		/* Used to check if vault is available. If not, then disable the vault-specific commands such as /balance etc. */
		if ((Bukkit.getPluginManager().getPlugin("Vault") == null) || !Bukkit.getPluginManager().getPlugin("Vault").isEnabled()) {
			getLogger().warning("Vault not found, so some features may not be available");
			getLogger().info("To fully maximize AllAssets' potential (Economy, Permissions, Chat, Give commands), download Vault from http://dev.bukkit.org/bukkit-plugins/vault/");
			/* I put this here because if the plugin reloads, it may be set to true, however the owner of a server could have removed vault, thus some features would crash */
			hasVault = false;
		} else {
			hasVault = true;
			setupVault();
		}

		ghostFactory = new ComphenixsGhostFactory(this);
		framework.registerCommands(this);

		/** All variables should have been initialised now */

		//		if (masterSwitch)
		dev(false);
		//Nav

		/* This is the features.yml file which enables/disables features according to the users will */
		getLogger().info("Initializing commands according to features.yml");
		if (ConfigHandler.features().getBoolean("AFK"))
			r(new CommandAFK(framework));
		if (ConfigHandler.features().getBoolean("AllAssets"))
			new CommandAllAssets(framework);
		if (ConfigHandler.features().getBoolean("Announcer"))
			new CommandAnnouncer(framework);
		if (ConfigHandler.features().getBoolean("Back"))
			new CommandBack(framework);
		if (ConfigHandler.features().getBoolean("Backup"))
			new CommandBackup(framework);
		if (ConfigHandler.features().getBoolean("Batch"))
			new CommandBatch(framework);
		if (ConfigHandler.features().getBoolean("Bind"))
			r(new CommandBind(framework));
		if (ConfigHandler.features().getBoolean("Butcher"))
			new CommandButcher(framework);
		if (ConfigHandler.features().getBoolean("ChestSearch"))
			new CommandChestSearch(framework);
		if (ConfigHandler.features().getBoolean("Clear"))
			new CommandClear(framework);
		if (ConfigHandler.features().getBoolean("ClearChat"))
			new CommandClearchat(framework);
		if (ConfigHandler.features().getBoolean("ConsoleLog"))
			new CommandConsoleLog(framework);
		if (ConfigHandler.features().getBoolean("Debug"))
			r(new CommandDebug(framework));
		if (ConfigHandler.features().getBoolean("Disable"))
			new CommandDisable(framework);
		if (ConfigHandler.features().getBoolean("DiscoArmor"))
			new CommandDiscoArmor(framework);
		if (ConfigHandler.features().getBoolean("Enable"))
			new CommandEnable(framework);
		if (ConfigHandler.features().getBoolean("Enchant"))
			r(new CommandEnchant(framework));
		if (ConfigHandler.features().getBoolean("Enderchest"))
			new CommandEnderchest(framework);
		if (ConfigHandler.features().getBoolean("Extinguish"))
			new CommandExtinguish(framework);
		if (ConfigHandler.features().getBoolean("FakeDeop"))
			new CommandFakeDeop(framework);
		if (ConfigHandler.features().getBoolean("FakeOp"))
			new CommandFakeOp(framework);
		if (ConfigHandler.features().getBoolean("FileBrowser"))
			r(new CommandFileBrowser(framework));
		if (ConfigHandler.features().getBoolean("FileEditor"))
			r(new CommandFileEditor(framework));
		if (ConfigHandler.features().getBoolean("Firework"))
			r(new CommandFirework(framework));
		if (ConfigHandler.features().getBoolean("Fly"))
			new CommandFly(framework);
		if (ConfigHandler.features().getBoolean("ForceChat"))
			new CommandForceChat(framework);
		if (ConfigHandler.features().getBoolean("ForceCommand"))
			new CommandForceCommand(framework);
		if (ConfigHandler.features().getBoolean("Friend"))
			new CommandFriend(framework);
		if (ConfigHandler.features().getBoolean("Gamemode"))
			new CommandGamemode(framework);
		if (ConfigHandler.features().getBoolean("Ghost"))
			new CommandGhost(framework);
		if (ConfigHandler.features().getBoolean("Glow"))
			new CommandGlow(framework);
		if (ConfigHandler.features().getBoolean("God"))
			if (ConfigHandler.config().getBoolean("useNMSGod"))
				new CommandNMSGod(framework);
			else
				r(new CommandGod(framework));
		if (ConfigHandler.features().getBoolean("Grief"))
			new CommandGrief(framework);
		if (ConfigHandler.features().getBoolean("Head"))
			new CommandHead(framework);
		if (ConfigHandler.features().getBoolean("Inventory"))
			new CommandInventory(framework);
		if (ConfigHandler.features().getBoolean("Launch"))
			new CommandLaunch(framework);
		if (ConfigHandler.features().getBoolean("Log"))
			new CommandLog(framework);
		if (ConfigHandler.features().getBoolean("More"))
			new CommandMore(framework);
		if (ConfigHandler.features().getBoolean("Near"))
			new CommandNear(framework);
		if (ConfigHandler.features().getBoolean("Oplist"))
			new CommandOplist(framework);
		if (ConfigHandler.features().getBoolean("Ping"))
			new CommandPing(framework);
		if (ConfigHandler.features().getBoolean("Plugins"))
			r(new PluginsCommandListener());
		if (ConfigHandler.features().getBoolean("PTime"))
			new CommandPTime(framework);
		if (ConfigHandler.features().getBoolean("PWeather"))
			new CommandPWeather(framework);
		if (ConfigHandler.features().getBoolean("Reload"))
			r(new ReloadCommandListener());
		if (ConfigHandler.features().getBoolean("Remove"))
			new CommandRemove(framework);
		if (ConfigHandler.features().getBoolean("Rename"))
			new CommandRename(framework);
		//		if (ConfigHandler.features().getBoolean("Restore"))
		//			new CommandRestore(framework);
		if (ConfigHandler.features().getBoolean("SetSpawn"))
			new CommandSetSpawn(framework);
		if (ConfigHandler.features().getBoolean("SignEdit"))
			new CommandSignEdit(framework);
		if (ConfigHandler.features().getBoolean("Silence"))
			r(new CommandSilence(framework));
		if (ConfigHandler.features().getBoolean("StaffChat"))
			r(new CommandStaffChat(framework));
		if (ConfigHandler.features().getBoolean("Spawn"))
			new CommandSpawn(framework);
		if (ConfigHandler.features().getBoolean("SpawnItem"))
			new CommandSpawnItem(framework);
		if (ConfigHandler.features().getBoolean("SpawnMob"))
			new CommandSpawnMob(framework);
		if (ConfigHandler.features().getBoolean("Time"))
			new CommandTime(framework);
		if (ConfigHandler.features().getBoolean("Tp"))
			new CommandTp(framework);
		if (ConfigHandler.features().getBoolean("Tphere"))
			new CommandTphere(framework);
		if (ConfigHandler.features().getBoolean("Weather"))
			new CommandWeather(framework);
		if (ConfigHandler.features().getBoolean("Worlds"))
			new CommandWorlds(framework);
		if (ConfigHandler.features().getBoolean("Workbench"))
			new CommandWorkbench(framework);

		/* Vault commands. Only loads them if Vault is enabled so that:
		 * [1] Unused commands aren't loaded
		 * [2] It's pointless having commands which don't work */
		if (hasVault) {
			if (ConfigHandler.features().getBoolean("Balance"))
				new CommandBalance(framework);
			if (ConfigHandler.features().getBoolean("Balancetop"))
				new CommandBalancetop(framework);
		}

		/* Listeners */
		r(new CommandCooldownListener());
		r(new ChatListener());
		r(new SignListener());
		r(new PlayerListener());
		r(new CustomUnknownCommandListener());
		r(new AnvilListener());
		//TODO put into a thingymajig
		r(new StopCommandListener());
		if (ConfigHandler.features().getBoolean("ConsoleSay"))
			r(new ConsoleSayListener());
		if (ConfigHandler.features().getBoolean("MultiCommands"))
			r(new MultiCommandListener());
		if (ConfigHandler.features().getBoolean("PickupSkeletonArrows"))
			r(new SkeletonArrowListener());
		if (ConfigHandler.features().getBoolean("ServerListMOTDCustomisation"))
			r(new ServerListingListener());
		//Buggy and deprecated until fixed
		//r(new BlockPoweredListener());

		/** Reloading stuff */

		/* Update NotificationsBoard for all admins */
		NotificationsBoard.updateAll();

		for (final User user : User.onlineUsers())
			user.refreshPing();

		/* Update UUIDData file */
		UUIDData.reloadDataFile();
		for (final Player p : Bukkit.getOnlinePlayers())
			UUIDData.setData(p);

		/* Start TPS counter */
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new TPS(), 100L, 1L);

		/* Update tempTimeMap.bin file */
		try {
			if (new File(getDataFolder(), "tempTimeMap.bin").exists())
				tempTimeMap = (Map<UUID, Long>) FileUtils.load(new File(Files.getStorage(), "tempTimeMap.bin"));
		} catch (final Exception e) {
			e.printStackTrace();
		}

		getLogger().info(Strings.NO_COLOR_TITLE + "AllAssets has been enabled successfully");
		Bukkit.broadcast(Strings.TITLE + "Plugin reloaded!", "AllAssets.allassets");
		getLogger().info("+---------------------------------+");
		
		/* Post load stuff */
		if (masterSwitch)
			dev(true);
		postLoad();
	}

	@Override
	public void onLoad() {
		getLogger().info("+---------------------------------+");
		getLogger().info("Preparing AllAssets for enabling...");
		tempTimeMap = new HashMap<UUID, Long>();
		framework = new CommandFramework(this);
		new ConfigHandler();
		getLogger().info("+---------------------------------+");

	}

	private void postLoad() {

	}

	/* Easy system to add listeners */
	private void r(final Listener l) {
		if (masterSwitch)
			for (final Method method : l.getClass().getMethods())
				if (method.getAnnotation(EventHandler.class) != null)
					getLogger().info(Strings.SHORT_NO_COLOR_TITLE + "Added event: " + l.getClass().getSimpleName() + " - " + method.getName());
		getServer().getPluginManager().registerEvents(l, this);
	}

	private void setupVault() {
		final RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
			getLogger().info("Vault Economy system hooked");
		}
		final RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null) {
			permission = permissionProvider.getProvider();
			getLogger().info("Vault Permission system hooked");
		}
		final RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
		if (chatProvider != null) {
			chat = chatProvider.getProvider();
			getLogger().info("Vault Chat system hooked");
		}
	}
}