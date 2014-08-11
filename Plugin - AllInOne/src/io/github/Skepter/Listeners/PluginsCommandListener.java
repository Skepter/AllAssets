package io.github.Skepter.Listeners;

import io.github.Skepter.AllInOne;
import io.github.Skepter.Config.ConfigHandler;
import io.github.Skepter.Utils.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

public class PluginsCommandListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(final PlayerCommandPreprocessEvent event) {
		final String cmd = event.getMessage().split(" ")[0].replace("/", "").toLowerCase();
		switch (cmd) {
		case "pl":
		case "plugins":
		case "plugin":
		case "?":
			if (event.getPlayer().hasPermission("AllInOne.plugins")) {
				event.setCancelled(true);
				event.getPlayer().sendMessage(TextUtils.title("Plugins"));
				event.getPlayer().sendMessage(AllInOne.instance().ttlc + "There are currently " + (Bukkit.getPluginManager().getPlugins().length - 1) + " plugins:");
				final List<String> pluginList = new ArrayList<String>();
				for (final Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
					if(ConfigHandler.features().getBoolean("PluginsShowAuthors")) {
						String authors = "";
						for (final String s : plugin.getDescription().getAuthors()) {
							authors = authors + s + ", ";
						}
						if(authors.length() != 0) {
							authors = authors.substring(0, authors.length() - 2);
						} else {
							authors = "undefined";
						}
						if (plugin.isEnabled()) {
							pluginList.add(ChatColor.GREEN + plugin.getName() + ChatColor.WHITE + ": v" + plugin.getDescription().getVersion() + "\n " + ChatColor.GRAY + "Authors: " + authors);
						} else {
							pluginList.add(ChatColor.RED + plugin.getName() + ChatColor.WHITE + ": v" + plugin.getDescription().getVersion() + "\n " + ChatColor.GRAY + "Authors: " + authors);
						}
					} else {
						if (plugin.isEnabled()) {
							pluginList.add(ChatColor.GREEN + plugin.getName() + ChatColor.WHITE + ": v" + plugin.getDescription().getVersion());
						} else {
							pluginList.add(ChatColor.RED + plugin.getName() + ChatColor.WHITE + ": v" + plugin.getDescription().getVersion());
						}
					}
				}
				Collections.sort(pluginList);
				for (final String s : pluginList) {
					final String[] str = s.split("\n");
					for (final String m : str) {
						event.getPlayer().sendMessage(m);
					}
				}

			}
		default:
			return;
		}
	}
}
