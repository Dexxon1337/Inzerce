package me.capanddex;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Inzerce extends JavaPlugin {
	FileConfiguration config;
	
	@Override
	public void onEnable() {
		config = this.getConfig();
		getLogger().info("Plugin spuštìn.");
		saveConfig();
	}

	@Override
	public void onDisable() {
		saveConfig();
		getLogger().info("Plugin vypnut.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("inzerat")	&& sender instanceof Player) {
			Player player = (Player) sender;
			
			if (args[0].equalsIgnoreCase("vytvorit")) {
				String text = args[1];
	//			ConfigHandler configHandle = new ConfigHandler(player,text,config);
				ConfigHandler.ulozInzerat(player, text, config);
			}
			
			if (args[0].equalsIgnoreCase("reload")) {	//reloadcmd
				if (player.hasPermission("inzerat.admin")){
				saveConfig();
				reloadConfig();
				}
			}
			
			if (args[0].equalsIgnoreCase("disable")) {	//plugin disable
				if (player.hasPermission("inzerat.admin")){
				this.setEnabled(false);
				}
			}
			
			if (args[0].equalsIgnoreCase("enable")) {	//plugin enable
				if (player.hasPermission("inzerat.admin")){
				this.setEnabled(true);
				}
			}
			
			if (args[0].equalsIgnoreCase("help")) {	//helpcmd
				if (player.hasPermission("inzerat.admin")){
					player.sendMessage("§3~~~~~~~~~~~~~~§6Plugin §lInzerce §6Help§3~~~~~~~~~~~~~~");
					player.sendMessage("§a/inzerat help §b- zobrazí tuto nápovedu.");
					player.sendMessage("§a/inzerat vytvorit [Item na prodej] [Pocet] [Cena] §b- vytvorí inzerát podle zadaných parametru.");
					player.sendMessage("§a/inzerat reload §b- reloadne plugin.");
					player.sendMessage("§a/inzerat disable §b- vypne plugin.");
					player.sendMessage("§a/inzerat enable §b- zapne plugin.");
					player.sendMessage("§3~~~~~~~~~~~~~~§6Plugin §lInzerce §6Help§3~~~~~~~~~~~~~~");
				}
				else {
				player.sendMessage("§3~~~~~~~~~~~~~~§6Plugin §lInzerce §6Help§3~~~~~~~~~~~~~~");
				player.sendMessage("§a/inzerat help §b- zobrazí tuto nápovedu.");
				player.sendMessage("§a/inzerat vytvorit [Item na prodej] [Pocet] [Cena] §b- vytvorí inzerát podle zadaných parametru.");
				player.sendMessage("§3~~~~~~~~~~~~~~§6Plugin §lInzerce §6Help§3~~~~~~~~~~~~~~");
				}
			}

			
			return true;
		}
		return false;
		
		
		
		
		
	}
}
