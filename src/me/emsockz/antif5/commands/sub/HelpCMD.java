package me.emsockz.antif5.commands.sub;

import me.emsockz.antif5.commands.PluginSubCommand;
import me.emsockz.antif5.file.config.MessagesCFG;

public class HelpCMD extends PluginSubCommand {
    public HelpCMD() {
        this.setPlayerCommand(false);
    }
    
	@Override
	public boolean execute() {
		if (checkPermission("roseantif5.commands.help"))  sendMessage(MessagesCFG.HELP_COMMAND);	
		return true;
	}
}
