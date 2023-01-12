package me.emsockz.antif5.commands.sub;

import me.emsockz.antif5.Main;
import me.emsockz.antif5.commands.PluginSubCommand;
import me.emsockz.antif5.file.config.MessagesCFG;

public class ReloadCMD extends PluginSubCommand {
    public ReloadCMD() {
        this.setPlayerCommand(false);
    }

	@Override
	public boolean execute() {
		if (!checkPermission("roseantif5.commands.reload")) return true; 
		Main.reloadPlugin();
		sendMessage(MessagesCFG.RELOAD_PLUGIN);
		return true;
	}
}
