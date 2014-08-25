package io.github.Skepter.Listeners;

import io.github.Skepter.AllAssets;
import io.github.Skepter.Utils.ErrorUtils;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ReloadCommandListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(final PlayerCommandPreprocessEvent event) {
		final String cmd = event.getMessage().split(" ")[0].replace("/", "").toLowerCase();
		switch (cmd) {
		case "reload":
			if (event.getPlayer().hasPermission("AllAssets.reload")) {
				event.setCancelled(true);
				if(event.getMessage().split(" ").length == 2) {
					if(event.getMessage().split(" ")[1].equalsIgnoreCase("server"))
						Bukkit.reload();
					else {
						if(event.getMessage().split(" ")[1].equalsIgnoreCase("AllAssets")) {
							ErrorUtils.error(event.getPlayer(), "You cannot reload AllAssets. Use /allassets reload instead");
							return;
						}
						try {
							Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin(event.getMessage().split(" ")[1]));
							Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin(event.getMessage().split(" ")[1]));
							event.getPlayer().sendMessage(AllAssets.instance().title + event.getMessage().split(" ")[1] + " successfully reloaded");
						} catch (final Exception e) {
							ErrorUtils.pluginNotFound(event.getPlayer(), event.getMessage().split(" ")[1]);
						}
					}
				} else
					ErrorUtils.error(event.getPlayer(), "Please specify a plugin to reload, or use /reload server to reload the server");
			}
		default:
			return;
		}
	}
}