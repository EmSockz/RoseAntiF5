package me.emsockz.antif5.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.emsockz.antif5.AntiF5;
import me.emsockz.antif5.events.PlayerToggleSwimEvent;

public class PlayerQuitListener implements Listener {

	@EventHandler
	public static void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		String name = p.getName();
		if (!AntiF5.stands.containsKey(name)) return;
		if (PlayerToggleSwimEvent.tasks.containsKey(p.getName())) {
			PlayerToggleSwimEvent.tasks.get(p.getName()).cancel();
			PlayerToggleSwimEvent.tasks.remove(p.getName());
		}
		
		if (!p.isDead()) {
			AntiF5.remove(p);
		}
	}
}
