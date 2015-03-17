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

				}

				/*
				 * else if (args[0].equalsIgnoreCase("reload")) { // plugin //
				 * disable if (player.hasPermission("inzerat.admin")) {
				 * this.setEnabled(false);
				 * player.sendMessage("§3Plugin §cReloaded!"); } }
				 */

				else if (args[0].equalsIgnoreCase("version")) { // version
					if (player.hasPermission("inzerat.admin")) {
						PluginDescriptionFile pdf = this.getDescription();
						pdf.getVersion();
						player.sendMessage("§3Plugin §6§lInzerce §3verze: §a"
								+ pdf.getVersion());
					} else {
						player.sendMessage("§cNa tento prikaz nemas opravneni!");
					}
				}
			}
			// pøíkazy pøes konzoli
			else {
				if (cmd.getName().equalsIgnoreCase("inzerat")) {
					if (args[0].equalsIgnoreCase("disable")) { // plugin disable
						this.setEnabled(false);
						getLogger().info("§3Plugin §cDisabled!");
					}

					else if (args[0].equalsIgnoreCase("version")) { // version
						PluginDescriptionFile pdf = this.getDescription();
						pdf.getVersion();
						getLogger().info(
								"§3Plugin §6§lInzerce §3verze: §a"
										+ pdf.getVersion());
					}
					// bez argumentu ci s retardovanými argumenty
					else {
						help(sender);
					}
				}
			}

		}

		return true;
	}

	// help do konzole
	public static void help(CommandSender sender) {
		sender.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
		sender.sendMessage("§a/inzerat help §b- zobrazí tuto nápovedu.");
		sender.sendMessage("§a/inzerat vytvorit <Item na prodej> <Pocet> <Cena> §b- vytvorí inzerát podle zadaných parametru.");
		sender.sendMessage("§a/inzerat reload §b- reloadne plugin.");
		sender.sendMessage("§a/inzerat disable §b- vypne plugin.");
		sender.sendMessage("§a/inzerat enable §b- zapne plugin.");
		sender.sendMessage("§a/inzerat version §b- zobrazí verzi pluginu.");
		sender.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");

	}

	// help pro hrace
	public static void help(Player player) {
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
			player.sendMessage("§a/inzerat prodam [Pocet] [Cena] §b- vytvorí prodejní inzerát na item, který máte v ruce.");
			player.sendMessage("§a/inzerat prodam [item] [Pocet] [Cena] §b- vytvorí kupní inzerát na zadaný item.");
			player.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
		}

	}

}
