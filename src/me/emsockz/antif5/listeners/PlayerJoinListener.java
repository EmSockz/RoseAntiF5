package me.emsockz.antif5.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.emsockz.antif5.AntiF5;
import me.emsockz.antif5.file.config.PluginCFG;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public static void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String name = p.getName();
		if (PluginCFG.BLACKLIST_PLAYERS.contains(name)) return;
		
		AntiF5.add(p);
	}
}
