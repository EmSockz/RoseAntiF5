package me.emsockz.antif5.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.emsockz.antif5.commands.sub.DisableCMD;
import me.emsockz.antif5.commands.sub.EnableCMD;
import me.emsockz.antif5.commands.sub.HelpCMD;
import me.emsockz.antif5.commands.sub.ReloadCMD;
import me.emsockz.antif5.commands.sub.ToggleCMD;
import me.emsockz.antif5.file.config.MessagesCFG;
import me.emsockz.antif5.Main;

public class SubCommandManager implements CommandExecutor {

	public static HashMap<String, SubCommandModel> subCommands = new HashMap<>();
	
    public SubCommandManager() {
    	subCommands.put("reload", new ReloadCMD());
    	subCommands.put("help",   new HelpCMD());
    	subCommands.put("enable",   new EnableCMD());
    	subCommands.put("disable",   new DisableCMD());
    	subCommands.put("toggle",   new ToggleCMD());
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
        	subCommands.get("help").onExecute(sender, command, label, args);
            return true;
        }
        String subcommand = args[0].toLowerCase();
        if (subCommands.get(subcommand) == null) {
        	((sender instanceof Player) ? Main.getAdventure().player((Player)sender) : Main.getAdventure().console())
            .sendMessage(MessagesCFG.COMMAND_NOT_FOUND.getString());
            return true;
        }

        subCommands.get(subcommand).onExecute(sender, command, label, args);
        return true;
	}
}
