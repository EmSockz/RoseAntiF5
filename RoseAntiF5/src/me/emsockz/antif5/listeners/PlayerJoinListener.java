package me.emsockz.antif5.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.emsockz.antif5.AntiF5;
import me.emsockz.antif5.Main;
import me.emsockz.antif5.infrastructure.player.Crawling;
import me.emsockz.antif5.infrastructure.player.Sneaking;
import me.emsockz.antif5.infrastructure.player.Swimming;

public class PlayerJoinListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public static void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Crawling.players.put(p.getName(), false);
		Sneaking.players.put(p.getName(), false);
		Swimming.players.put(p.getName(), false);
		
		
		Main.schedulerRun5(() -> {
			AntiF5.add(p);
		});
		
		AntiF5.stands.forEach((k, v) -> {
			p.hideEntity(Main.getInstance(), v);
		});
	}
}
