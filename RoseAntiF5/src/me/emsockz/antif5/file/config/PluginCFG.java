package me.emsockz.antif5.file.config;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import me.emsockz.antif5.Main;
import me.emsockz.antif5.infrastructure.builder.ItemBuilder;

public class PluginCFG {

	public static String LANG;
	
	public static boolean ENABLE_ANTIF5;
	public static List<String> BLACKLIST_PLAYERS;
	
	public static ItemStack ANTIF5_ITEM_STAND;
	public static ItemStack ANTIF5_ITEM_SNEAK;
	public static ItemStack ANTIF5_ITEM_SWIM;
	public static ItemStack ANTIF5_ITEM_CRAWL;
	
	public static void reload() {
		FileConfiguration cfg = Main.getInstance().getConfig();
		LANG = cfg.getString("lang");
		ENABLE_ANTIF5 = cfg.getBoolean("enableAntiF5");
		BLACKLIST_PLAYERS = cfg.getStringList("blacklistPlayers");
		
		ANTIF5_ITEM_STAND = ItemBuilder.build(cfg.getString("antiF5Item.stand.Material"), cfg.getInt("antiF5Item.stand.CustomModelData"));
		ANTIF5_ITEM_SNEAK = ItemBuilder.build(cfg.getString("antiF5Item.sneak.Material"), cfg.getInt("antiF5Item.sneak.CustomModelData"));
		ANTIF5_ITEM_SWIM = ItemBuilder.build(cfg.getString("antiF5Item.swim.Material"), cfg.getInt("antiF5Item.swim.CustomModelData"));
		ANTIF5_ITEM_CRAWL = ItemBuilder.build(cfg.getString("antiF5Item.crawl.Material"), cfg.getInt("antiF5Item.crawl.CustomModelData"));
	}
}
