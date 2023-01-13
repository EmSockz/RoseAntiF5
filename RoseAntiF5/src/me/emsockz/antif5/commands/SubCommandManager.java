package me.emsockz.antif5.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.emsockz.antif5.commands.sub.HelpCMD;
import me.emsockz.antif5.commands.sub.ReloadCMD;
import me.emsockz.antif5.file.config.MessagesCFG;
import net.kyori.adventure.audience.Audience;

public class SubCommandManager implements CommandExecutor {

	public static HashMap<String, PluginSubCommand> subCommands = new HashMap<>();
	
    public SubCommandManager() {
    	subCommands.put("reload", new ReloadCMD());
    	subCommands.put("help",   new HelpCMD());
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
        	subCommands.get("help").onExecute(sender, command, label, args);
            return true;
        }
        String subcommand = args[0].toLowerCase();
        if (subCommands.get(subcommand) == null) {
        	((Audience) sender).sendMessage(MessagesCFG.COMMAND_NOT_FOUND.getString());
            return true;
        }

        subCommands.get(subcommand).onExecute(sender, command, label, args);
        return true;
	}
}
