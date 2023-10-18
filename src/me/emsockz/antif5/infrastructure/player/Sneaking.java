package me.emsockz.antif5.infrastructure.player;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.emsockz.antif5.Main;
import me.emsockz.antif5.events.PlayerToggleSneakEvent;
import static me.emsockz.antif5.Main.schedulerRun;

public class Sneaking {
	
	public static HashMap<String, Boolean> players = new HashMap<>();
		
	public static void listening() {
		Main.players.forEach((player) -> {
			if (!player.isSwimming() && player.isSneaking()) {
				if (players.get(player.getName()) == true) return;
				else {
					players.put(player.getName(), true);
					schedulerRun(() -> {
						Bukkit.getPluginManager().callEvent(new PlayerToggleSneakEvent(player, true));
					});
				}
			}
			else {
				if (players.get(player.getName()) == false) return;
				else {
					players.put(player.getName(), false);
					schedulerRun(() -> {
						Bukkit.getPluginManager().callEvent(new PlayerToggleSneakEvent(player, false));
					});
				}
			}
		});
	}
	
	public static boolean isSneaking(Player p) {
		return players.get(p.getName());
	}
}
