package me.capanddex;

import java.util.ArrayList;


import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Inzerce extends JavaPlugin {
	FileConfiguration config;
	ArrayList<Inzerat> list;

	@Override
	public void onEnable() {
		config = this.getConfig();
		saveDefaultConfig();
		getLogger().info("Plugin spu�t�n.");
		saveConfig();
		list = new ArrayList<Inzerat>();
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
				if (args.length == 0) {
					help(player);
				} else if (args[0].equalsIgnoreCase("prodam")) {
					float price;
					int pocet;
					Material mat;
					ItemStack item = player.getItemInHand();
					if (!(item.getType().equals(Material.AIR))) {
						mat = player.getItemInHand().getType();

						if (StringUtils.isNumeric(args[2])) {
							price = Float.parseFloat(args[2]);
							if (StringUtils.isNumeric(args[1])) {
								pocet = Integer.parseInt(args[1]);
							} else {
								player.sendMessage(ChatColor.RED
										+ "Pocet mus� b�t cislo!");
								return true;
							}
						}

						else {
							player.sendMessage(ChatColor.RED
									+ "Cena mus� b�t cislo!");
							return true;
						}

						Inzerat ad = new Inzerat(player, mat, price, pocet,
								false);
						player.sendMessage("Hrac "
								+ ad.getAdvertiser().getDisplayName()
								+ " prod�v� " + ad.getCount() + "x "
								+ ad.getMat().name() + " za "
								+ ad.getPrice() + " korun.");

						list.add(ad);
					} else {
						player.sendMessage(ChatColor.RED
								+ "Mus� dr�et v ruce n�jak� item!");
						return true;
					}
				}

				else if (args[0].equalsIgnoreCase("help")) {
					help(player);
				}
				else if (args[0].equalsIgnoreCase("vypsat")) {
					player.sendMessage(ChatColor.AQUA + "+++++V�pis v�ech aktu�ln�ch inzer�t�+++++");
					for(int i = 0; i<list.size(); i++) {
					// tady bude v�pis	
						player.sendMessage("Inzerent: " + "" + " P�edm�t: " + "" + " Po�et: " + "" + " Cena: " + "");
					}
				}

				/*
				 * else if (args[0].equalsIgnoreCase("reload")) { // plugin //
				 * disable if (player.hasPermission("inzerat.admin")) {
				 * this.setEnabled(false);
				 * player.sendMessage("�3Plugin �cReloaded!"); } }
				 */

				else if (args[0].equalsIgnoreCase("version")) { // version
					if (player.hasPermission("inzerat.admin")) {
						PluginDescriptionFile description = this
								.getDescription();
						player.sendMessage("�3Plugin �6�lInzerce �3verze: �a"
								+ description.getVersion());
					} 
					else {
						player.sendMessage("�cNa tento prikaz nemas opravneni!");
					}
				}
				
			}
		}

			// p��kazy p�es konzoli
			else {
				if (cmd.getName().equalsIgnoreCase("inzerat")) {
					if (args.length == 0) {
						help(sender);
					} else if (args[0].equalsIgnoreCase("disable")) { // plugin
																		// disable
						this.setEnabled(false);
						getLogger().info("�3Plugin �cDisabled!");
					}

					else if (args[0].equalsIgnoreCase("version")) { // version
						PluginDescriptionFile description = this
								.getDescription();
						getLogger().info(
								"�3Plugin �6�lInzerce �3verze: �a"
										+ description.getVersion());
					}

					// bez argumentu ci s retardovan�mi argumenty
					else {
						help(sender);
					}
				}
			}
			return true;
	}

	// help do konzole
	public static void help(CommandSender sender) {
		sender.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
		sender.sendMessage("�a/inzerat help �b- zobraz� tuto n�povedu.");
		sender.sendMessage("�a/inzerat prodam [Pocet] [Cena] �b- vytvor� prodejn� inzer�t na item, kter� m�te v ruce. (pouze in-game)");
		sender.sendMessage("�a/inzerat prodam [item] [Pocet] [Cena] �b- vytvor� kupn� inzer�t na zadan� item. (pouze in-game)");
		// sender.sendMessage("�a/inzerat reload �b- reloadne plugin.");
		// sender.sendMessage("�a/inzerat disable �b- vypne plugin.");
		sender.sendMessage("�a/inzerat version �b- zobraz� verzi pluginu.");
		sender.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");

	}

	// help pro hrace
	public static void help(Player player) {
		if (player.hasPermission("inzerat.admin")) {
			player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
			player.sendMessage("�a/inzerat help �b- zobraz� tuto n�povedu.");
			player.sendMessage("�a/inzerat prodam [Pocet] [Cena] �b- vytvor� prodejn� inzer�t na item, kter� m�te v ruce.");
			player.sendMessage("�a/inzerat prodam [item] [Pocet] [Cena]   �b- vytvor� kupn� inzer�t na zadan� item.");
			// player.sendMessage("�a/inzerat reload �b- reloadne plugin.");
			// player.sendMessage("�a/inzerat disable �b- vypne plugin.");
			player.sendMessage("�a/inzerat version �b- zobraz� verzi pluginu.");
			player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
		} else {
			player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
			player.sendMessage("�a/inzerat help �b- zobraz� tuto n�povedu.");
			player.sendMessage("�a/inzerat prodam [Pocet] [Cena] �b- vytvor� prodejn� inzer�t na item, kter� m�te v ruce.");
			player.sendMessage("�a/inzerat prodam [item] [Pocet] [Cena] �b- vytvor� kupn� inzer�t na zadan� item.");
			player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
		}

	}

}
