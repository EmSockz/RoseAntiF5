package me.emsockz.antif5.infrastructure.player;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.emsockz.antif5.events.PlayerToggleCrawlEvent;

import static me.emsockz.antif5.Main.schedulerRun;

public class Crawling {

	public static HashMap<String, Boolean> players = new HashMap<>();
		
	
	public static void listening() {
		Bukkit.getOnlinePlayers().forEach((player) -> {
			if (player.isSwimming() && player.getLocation().getBlock().getType() != Material.WATER) {
				if (players.get(player.getName()) == true) return;
				else {
					players.put(player.getName(), true);
					schedulerRun(() -> {
						Bukkit.getPluginManager().callEvent(new PlayerToggleCrawlEvent(player, true));
					});
				}
			}
			else {
				if (players.get(player.getName()) == false) return;
				else {
					players.put(player.getName(), false);
					schedulerRun(() -> {
						Bukkit.getPluginManager().callEvent(new PlayerToggleCrawlEvent(player, false));
					});
				}
			}
		});
	}
	
	public static boolean isCrawling(Player p) {
		return players.get(p.getName());
	}
}
