package me.emsockz.antif5.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.emsockz.antif5.AntiF5;

import static me.emsockz.antif5.Main.schedulerRun5;

public class PlayerRespawnListener implements Listener {

	@EventHandler
	public static void onRespawn(PlayerRespawnEvent e) {
		schedulerRun5(() -> {
			AntiF5.add(e.getPlayer());
		});
	}
}
