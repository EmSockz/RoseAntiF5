package me.emsockz.antif5.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.emsockz.antif5.AntiF5;
import me.emsockz.antif5.file.config.PluginCFG;

public class PlayerToggleSneakEvent extends Event {

	private static final HandlerList HANDLERS = new HandlerList();
	private final Player player;
	private final String playerName;
	private final boolean isSneaking;
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
		 
	}
	
	public PlayerToggleSneakEvent(Player player, boolean isSneaking) {
		this.player = player;
		this.playerName = player.getName();
		this.isSneaking = isSneaking;
		if (PluginCFG.BLACKLIST_PLAYERS.contains(player.getName())) return;
		
		if (isSneaking) {
			AntiF5.stands.get(player.getName()).getEquipment().setHelmet(PluginCFG.ANTIF5_ITEM_SNEAK);
		}
		
		else {
			AntiF5.stands.get(player.getName()).getEquipment().setHelmet(PluginCFG.ANTIF5_ITEM_STAND);
		}
	}
	
	
	public Player getPlayer() {
		return this.player;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public Boolean isSneaking() {
		return this.isSneaking;
	}
	
	
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
}
