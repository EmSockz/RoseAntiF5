package me.emsockz.antif5.events;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitTask;

import me.emsockz.antif5.AntiF5;
import me.emsockz.antif5.Main;
import me.emsockz.antif5.file.config.PluginCFG;

public class PlayerToggleSwimEvent extends Event {

	public static HashMap<String, BukkitTask> tasks = new HashMap<>();
	
	private static final HandlerList HANDLERS = new HandlerList();
	private final Player player;
	private final String playerName;
	private final boolean isSwimming;
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
		
	}
	
	public PlayerToggleSwimEvent(Player player, boolean isCrawling) {
		this.player = player;
		this.playerName = player.getName();
		this.isSwimming = isCrawling;
		if (PluginCFG.BLACKLIST_PLAYERS.contains(playerName)) return;
		
		if (isSwimming) {
			BukkitTask t = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
				if (player.isSwimming()) {
					AntiF5.stands.get(playerName).getEquipment().setHelmet(PluginCFG.ANTIF5_ITEM_SWIM);
					AntiF5.stands.get(playerName).teleport(player.getEyeLocation().clone().add(0,0.1,0.0));
				} else {
					AntiF5.stands.get(playerName).getEquipment().setHelmet(PluginCFG.ANTIF5_ITEM_STAND);
					AntiF5.stands.get(playerName).teleport(player.getEyeLocation().clone().add(0,-0.20,0.0));
				}
			}, 0, 1);
			tasks.put(playerName, t);
			AntiF5.stands.get(playerName).getEquipment().setHelmet(PluginCFG.ANTIF5_ITEM_SWIM);
		}
		
		else {
			tasks.get(playerName).cancel();
			tasks.remove(playerName);
			player.addPassenger(AntiF5.stands.get(playerName));
			AntiF5.stands.get(playerName).getEquipment().setHelmet(PluginCFG.ANTIF5_ITEM_STAND);
		}
	}
	
	
	public Player getPlayer() {
		return this.player;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public Boolean isCrawling() {
		return this.isSwimming;
	}
	
	
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
}
