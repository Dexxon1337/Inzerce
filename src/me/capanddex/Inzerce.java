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
		saveDefaultConfig();
		getLogger().info("Plugin spuštìn.");
		saveConfig();

	}

	@Override
	public void onDisable() {
		saveConfig();
		getLogger().info("Plugin vypnut.");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("inzerat")) {

				if (args[0].equalsIgnoreCase("vytvorit")) {
					String[] stringSlov = new String[args.length];
					for(int i=1;i < args.length;i++) {
						stringSlov[i-1] = args[i] + " ";
					}
					String text = stringSlov.toString();
					// ConfigHandler configHandle = new
					// ConfigHandler(player,text,config);
					ConfigHandler.ulozInzerat(player, text, config);
				}
			} else {
				sender.sendMessage("§cTento prikaz nemuzes pouzit v konzoli!");
				if (args[0].equalsIgnoreCase("reload")) { // reloadcmd
					if (player.hasPermission("inzerat.admin")) {
						saveConfig();
						reloadConfig();
						player.sendMessage("§3Config §aRealoaded!");
					}
				}

				if (args[0].equalsIgnoreCase("disable")) { // plugin disable
					if (player.hasPermission("inzerat.admin")) {
						this.setEnabled(false);
						player.sendMessage("§3Plugin §cDisabled!");
					}
				}

				if (args[0].equalsIgnoreCase("enable")) { // plugin enable
					if (player.hasPermission("inzerat.admin")) {
						this.setEnabled(true);
						player.sendMessage("§3Plugin §aEnabled!");
					}
				}

				if (args[0].equalsIgnoreCase("version")) { // version
					if (player.hasPermission("inzerat.admin")) {
						PluginDescriptionFile pdf = this.getDescription();
						pdf.getVersion();
						player.sendMessage("§3Plugin §6§lInzerce §3verze: §a"
								+ pdf.getVersion());
					}
				}

				if (args[0].equalsIgnoreCase("help")) { // helpcmd
					if (player.hasPermission("inzerat.admin")) {
						player.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
						player.sendMessage("§a/inzerat help §b- zobrazí tuto nápovedu.");
						player.sendMessage("§a/inzerat vytvorit <Item na prodej> <Pocet> <Cena> §b- vytvorí inzerát podle zadaných parametru.");
						player.sendMessage("§a/inzerat reload §b- reloadne plugin.");
						player.sendMessage("§a/inzerat disable §b- vypne plugin.");
						player.sendMessage("§a/inzerat enable §b- zapne plugin.");
						player.sendMessage("§a/inzerat version §b- zobrazí verzi pluginu.");
						player.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
					} else {
						player.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
						player.sendMessage("§a/inzerat help §b- zobrazí tuto nápovedu.");
						player.sendMessage("§a/inzerat vytvorit [Item na prodej] [Pocet] [Cena] §b- vytvorí inzerát podle zadaných parametru.");
						player.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
					}
				}

				else {
					if (player.hasPermission("inzerat.admin")) {
						player.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
						player.sendMessage("§a/inzerat help §b- zobrazí tuto nápovedu.");
						player.sendMessage("§a/inzerat vytvorit <Item na prodej> <Pocet> <Cena> §b- vytvorí inzerát podle zadaných parametru.");
						player.sendMessage("§a/inzerat reload §b- reloadne plugin.");
						player.sendMessage("§a/inzerat disable §b- vypne plugin.");
						player.sendMessage("§a/inzerat enable §b- zapne plugin.");
						player.sendMessage("§a/inzerat version §b- zobrazí verzi pluginu.");
						player.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
					} else {
						player.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
						player.sendMessage("§a/inzerat help §b- zobrazí tuto nápovedu.");
						player.sendMessage("§a/inzerat vytvorit [Item na prodej] [Pocet] [Cena] §b- vytvorí inzerát podle zadaných parametru.");
						player.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
					}

				}
				return true;

			}
		}
		return false;
	}
}
