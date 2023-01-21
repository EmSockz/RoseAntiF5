package me.emsockz.antif5.commands.sub;

import org.bukkit.Bukkit;

import me.emsockz.antif5.AntiF5;
import me.emsockz.antif5.Main;
import me.emsockz.antif5.commands.SubCommandModel;
import me.emsockz.antif5.file.config.MessagesCFG;

public class EnableCMD extends SubCommandModel {
	
    public EnableCMD() {
        this.setPlayerCommand(false);
    }

	@Override
	public boolean execute() {
		if (!checkPermission("roseantif5.commands.enable", true)) return true; 
		Main.getInstance().getConfig().set("enableAntiF5", true);
		Main.getInstance().saveConfig();
		Main.getInstance().reloadConfig();
		
		sendMessage(MessagesCFG.ANTIF5_ENABLED);
        Bukkit.getOnlinePlayers().forEach((player) -> {
        	AntiF5.add(player);
        });
        
		return true;
	}
}