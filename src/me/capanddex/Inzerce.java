package me.capanddex;

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
		
	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("inzerat")	&& sender instanceof Player) {
			Player player = (Player) sender;
			
			if (args[0].equalsIgnoreCase("vytvorit")) {
				String text = args[1];
	//			ConfigHandler configHandle = new ConfigHandler(player,text,config);
				ConfigHandler.ulozInzerat(player, text, config);
			}
 
			return true;
		}
		return false;
	}
}
