package me.emsockz.antif5.file;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.emsockz.antif5.Main;

public class MessagesFile {
	
	private static File file;
	private static FileConfiguration config;
	
	public MessagesFile() {
		if (!new File(Main.getInstance().getDataFolder(), "messages.yml").exists()) {
			Main.getInstance().saveResource("messages.yml", false);
			
			file = new File(Main.getInstance().getDataFolder(), "messages.yml");
			config = YamlConfiguration.loadConfiguration(file);
		} else {
			file = new File(Main.getInstance().getDataFolder(), "messages.yml");
			config = YamlConfiguration.loadConfiguration(file);
		}
	}
	

	public static void save() {
		file = new File(Main.getInstance().getDataFolder(), "messages.yml");
		
		try {
			config.save(file);
		} catch (IOException e) {
			throw new RuntimeException("Не удалось сохранить файл messages.yml", e);
		}
		
		config = YamlConfiguration.loadConfiguration(file);
	}

	public static void reload() {
		file = new File(Main.getInstance().getDataFolder(), "messages.yml");
		config = YamlConfiguration.loadConfiguration(file);
	}

	public static FileConfiguration getConfig() {
		return config;
	}
}
