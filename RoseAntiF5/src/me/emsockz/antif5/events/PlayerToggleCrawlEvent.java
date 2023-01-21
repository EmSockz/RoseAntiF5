package me.emsockz.antif5.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.emsockz.antif5.AntiF5;
import me.emsockz.antif5.Main;
import me.emsockz.antif5.file.config.PluginCFG;

public class PlayerToggleCrawlEvent extends Event {

	private static final HandlerList HANDLERS = new HandlerList();
	private final Player player;
	private final String playerName;
	private final boolean isCrawling;
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
		
	}
	
	public PlayerToggleCrawlEvent(Player player, boolean isCrawling) {
		this.player = player;
		this.playerName = player.getName();
		this.isCrawling = isCrawling;
		Main.debug("PlayerToggleCrawlEvent");
		Main.debug(String.valueOf(isCrawling));
		if (isCrawling) {
			AntiF5.stands.get(playerName).getEquipment().setHelmet(PluginCFG.ANTIF5_ITEM_CRAWL);
		}
		
		else {
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
		return this.isCrawling;
	}
	
	
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
}
