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
		saveDefaultConfig();
		saveConfig();
		getLogger().info("Plugin spusten.");

	}

	@Override
	public void onDisable() {
		saveConfig();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("inzerat")
				&& sender instanceof Player) {
			Player player = (Player) sender;

			if (args[0].equalsIgnoreCase("vytvorit")) {
				String text = args[1];
				// zatim to necham vsechno v onCommand
				config.set("inzeraty." + player.getName() + ".inzerat", text);
				saveConfig();
			}
			else if(args[0].equalsIgnoreCase("smazat")){
				config.set("inzeraty." + player.getName(), null);
				saveConfig();
			}

			return true;
		}
		return false;
	}
}
