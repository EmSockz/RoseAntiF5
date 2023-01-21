package me.emsockz.antif5.infrastructure.builder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {

	public static ItemStack build(String material, int CMD) {
		ItemStack i = new ItemStack(Material.valueOf(material), 1);
		ItemMeta m = i.getItemMeta();
		m.setCustomModelData(CMD);
		i.setItemMeta(m);
		return i;
	}
	
}
