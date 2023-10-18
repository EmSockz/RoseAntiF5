package me.emsockz.antif5.commands;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.emsockz.antif5.file.config.MessagesCFG;
import me.emsockz.antif5.util.TextUtil;
import me.emsockz.antif5.Main;

public abstract class SubCommandModel {

    protected CommandSender sender;
    protected Player player;
    protected Command command;
    protected boolean isPlayer;
    protected String[] args;
    protected Audience aud;

    private boolean isPlayerCommand;
    

    public boolean onExecute(CommandSender sender, Command command, String s, String[] args) {
        this.sender = sender;
        this.isPlayer = sender instanceof Player;
        this.player = isPlayer ? (Player) sender : null;
        this.args = args;
        this.command = command;
        this.aud = isPlayer ? Main.getAdventure().player(player) : Main.getAdventure().console();
        

        if (isPlayerCommand && !isPlayer) {
            sendMessage(MessagesCFG.NOT_FOR_CONSOLE);
            return true;
        }
        
        return this.execute();
    }

    public abstract boolean execute();
    
    public void sendHelp() {
		if (checkPermission("roseantif5.commands.help.admin", false))
			sendMessage(MessagesCFG.HELP_COMMAND_ADMIN);
		else if (checkPermission("roseantif5.commands.help", false)) 
			sendMessage(MessagesCFG.HELP_COMMAND);	
		else 
			sendMessage(MessagesCFG.NO_PERMISSIONS);
    }

    public boolean checkPermission(String permission, boolean showMSG) {
    	if (this.sender.hasPermission(permission)) {
    		return true;
    	}
    	
    	if (showMSG)
    		sendMessage(MessagesCFG.NO_PERMISSIONS);
    	
    	return false;
    }

    public void setPlayerCommand(boolean playerCommand) {
        isPlayerCommand = playerCommand;
    }

    public void sendMessage(MessagesCFG msg){
    	if (msg.getID() == 0) {
    		this.aud.sendMessage(msg.getString());
    	}
    	else {
    		for(Component s : msg.getStringList()) {
    			this.aud.sendMessage(s);
    		}
    	}
    }
    
    public void sendMessage(MessagesCFG msg, HashMap<String, String> replace){
    	if (msg.getID() == 0) {
    		this.aud.sendMessage(TextUtil.replace(msg.getString(), replace));
    	}
    	else {
    		for(Component s : msg.getStringList()) {
    			this.aud.sendMessage(TextUtil.replace(s, replace));
    		}
    	}
    }
}
