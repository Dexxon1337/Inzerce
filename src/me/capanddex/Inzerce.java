package me.capanddex;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
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
		if(sender instanceof Player) {
			Player player = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("inzerat")) {

				if (args[0].equalsIgnoreCase("vytvorit")) {
					String text = args[1];
					//ConfigHandler configHandle = new ConfigHandler(player,text,config);
					ConfigHandler.ulozInzerat(player, text, config);
				}
			} else {
				sender.sendMessage("§cTento prikaz nemuzes pouzit v konzoli!");
		if(sender instanceof Player) {
			}
				if (args[1].equalsIgnoreCase("reload")) {	//reloadcmd
					if (sender.hasPermission("inzerat.admin")){
						saveConfig();
						reloadConfig();
						sender.sendMessage("§3Config §aRealoaded!");
					}
				}
			
				if (args[2].equalsIgnoreCase("disable")) {	//plugin disable
					if (sender.hasPermission("inzerat.admin")){
						this.setEnabled(false);
						sender.sendMessage("§3Plugin §cDisabled!");
					}
				}
			
				if (args[3].equalsIgnoreCase("enable")) {	//plugin enable
					if (sender.hasPermission("inzerat.admin")){
						this.setEnabled(true);
						sender.sendMessage("§3Plugin §aEnabled!");
					}
				}
			
				if (args[4].equalsIgnoreCase("version")) {	//version
					if (sender.hasPermission("inzerat.admin")){
						PluginDescriptionFile pdf = this.getDescription();
						pdf.getVersion();
						sender.sendMessage("§3Plugin §6§lInzerce §3verze: §a" + pdf.getVersion());
					}
				}
			
				if (args[5].equalsIgnoreCase("help")) {	//helpcmd
					if (sender.hasPermission("inzerat.admin")){
						sender.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
						sender.sendMessage("§a/inzerat help §b- zobrazí tuto nápovedu.");
						sender.sendMessage("§a/inzerat vytvorit <Item na prodej> <Pocet> <Cena> §b- vytvorí inzerát podle zadaných parametru.");
						sender.sendMessage("§a/inzerat reload §b- reloadne plugin.");
						sender.sendMessage("§a/inzerat disable §b- vypne plugin.");
						sender.sendMessage("§a/inzerat enable §b- zapne plugin.");
						sender.sendMessage("§a/inzerat version §b- zobrazí verzi pluginu.");
						sender.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
					}
					else {
						sender.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
						sender.sendMessage("§a/inzerat help §b- zobrazí tuto nápovedu.");
						sender.sendMessage("§a/inzerat vytvorit [Item na prodej] [Pocet] [Cena] §b- vytvorí inzerát podle zadaných parametru.");
						sender.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
					}
				}
			
				else {
					if (sender.hasPermission("inzerat.admin")){
						sender.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
						sender.sendMessage("§a/inzerat help §b- zobrazí tuto nápovedu.");
						sender.sendMessage("§a/inzerat vytvorit <Item na prodej> <Pocet> <Cena> §b- vytvorí inzerát podle zadaných parametru.");
						sender.sendMessage("§a/inzerat reload §b- reloadne plugin.");
						sender.sendMessage("§a/inzerat disable §b- vypne plugin.");
						sender.sendMessage("§a/inzerat enable §b- zapne plugin.");
						sender.sendMessage("§a/inzerat version §b- zobrazí verzi pluginu.");
						sender.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
					}
					else {
						sender.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
						sender.sendMessage("§a/inzerat help §b- zobrazí tuto nápovedu.");
						sender.sendMessage("§a/inzerat vytvorit [Item na prodej] [Pocet] [Cena] §b- vytvorí inzerát podle zadaných parametru.");
						sender.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
					}
				
				}
				return true;
			}
		}
		return false;
	}
}