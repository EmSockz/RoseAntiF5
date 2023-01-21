package me.emsockz.antif5.infrastructure.player;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.emsockz.antif5.events.PlayerToggleSwimEvent;

import static me.emsockz.antif5.Main.schedulerRun;

public class Swimming {
	
	public static HashMap<String, Boolean> players = new HashMap<>();
		
	public static void listening() {
		Bukkit.getOnlinePlayers().forEach((player) -> {
			if (player.getLocation().getBlock().getType() == Material.WATER) {
				if (players.get(player.getName()) == true) return;
				else {
					players.put(player.getName(), true);
					schedulerRun(() -> {
						Bukkit.getPluginManager().callEvent(new PlayerToggleSwimEvent(player, true));
					});
				}
			}
			else {
				if (players.get(player.getName()) == false) return;
				else {
					players.put(player.getName(), false);
					schedulerRun(() -> {
						Bukkit.getPluginManager().callEvent(new PlayerToggleSwimEvent(player, false));
					});
				}
			}
		});
	}
	
	public static boolean isSwimming(Player p) {
		return players.get(p.getName());
	}
}
