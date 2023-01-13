package me.emsockz.antif5;

import java.util.HashMap;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import me.emsockz.antif5.file.config.PluginCFG;
import me.emsockz.antif5.infrastructure.builder.ArmorStandBuilder;

public class AntiF5 {

	public static HashMap<String, ArmorStand> stands = new HashMap<>();
	
	public static void add(Player p) {
		String name = p.getName();
		if (PluginCFG.BLACKLIST_PLAYERS.contains(name)) return;
		
		ArmorStand stand = ArmorStandBuilder.build(p.getLocation(), PluginCFG.ANTIF5_ITEM); 
		p.addPassenger(stand);
		
		stands.put(name, stand);
	}
	
	public static void remove(Player p) {
		String name = p.getName();
		if (!stands.containsKey(name)) return;
		stands.get(name).setHealth(0);
		stands.get(name).remove();
		stands.remove(name);
	}
	
	public static void update(Player p) {
		remove(p);
		add(p);
	}
	
}
