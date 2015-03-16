package me.capanddex;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ConfigHandler {
/*	private Player inzerent;
	private String inzerat;
	private FileConfiguration soubor;
	*/
	public static void ulozInzerat(Player inzerent, String itemName, int count, FileConfiguration soubor) {
		soubor.set("inzeraty."+inzerent.getName()+".item", itemName);
		soubor.set("inzeraty."+inzerent.getName()+".itemcount", count);
		
	}
/*	ConfigHandler(Player player, String text, FileConfiguration config) {
		inzerent = player;
		inzerat = text;
		soubor = config;
	}
	*/

}
