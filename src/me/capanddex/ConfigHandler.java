package me.capanddex;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ConfigHandler {
	private Player inzerent;
	private String inzerat;
	private FileConfiguration soubor;
	public void ulozInzerat() {
		soubor.set("inzeraty.hrac", inzerent.getName());
		soubor.set("inzeraty.hrac.inzerat", inzerat);
	}
	ConfigHandler(Player player, String text, FileConfiguration config) {
		inzerent = player;
		inzerat = text;
		soubor = config;
	}

}
