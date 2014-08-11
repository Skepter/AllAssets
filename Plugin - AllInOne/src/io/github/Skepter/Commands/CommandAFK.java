package io.github.Skepter.Commands;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Commands.CommandFramework.CommandArgs;
import io.github.Skepter.Commands.CommandFramework.CommandHandler;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Users.User;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class CommandAFK implements Listener {

	public CommandAFK(final CommandFramework framework) {
		framework.registerCommands(this);
	}

	@CommandHandler(name = "afk", permission = "AllInOne.afk", description = "Sets your status as away from keyboard", usage = "Use <command>")
	public void onCommand(final CommandArgs args) {
		final Player player = args.getPlayer();
		final User user = new User(player);
		if (!user.isAFK()) {
			Bukkit.broadcastMessage(AllInOne.instance().ttlc + player.getName() + " is now AFK");
			user.setAFK(true);
		} else {
			Bukkit.broadcastMessage(AllInOne.instance().ttlc + player.getName() + " is no longer AFK");
			user.setAFK(false);
		}
		return;
	}

	@EventHandler
	public void onHurt(final EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			final Player player = (Player) event.getEntity();
			final User user = new User(player);
			if (ConfigHandler.getConfig().getBoolean("afkProtect") && user.isAFK()) {
				event.setDamage(0.0D);
				event.setCancelled(false);
			}
		}
	}

	@EventHandler
	public void onMove(final PlayerMoveEvent event) {
		final Player player = event.getPlayer();
		final User user = new User(player);
		if (user.isAFK()) {
			Bukkit.broadcastMessage(AllInOne.instance().ttlc + player.getName() + " is no longer AFK");
			user.setAFK(false);
		}
	}
}
