package me.emsockz.antif5;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import me.emsockz.antif5.file.MessagesFile;
import me.emsockz.antif5.file.config.MessagesCFG;
import me.emsockz.antif5.file.config.PluginCFG;
import me.emsockz.antif5.listeners.PlayerJoinListener;
import me.emsockz.antif5.listeners.PlayerQuitListener;
import me.emsockz.antif5.listeners.PlayerTeleportListener;
import me.emsockz.antif5.commands.SubCommandManager;
import me.emsockz.antif5.commands.TabCommandManager;

public class Main extends JavaPlugin {
	
	private static Main instance = null;
	
    @Override
    public void onEnable() {
    	instance = this;
    	
    	saveDefaultConfig();
    	new MessagesFile();
    	
    	PluginCFG.reload();
    	MessagesCFG.refreshAll();
    	
    	Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), instance);
    	Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), instance);
    	Bukkit.getPluginManager().registerEvents(new PlayerTeleportListener(), instance);
    	
        PluginCommand pluginCommand = instance.getCommand("antif5");
    		pluginCommand.setExecutor(new SubCommandManager());
    		pluginCommand.setTabCompleter(new TabCommandManager());
    }
    
    @Override
    public void onDisable() {
    	AntiF5.stands.forEach((name, stand) -> {
    		stand.setHealth(0);
    		stand.remove();
    	});
    }
	
    
    public static void reloadPlugin() {
    	Bukkit.getOnlinePlayers().forEach((player) -> {
    		AntiF5.remove(player);
    	});
    	
    	instance.reloadConfig();
    	MessagesFile.reload();
    	PluginCFG.reload();
    	MessagesCFG.refreshAll();
    	
    	Bukkit.getOnlinePlayers().forEach((player) -> {
    		AntiF5.add(player);
    	});
    }
    
    
	public static Main getInstance() {
		return instance;
	}
}
