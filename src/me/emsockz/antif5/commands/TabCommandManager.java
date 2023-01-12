package me.emsockz.antif5.commands;

import java.util.ArrayList;
import java.util.List; 

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabCommandManager implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("roseantif5.help")) return listSubCommand;
		
		return null;
	}
	
	
	private static ArrayList<String> listSubCommand = new ArrayList<>(List.of("help","reload"));
}
