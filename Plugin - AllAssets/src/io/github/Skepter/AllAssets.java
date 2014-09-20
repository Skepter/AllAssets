package io.github.Skepter;

import io.github.Skepter.CommandListeners.CommandAFK;
import io.github.Skepter.CommandListeners.CommandBind;
import io.github.Skepter.CommandListeners.CommandEnchant;
import io.github.Skepter.CommandListeners.CommandFirework;
import io.github.Skepter.CommandListeners.CommandGod;
import io.github.Skepter.CommandListeners.CommandStaffChat;
import io.github.Skepter.Commands.CommandAllAssets;
import io.github.Skepter.Commands.CommandBack;
import io.github.Skepter.Commands.CommandBalance;
import io.github.Skepter.Commands.CommandBalancetop;
import io.github.Skepter.Commands.CommandBatch;
import io.github.Skepter.Commands.CommandButcher;
import io.github.Skepter.Commands.CommandChestSearch;
import io.github.Skepter.Commands.CommandClear;
import io.github.Skepter.Commands.CommandClearchat;
import io.github.Skepter.Commands.CommandConsoleLog;
import io.github.Skepter.Commands.CommandDebug;
import io.github.Skepter.Commands.CommandDisable;
import io.github.Skepter.Commands.CommandDiscoArmor;
import io.github.Skepter.Commands.CommandEnable;
import io.github.Skepter.Commands.CommandFly;
import io.github.Skepter.Commands.CommandForceChat;
import io.github.Skepter.Commands.CommandFramework;
import io.github.Skepter.Commands.CommandGamemode;
import io.github.Skepter.Commands.CommandGhost;
import io.github.Skepter.Commands.CommandInventory;
import io.github.Skepter.Commands.CommandLaunch;
import io.github.Skepter.Commands.CommandLog;
import io.github.Skepter.Commands.CommandNMSGod;
import io.github.Skepter.Commands.CommandNear;
import io.github.Skepter.Commands.CommandOplist;
import io.github.Skepter.Commands.CommandPTime;
import io.github.Skepter.Commands.CommandPWeather;
import io.github.Skepter.Commands.CommandPing;
import io.github.Skepter.Commands.CommandRename;
import io.github.Skepter.Commands.CommandSignEdit;
import io.github.Skepter.Commands.CommandTime;
import io.github.Skepter.Commands.CommandTp;
import io.github.Skepter.Commands.CommandTphere;
import io.github.Skepter.Commands.CommandWeather;
import io.github.Skepter.Commands.CommandWorlds;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Config.PlayerData;
import io.github.Skepter.Config.UUIDData;
import io.github.Skepter.Libs.ComphenixsGhostFactory;
import io.github.Skepter.Listeners.ChatListener;
import io.github.Skepter.Listeners.ConsoleSayListener;
import io.github.Skepter.Listeners.CustomUnknownCommandListener;
import io.github.Skepter.Listeners.LogListener;
import io.github.Skepter.Listeners.MultiCommandListener;
import io.github.Skepter.Listeners.PlayerListener;
import io.github.Skepter.Listeners.PluginsCommandListener;
import io.github.Skepter.Listeners.ReloadCommandListener;
import io.github.Skepter.Listeners.ServerListingListener;
import io.github.Skepter.Listeners.SignListener;
import io.github.Skepter.Listeners.SkeletonArrowListener;
import io.github.Skepter.Tasks.TPS;
import io.github.Skepter.Utils.JavaUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/* AllAssets plugin, version 0.2
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
 * @author Skepter */

// something like WE with /replace <block> <radius>
// firework interface like Enchant - use For loops to generate it (similar to enchant inv)
// /griefReport command - adds to the /log
// Explore the ResourceBundle for setting Locale
// A way to mute a player which stops all other chat being sent to that player except admin

// sort out switch statements on strings and use toLowerCase to make it case safe
// add messages after commands (e.g. you successfully set the time to day etc.)
// command allassets pluginFile instead of devPluginFile
// how many unique players

// plugin config manager thingy

// I'm sorry everyone who doesn't speak English. It's an English plugin.
// built in announcer

//color customisation -
//normalColor: 9
//emphasisColor: b
//@ajcozzo
//disco armor

//recent players command - like seen, but for recent players
//still able to tp when they're offline
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
public class AllAssets extends JavaPlugin {

	/* Messages - shouldn't really be here but meh -.- */
	public final static String title = ChatColor.BLUE + "[" + ChatColor.AQUA + "AllAssets" + ChatColor.BLUE + "]" + ChatColor.WHITE + " ";
	public final static String titleNoColor = "[AllAssets] ";
	public final static String error = ChatColor.DARK_RED + "[" + ChatColor.RED + "AllAssets" + ChatColor.DARK_RED + "]" + ChatColor.RED + " ";
	public final static String houseStyleColor = ChatColor.AQUA + "";

	/* Vault stuff */
	public boolean hasVault = false;
	public Economy economy = null;
	public Permission permission = null;
	public Chat chat = null;

	/* Other stuff */
	public CommandFramework framework;
	public Map<UUID, Long> tempTimeMap;
	public ComphenixsGhostFactory ghostFactory;

	@Override
	public void onEnable() {
		getLogger().info("+---------------------------------+");
		getLogger().info("Initializing AllAssets version " + getDescription().getVersion());

		/* Some names will be removed - depends on whatever is in the Libs package */
		getLogger().info("AllAssets, created by Skepter. Special thanks to: Plo124, AmoebaMan, mkremins, Minnymin3, Comphenix, Logout400, Desht, DPOHVAR and RainoBot97");

		if (!new File(getDataFolder(), "Read me.txt").exists())
			saveResource("Read me.txt", false);

		/* A method of dealing with console errors and stuff ... I hope */
		((org.apache.logging.log4j.core.Logger) org.apache.logging.log4j.LogManager.getRootLogger()).addFilter(new LogListener(this));

		tempTimeMap = new HashMap<UUID, Long>();
		framework = new CommandFramework(this);

		new ConfigHandler();

		/* Used to check if vault is available. If not, then disable the vault-specific commands such as /balance etc. */
		if ((Bukkit.getPluginManager().getPlugin("Vault") == null) || !Bukkit.getPluginManager().getPlugin("Vault").isEnabled()) {
			getLogger().warning("Vault not found, so some features may not be available");
			/* I put this here because if the plugin reloads, it may be set to true, however the owner of a server could have removed vault, thus some features would crash */
			hasVault = false;
		} else {
			hasVault = true;
			setupVault();
			getLogger().info("Vault has been found and hooked into successfully");
		}

		ghostFactory = new ComphenixsGhostFactory(this);
		framework.registerCommands(this);

		/* This is the features.yml file which enables/disables features according to the users will */
		getLogger().info("Initializing commands according to features.yml");
		if (ConfigHandler.instance().features().getBoolean("AFK"))
			r(new CommandAFK(framework));
		if (ConfigHandler.instance().features().getBoolean("AllAssets"))
			new CommandAllAssets(framework);
		if (ConfigHandler.instance().features().getBoolean("Back"))
			new CommandBack(framework);
		if (ConfigHandler.instance().features().getBoolean("Batch"))
			new CommandBatch(framework);
		if (ConfigHandler.instance().features().getBoolean("Bind"))
			r(new CommandBind(framework));
		if (ConfigHandler.instance().features().getBoolean("Butcher"))
			new CommandButcher(framework);
		if (ConfigHandler.instance().features().getBoolean("ChestSearch"))
			new CommandChestSearch(framework);
		if (ConfigHandler.instance().features().getBoolean("Clear"))
			new CommandClear(framework);
		if (ConfigHandler.instance().features().getBoolean("ClearChat"))
			new CommandClearchat(framework);
		if (ConfigHandler.instance().features().getBoolean("ConsoleLog"))
			new CommandConsoleLog(framework);
		if (ConfigHandler.instance().features().getBoolean("Debug"))
			new CommandDebug(framework);
		if (ConfigHandler.instance().features().getBoolean("Disable"))
			new CommandDisable(framework);
		if (ConfigHandler.instance().features().getBoolean("DiscoArmor"))
			new CommandDiscoArmor(framework);
		if (ConfigHandler.instance().features().getBoolean("Enchant"))
			r(new CommandEnchant(framework));
		if (ConfigHandler.instance().features().getBoolean("Enable"))
			new CommandEnable(framework);
		if (ConfigHandler.instance().features().getBoolean("ForceChat"))
			new CommandForceChat(framework);
		if (ConfigHandler.instance().features().getBoolean("Firework"))
			r(new CommandFirework(framework));
		if (ConfigHandler.instance().features().getBoolean("Fly"))
			new CommandFly(framework);
		if (ConfigHandler.instance().features().getBoolean("Gamemode"))
			new CommandGamemode(framework);
		if (ConfigHandler.instance().features().getBoolean("Ghost"))
			new CommandGhost(framework);
		if (ConfigHandler.instance().features().getBoolean("God"))
			if (ConfigHandler.instance().config().getBoolean("useNMSGod"))
				new CommandNMSGod(framework);
			else
				r(new CommandGod(framework));
		if (ConfigHandler.instance().features().getBoolean("Inventory"))
			new CommandInventory(framework);
		if (ConfigHandler.instance().features().getBoolean("Launch"))
			new CommandLaunch(framework);
		if (ConfigHandler.instance().features().getBoolean("Log"))
			new CommandLog(framework);
		if (ConfigHandler.instance().features().getBoolean("Near"))
			new CommandNear(framework);
		if (ConfigHandler.instance().features().getBoolean("Oplist"))
			new CommandOplist(framework);
		if (ConfigHandler.instance().features().getBoolean("Ping"))
			new CommandPing(framework);
		if (ConfigHandler.instance().features().getBoolean("Plugins"))
			r(new PluginsCommandListener());
		if (ConfigHandler.instance().features().getBoolean("PTime"))
			new CommandPTime(framework);
		if (ConfigHandler.instance().features().getBoolean("PWeather"))
			new CommandPWeather(framework);
		if (ConfigHandler.instance().features().getBoolean("Reload"))
			r(new ReloadCommandListener());
		if (ConfigHandler.instance().features().getBoolean("Rename"))
			new CommandRename(framework);
		if (ConfigHandler.instance().features().getBoolean("SignEdit"))
			new CommandSignEdit(framework);
		if (ConfigHandler.instance().features().getBoolean("StaffChat"))
			r(new CommandStaffChat(framework));
		if (ConfigHandler.instance().features().getBoolean("Time"))
			new CommandTime(framework);
		if (ConfigHandler.instance().features().getBoolean("Tp"))
			new CommandTp(framework);
		if (ConfigHandler.instance().features().getBoolean("Tphere"))
			new CommandTphere(framework);
		if (ConfigHandler.instance().features().getBoolean("Weather"))
			new CommandWeather(framework);
		if (ConfigHandler.instance().features().getBoolean("Worlds"))
			new CommandWorlds(framework);

		/* Vault commands. Only loads them if Vault is enabled so that:
		 * [1] Unused commands aren't loaded
		 * [2] It's pointless having commands which don't work */
		if (hasVault) {
			if (ConfigHandler.instance().features().getBoolean("Balance"))
				new CommandBalance(framework);
			if (ConfigHandler.instance().features().getBoolean("Balancetop"))
				new CommandBalancetop(framework);
		}

		/* Listeners */
		r(new ChatListener());
		r(new SignListener());
		r(new PlayerListener());
		r(new CustomUnknownCommandListener());
		if (ConfigHandler.instance().features().getBoolean("ConsoleSay"))
			r(new ConsoleSayListener());
		if (ConfigHandler.instance().features().getBoolean("MultiCommands"))
			r(new MultiCommandListener());
		if (ConfigHandler.instance().features().getBoolean("PickupSkeletonArrows"))
			r(new SkeletonArrowListener());
		if (ConfigHandler.instance().features().getBoolean("ServerListMOTDCustomisation"))
			r(new ServerListingListener());

		/* Update UUIDData file */
		UUIDData.reloadDataFile();
		for (final Player p : Bukkit.getOnlinePlayers())
			UUIDData.setData(p);

		/* Start TPS counter */
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new TPS(), 100L, 1L);

		/* Update tempTimeMap.bin file */
		try {
			if (new File(getDataFolder(), "tempTimeMap.bin").exists())
				JavaUtils.load(new File(getDataFolder(), "tempTimeMap.bin"));
		} catch (final Exception e) {
			e.printStackTrace();
		}

		getLogger().info(titleNoColor + "AllAssets has been enabled successfully");
		Bukkit.broadcast(title + "Plugin reloaded!", "AllAssets.allassets");
		getLogger().info("+---------------------------------+");
	}

	/* Writing getServer().... etc. is too much work :S */
	private void r(final Listener l) {
		getServer().getPluginManager().registerEvents(l, this);
	}

	@Override
	public void onDisable() {
		CommandConsoleLog.players.clear();
		Bukkit.getServer().getScheduler().cancelTasks(this);

		if (!tempTimeMap.isEmpty())
			try {
				JavaUtils.save(tempTimeMap, new File(getDataFolder(), "tempTimeMap.bin"));
			} catch (final Exception e) {
				e.printStackTrace();
			}
		PlayerData.saveAllPlayers();
		getLogger().info(titleNoColor + getDescription().getVersion() + " has been disabled successfully");
	}

	public static AllAssets instance() {
		return JavaPlugin.getPlugin(AllAssets.class);
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
		return framework.handleCommand(sender, label, command, args);
	}

	private void setupVault() {
		final RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null)
			economy = economyProvider.getProvider();
		final RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null)
			permission = permissionProvider.getProvider();
		final RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
		if (chatProvider != null)
			chat = chatProvider.getProvider();
	}
}
