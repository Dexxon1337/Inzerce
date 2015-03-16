package me.capanddex;

import org.apache.commons.lang3.StringUtils;
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
		getLogger().info("Plugin spu�t�n.");
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
					String itemName = args[1];
					int pocet = 0;
					if(StringUtils.isNumeric(args[2])) {
					pocet = Integer.parseInt(args[2]);
					}
					else {
						player.sendMessage("Jsi pi�a? Pro� p�e� hovna ty kundo? Druh� argument mus� b�t ��slo.");
						return false;
					}
					ConfigHandler.ulozInzerat(player, itemName, pocet, config);
				}	
				
			else if (args[0].equalsIgnoreCase("disable")) { // plugin disable
				if (player.hasPermission("inzerat.admin")) {
					this.setEnabled(false);
					player.sendMessage("�3Plugin �cDisabled!");
				}
			}
			else if (args[0].equalsIgnoreCase("enable")) { // plugin enable
				if (player.hasPermission("inzerat.admin")) {
					this.setEnabled(true);
					player.sendMessage("�3Plugin �aEnabled!");
				}
			}	
				
			else if (args[0].equalsIgnoreCase("version")) { // version
					if (player.hasPermission("inzerat.admin")) {
						PluginDescriptionFile pdf = this.getDescription();
						pdf.getVersion();
						player.sendMessage("�3Plugin �6�lInzerce �3verze: �a"
								+ pdf.getVersion());
					}
				}	
				
				
			else if (args[0].equalsIgnoreCase("help")) { // helpcmd
						if (player.hasPermission("inzerat.admin")) {
							player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
							player.sendMessage("�a/inzerat help �b- zobraz� tuto n�povedu.");
							player.sendMessage("�a/inzerat vytvorit <Item na prodej> <Pocet> <Cena> �b- vytvor� inzer�t podle zadan�ch parametru.");
							player.sendMessage("�a/inzerat reload �b- reloadne plugin.");
							player.sendMessage("�a/inzerat disable �b- vypne plugin.");
							player.sendMessage("�a/inzerat enable �b- zapne plugin.");
							player.sendMessage("�a/inzerat version �b- zobraz� verzi pluginu.");
							player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
						} else {
							player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
							player.sendMessage("�a/inzerat help �b- zobraz� tuto n�povedu.");
							player.sendMessage("�a/inzerat vytvorit [Item na prodej] [Pocet] [Cena] �b- vytvor� inzer�t podle zadan�ch parametru.");
							player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
						}
					}
				//bez argumentu ci s retardovan�mi argumenty
					else {
						if (player.hasPermission("inzerat.admin")) {
							player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
							player.sendMessage("�a/inzerat help �b- zobraz� tuto n�povedu.");
							player.sendMessage("�a/inzerat vytvorit <Item na prodej> <Pocet> <Cena> �b- vytvor� inzer�t podle zadan�ch parametru.");
							player.sendMessage("�a/inzerat reload �b- reloadne plugin.");
							player.sendMessage("�a/inzerat disable �b- vypne plugin.");
							player.sendMessage("�a/inzerat enable �b- zapne plugin.");
							player.sendMessage("�a/inzerat version �b- zobraz� verzi pluginu.");
							player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
						} else {
							player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
							player.sendMessage("�a/inzerat help �b- zobraz� tuto n�povedu.");
							player.sendMessage("�a/inzerat vytvorit [Item na prodej] [Pocet] [Cena] �b- vytvor� inzer�t podle zadan�ch parametru.");
							player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
						}
					}
				
				return true;
				
			}
		}
		//p��kazy p�es konzoli
			else {
				if (args[0].equalsIgnoreCase("disable")) { // plugin disable
						this.setEnabled(false);
						getLogger().info("�3Plugin �cDisabled!");
				}
				else if (args[0].equalsIgnoreCase("enable")) { // plugin enable
						this.setEnabled(true);
						getLogger().info("�3Plugin �aEnabled!");
				}	
					
				else if (args[0].equalsIgnoreCase("version")) { // version
							PluginDescriptionFile pdf = this.getDescription();
							pdf.getVersion();
							getLogger().info("�3Plugin �6�lInzerce �3verze: �a"
									+ pdf.getVersion());
						}
					//bez argumentu ci s retardovan�mi argumenty
				else {
				sender.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
				sender.sendMessage("�a/inzerat help �b- zobraz� tuto n�povedu.");
				sender.sendMessage("�a/inzerat vytvorit <Item na prodej> <Pocet> <Cena> �b- vytvor� inzer�t podle zadan�ch parametru.");
				sender.sendMessage("�a/inzerat reload �b- reloadne plugin.");
				sender.sendMessage("�a/inzerat disable �b- vypne plugin.");
				sender.sendMessage("�a/inzerat enable �b- zapne plugin.");
				sender.sendMessage("�a/inzerat version �b- zobraz� verzi pluginu.");
				sender.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
				}
			}

		return false;
	}
}

