package me.emsockz.antif5.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import me.emsockz.antif5.AntiF5;
import me.emsockz.antif5.events.PlayerToggleSwimEvent;

public class PlayerTeleportListener implements Listener {

	@EventHandler
	public static void onTeleport(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		if (PlayerToggleSwimEvent.tasks.containsKey(p.getName())) {
			PlayerToggleSwimEvent.tasks.get(p.getName()).cancel();
			PlayerToggleSwimEvent.tasks.remove(p.getName());
		}
		AntiF5.update(p);
	}
}
