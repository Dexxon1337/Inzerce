package me.capanddex;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Inzerce extends JavaPlugin{
	@Override
	public void onEnable() {
		
	}
	@Override
	public void onDisable() {
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("inzerat") && sender instanceof Player) {
			Player player = (Player) sender;

			player.sendMessage(ChatColor.RED + "Ahoj, "+player.getName() + "!");
//toto je zmena
	

			return true;
		}
		return false;
	}
}
