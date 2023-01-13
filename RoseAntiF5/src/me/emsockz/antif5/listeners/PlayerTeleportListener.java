package me.emsockz.antif5.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import me.emsockz.antif5.AntiF5;

public class PlayerTeleportListener implements Listener {

	@EventHandler
	public static void onTeleport(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		String name = p.getName();
		if (!AntiF5.stands.containsKey(name)) return;
		
		AntiF5.update(p);
	}


}
