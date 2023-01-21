package me.emsockz.antif5.commands.sub;

import java.util.HashMap;

import me.emsockz.antif5.AntiF5;
import me.emsockz.antif5.Main;
import me.emsockz.antif5.commands.SubCommandModel;
import me.emsockz.antif5.file.config.MessagesCFG;

public class DisableCMD extends SubCommandModel {
	
    public DisableCMD() {
        this.setPlayerCommand(false);
    }

	@Override
	public boolean execute() {
		if (!checkPermission("roseantif5.commands.enable", true)) return true; 
		Main.getInstance().getConfig().set("enableAntiF5", false);
		Main.getInstance().saveConfig();
		Main.getInstance().reloadConfig();
		
		sendMessage(MessagesCFG.ANTIF5_DISABLED);
		
    	AntiF5.stands.forEach((name, stand) -> {
    		stand.setHealth(0);
    		stand.remove();
    	});
    	
    	AntiF5.stands = new HashMap<>();
		
		return true;
	}
}
