package me.emsockz.antif5.infrastructure.builder;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

public class ArmorStandBuilder {
	
	public static ArmorStand build(Location loc, ItemStack head) {
		Location locSpawn = loc.clone();
		double y = loc.getY();
		locSpawn.setY(1);
		locSpawn.setYaw(0);
		locSpawn.setPitch(0);
		ArmorStand stand = (ArmorStand) loc.getWorld().spawn(loc, ArmorStand.class);
		
		
		stand.setVisible(false);
		stand.setInvisible(true);
		stand.setInvulnerable(true);
		stand.setMarker(true);
		stand.setGravity(false);
		stand.setAI(false);
		stand.setBasePlate(false);
		stand.setCanPickupItems(false);
		stand.getEquipment().setHelmet(head);
		
		Location locTP = locSpawn.clone();
		locTP.setY(y);
		stand.teleport(locTP);
	
		return stand;
	}
	
}
