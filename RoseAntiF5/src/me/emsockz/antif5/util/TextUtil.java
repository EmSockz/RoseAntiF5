package me.emsockz.antif5.util;
 
import java.util.ArrayList;
import java.util.List;

import me.emsockz.antif5.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class TextUtil {

	public static Component parseString(String string) {
		return MiniMessage.miniMessage().deserialize(parseLegacyString(string));
	}

	public static Component getStringMessage(String string) {
		return MiniMessage.miniMessage().deserialize(parseLegacyString(Main.getMCFG().getString(string)));
	}

	public static List<Component> getStringListMessage(String string) {
		List<String> text = Main.getMCFG().getStringList(string);		
		List<String> text2 = new ArrayList<>();
		List<Component> result = new ArrayList<>(); 

		text.forEach((s) -> {
			text2.add(parseLegacyString(s));
		});
		
		text2.forEach((s) -> {
			result.add(MiniMessage.miniMessage().deserialize(s));
		});
		
		return result;
	}

	public static List<Component> parseStringList(List<String> string) {
		List<String> text = new ArrayList<>();
		List<Component> result = new ArrayList<>();
		
		string.forEach((s) -> {
			text.add(parseLegacyString(s));
		});
		
		text.forEach((s) -> {
			result.add(MiniMessage.miniMessage().deserialize(s));
		});
		
		return result;
	}

	public static String parseLegacyString(String string) {
		return string.replace("&", "§").replace("§r", "<reset>").replace("§0", "<black>").replace("§1", "<dark_blue>").replace("§2", "<dark_green>")
	    		.replace("§3", "<dark_aqua>").replace("§4", "<dark_red>").replace("§5", "<dark_purple>").replace("§6", "<gold>").replace("§7", "<gray>")
	    		.replace("§8", "<dark_gray>").replace("§9", "<blue>").replace("§a", "<green>").replace("§c", "<red>").replace("§d", "<light_purple>")
	    		.replace("§b", "<aqua>").replace("§f", "<white>").replace("§l", "<bold>").replace("§n", "<underlined>").replace("§o", "<italic>")
	    		.replace("§m", "<strikethrough>").replace("§k", "<obfuscated>");		
	}
}
