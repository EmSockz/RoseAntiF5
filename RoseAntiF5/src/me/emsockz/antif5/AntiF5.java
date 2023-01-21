package me.emsockz.antif5;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import me.emsockz.antif5.events.PlayerToggleSwimEvent;
import me.emsockz.antif5.file.config.PluginCFG;
import me.emsockz.antif5.infrastructure.builder.ArmorStandBuilder;

public class AntiF5 {

	public static HashMap<String, ArmorStand> stands = new HashMap<>();
	
	@SuppressWarnings("deprecation")
	public static void add(Player p) {
		String name = p.getName();
		if (PluginCFG.BLACKLIST_PLAYERS.contains(name)) return;
		Main.players.add(p);
		ArmorStand stand = null;
				
		if (p.isSwimming())
			stand = ArmorStandBuilder.build(p.getLocation(), PluginCFG.ANTIF5_ITEM_SWIM); 
		else if (p.isSneaking())
			stand = ArmorStandBuilder.build(p.getLocation(), PluginCFG.ANTIF5_ITEM_SNEAK); 
		else 
			stand = ArmorStandBuilder.build(p.getLocation(), PluginCFG.ANTIF5_ITEM_STAND); 
		p.addPassenger(stand);
		
		//hide antif5
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (!(player.getName().equals(p.getName()))) {
				player.hideEntity(Main.getInstance(), stand);
			}
		}	
		
		stands.put(name, stand);
	}
	
	public static void remove(Player p) {
		String name = p.getName();
		if (!stands.containsKey(name)) return;
		Main.players.remove(p);
		stands.get(name).setHealth(0);
		stands.get(name).remove();
		stands.remove(name);
	}
	
	public static void update(Player p) {
		Main.players.remove(p);	
		remove(p);
		add(p);
		Main.players.add(p);	
	}
	
	public static boolean toggle(Player p) {
		FileConfiguration cfg = Main.getInstance().getConfig();
		boolean toggle = !(PluginCFG.BLACKLIST_PLAYERS.contains(p.getName()));
		
		//если у игрока включен АнтиФ5
		if (toggle) {
			if (PlayerToggleSwimEvent.tasks.containsKey(p.getName())) {
				PlayerToggleSwimEvent.tasks.get(p.getName()).cancel();
				PlayerToggleSwimEvent.tasks.remove(p.getName());
			}
			List<String> l = cfg.getStringList("blacklistPlayers");
			l.add(p.getName());
			cfg.set("blacklistPlayers", l);
			PluginCFG.BLACKLIST_PLAYERS = l;
			Main.getInstance().saveConfig();
			Main.getInstance().reloadConfig();
			remove(p);
		}
		
		//если у игрока выключен АнтиФ5
		else {
			List<String> l = cfg.getStringList("blacklistPlayers");
			l.remove(p.getName());
			cfg.set("blacklistPlayers", l);
			PluginCFG.BLACKLIST_PLAYERS = l;
			Main.getInstance().saveConfig();
			Main.getInstance().reloadConfig();
			add(p);
		}
		
		return !toggle;
	}
}
