package me.emsockz.antif5.commands.sub;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.emsockz.antif5.Main;
import me.emsockz.antif5.AntiF5;
import me.emsockz.antif5.commands.SubCommandModel;
import me.emsockz.antif5.file.config.MessagesCFG;
import me.emsockz.antif5.util.TextUtil;

public class ToggleCMD extends SubCommandModel {
	
    public ToggleCMD() {
        this.setPlayerCommand(false);
    }
    
	@Override
	public boolean execute() {
		if (!checkPermission("roseantif5.commands.toggle", true)) return true;
		Main.debug(String.valueOf(args.length));
		
		if (args.length == 1) {
			if (player == null) {
				sendMessage(MessagesCFG.NOT_FOR_CONSOLE);
				return true;
			}
			Main.debug("1");
			@SuppressWarnings("serial") HashMap<String, String> r = new HashMap<String, String>() {{put("{player}", player.getName());}};
			aud.sendMessage(AntiF5.toggle(player) ? TextUtil.replace(MessagesCFG.ANTIF5_PLAYER_ENABLED.getString(), r) : TextUtil.replace(MessagesCFG.ANTIF5_PLAYER_DISABLED.getString(), r));
		}
		
		else if (args.length == 2) {
			if (!checkPermission("roseantif5.commands.toggle.other", true)) return true;
			
			if (Bukkit.getPlayer(args[1]) == null) {
				sendMessage(MessagesCFG.PLAYER_NOT_FOUND);
				return true;
			}
			Main.debug("2");
			Player p = Bukkit.getPlayer(args[1]);
			@SuppressWarnings("serial") HashMap<String, String> r = new HashMap<String, String>() {{put("{player}", p.getName());}};
			aud.sendMessage(AntiF5.toggle(p) ? TextUtil.replace(MessagesCFG.ANTIF5_PLAYER_ENABLED.getString(), r) : TextUtil.replace(MessagesCFG.ANTIF5_PLAYER_DISABLED.getString(), r));
		}
		
		else
			sendHelp();
		
		return true;
	}
}
