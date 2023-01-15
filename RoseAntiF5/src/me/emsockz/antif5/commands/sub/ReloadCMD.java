package me.emsockz.antif5.commands.sub;

import me.emsockz.antif5.Main;
import me.emsockz.antif5.commands.SubCommandModel;
import me.emsockz.antif5.file.config.MessagesCFG;

public class ReloadCMD extends SubCommandModel {
	
    public ReloadCMD() {
        this.setPlayerCommand(false);
    }

	@Override
	public boolean execute() {
		if (!checkPermission("roseantif5.commands.reload", true)) return true; 
		Main.getInstance().reloadPlugin();
		sendMessage(MessagesCFG.RELOAD_PLUGIN);
		return true;
	}
}
