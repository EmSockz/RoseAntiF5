package me.emsockz.antif5.commands.sub;

import me.emsockz.antif5.commands.SubCommandModel;
import me.emsockz.antif5.file.config.MessagesCFG;

public class HelpCMD extends SubCommandModel {
	
    public HelpCMD() {
        this.setPlayerCommand(false);
    }
    
	@Override
	public boolean execute() {
		if (checkPermission("roseantif5.commands.help.admin", false))
			sendMessage(MessagesCFG.HELP_COMMAND_ADMIN);
		else if (checkPermission("roseantif5.commands.help", false)) 
			sendMessage(MessagesCFG.HELP_COMMAND);	
		else
			sendMessage(MessagesCFG.NO_PERMISSIONS);
		
		return true;
	}
}
