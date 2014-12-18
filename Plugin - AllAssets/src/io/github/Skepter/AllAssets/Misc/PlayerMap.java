package io.github.Skepter.AllAssets.Misc;

import io.github.Skepter.AllAssets.AllAssets;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/* Manages maps and prevents memory leaks */
public class PlayerMap<K, V> implements Listener {

	private Map<UUID, V> map = new HashMap<UUID, V>();
	
	public PlayerMap() {
		AllAssets.instance().r(this);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		if(map.containsKey(event.getPlayer().getUniqueId()))
			remove(event.getPlayer());
	}
	
	public void put(Player player, V value) {
		map.put(player.getUniqueId(), value);
	}
	
	public V get(Player player) {
		return map.get(player.getUniqueId());
	}
	
	public void remove(Player player) {
		map.remove(player.getUniqueId());
	}
	
	public void clear() {
		map.clear();
	}
	
	public boolean containsKey(Object key) {
		if (map.containsKey(key))
			return true;
		else
			return false;
	}
	
	public Set<UUID> keySet() {
		return map.keySet();
	}
	
	public Collection<V> values() {
		return map.values();
	}
	
	public Set<Entry<UUID, V>> entrySet() {
		return map.entrySet();
	}

}