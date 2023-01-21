package me.emsockz.antif5;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.emsockz.antif5.file.MessagesFile;
import me.emsockz.antif5.file.config.MessagesCFG;
import me.emsockz.antif5.file.config.PluginCFG;
import me.emsockz.antif5.listeners.PlayerDeathListener;
import me.emsockz.antif5.listeners.PlayerJoinListener;
import me.emsockz.antif5.listeners.PlayerQuitListener;
import me.emsockz.antif5.listeners.PlayerRespawnListener;
import me.emsockz.antif5.listeners.PlayerTeleportListener;
import me.emsockz.antif5.infrastructure.player.Crawling;
import me.emsockz.antif5.infrastructure.player.Sneaking;
import me.emsockz.antif5.infrastructure.player.Swimming;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import me.emsockz.antif5.commands.SubCommandManager;
import me.emsockz.antif5.commands.TabCommandManager;

public class Main extends JavaPlugin {
	
	public static List<Player> players = new ArrayList<>();
	
	private static Main instance = null;
	private static MessagesFile messages = null;
	private static BukkitAudiences adventure;
	
	private static Logger log = Logger.getLogger("Minecraft");
	
    @Override
    public void onEnable() {
    	instance = this;
		adventure = BukkitAudiences.create(instance);
		loadMessagesFiles();
		
    	saveDefaultConfig();
		PluginCFG.LANG = getConfig().getString("lang");
		messages = new MessagesFile();
    	
    	PluginCFG.reload();
    	MessagesCFG.refreshAll();
    	
    	Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), instance);
    	Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), instance);
    	Bukkit.getPluginManager().registerEvents(new PlayerTeleportListener(), instance);
    	Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), instance);
    	Bukkit.getPluginManager().registerEvents(new PlayerRespawnListener(), instance);
    	
        PluginCommand pluginCommand = instance.getCommand("roseantif5");
    		pluginCommand.setExecutor(new SubCommandManager());
    		pluginCommand.setTabCompleter(new TabCommandManager());
    		
        Bukkit.getOnlinePlayers().forEach((player) -> {
        	AntiF5.add(player);
        	players.add(player);
        });
        
        
        
        Bukkit.getScheduler().runTaskTimerAsynchronously(instance, () -> {
        	Crawling.listening();
        	Sneaking.listening();
        	Swimming.listening();
        }, 1, 3);
        
		if (!new File(Main.getInstance().getDataFolder(), "resourcepack.zip").exists()) 
			instance.saveResource("resourcepack.zip", false);
    }
    
    @Override
    public void onDisable() {
    	AntiF5.stands.forEach((name, stand) -> {
    		stand.setHealth(0);
    		stand.remove();
    	});
    	
		if (adventure != null) {
			adventure.close();
			adventure = null;
		}
    }
	
	public void loadMessagesFiles() {
		List<String> langs = List.of("en", "ru");;
		
		langs.forEach((lang) -> {
			if (!new File(Main.getInstance().getDataFolder(), "lang/messages_"+lang+".yml").exists()) 
				instance.saveResource("lang/messages_"+lang+".yml", false);
		});
	}
	
	
	
	public static void debug(String text) {
		log.severe("DEBUG: "+text);
	}
    public static void logInfo(String text){
    	log.info(text);
    }
    public static void logWarning(String text){
    	log.warning(text);
    }
    public static void logSevere(String text){
    	log.severe(text);
    }
    
    public static void schedulerRun(Runnable task) {
    	Bukkit.getScheduler().runTask(instance, task);
    }
    
    public static void schedulerRun5(Runnable task) {
        Bukkit.getScheduler().runTaskLater(instance, task, 5);
    }
    
    public static MessagesFile getMessages() {
    	return messages;
    }
    
	public static Main getInstance() {
		return instance;
	}
    
	public static FileConfiguration getMCFG() {
		return messages.getConfig();
	}
    
	public static BukkitAudiences getAdventure() {
		if (adventure == null) {
			throw new IllegalStateException("Tried to acces Adventure when the plugin was disables!");
		}
		
		return adventure;
	}
	
    public void reloadPlugin() {
    	Bukkit.getOnlinePlayers().forEach((player) -> {
    		AntiF5.remove(player);
    	});
    	
    	instance.reloadConfig();
    	PluginCFG.LANG = instance.getConfig().getString("lang");
    	messages.reload();
    	PluginCFG.reload();
    	MessagesCFG.refreshAll();
    	
    	Bukkit.getOnlinePlayers().forEach((player) -> {
    		AntiF5.add(player);
    	});
    }
}
