package me.emsockz.antif5.commands;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.emsockz.antif5.file.config.MessagesCFG;

public abstract class PluginSubCommand {

    protected CommandSender sender;
    protected Player player;
    protected Command command;
    protected boolean isPlayer;
    protected String[] args;

    private boolean isPlayerCommand;
    

    public boolean onExecute(CommandSender sender, Command command, String s, String[] args) {
        this.sender = sender;
        this.isPlayer = sender instanceof Player;
        this.player = isPlayer ? (Player) sender : null;
        this.args = args;
        this.command = command;

        if (isPlayerCommand && !isPlayer) {
            sendMessage(MessagesCFG.NOT_FOR_CONSOLE);
            return true;
        }
        
        return this.execute();
    }

    public abstract boolean execute();


    public boolean checkPermission(String permission) {
    	if (this.sender.hasPermission(permission)) {
    		return true;
    	}
    	
    	sendMessage(MessagesCFG.NO_PERMISSIONS);
    	
    	return false;
    }

    public void setPlayerCommand(boolean playerCommand) {
        isPlayerCommand = playerCommand;
    }

    public void sendMessage(MessagesCFG msg){
    	if (msg.getID() == 0) {
    		((Audience) this.sender).sendMessage(msg.getString());
    	}
    	else {
    		for(Component s : msg.getStringList()) {
    			((Audience) this.sender).sendMessage(s);
    		}
    	}
    }
}
