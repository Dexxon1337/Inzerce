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
	int counter = 1;
	@Override
	public void onEnable() {
		config = this.getConfig();
		saveDefaultConfig();
		getLogger().info("Plugin spusten.");
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
					Inzerat ad = prodam(player, args, counter);
					if (!ad.equals(null)) {
						this.getServer().broadcastMessage("Hrac " + ad.getAdvertiser().getDisplayName() + " prod�v� " + ad.getCount() + " ks " + ad.getMat().toString().toLowerCase() + ". Cena: �" + ad.getPrice());
						list.add(ad);
						counter++;
						return true;
					}
					else {
						return true;
					}
				}

				else if (args[0].equalsIgnoreCase("help")) {
					help(player);
				} else if (args[0].equalsIgnoreCase("vypsat")) {

					player.sendMessage(ChatColor.AQUA + "---V�pis v�ech "
							+ list.size() + " inzer�t�---");
					for (int i = 0; i < list.size(); i++) {
						player.sendMessage("ID: " + list.get(i).getID() + " Inzerent: "
								+ list.get(i).getAdvertiser().getDisplayName() + " P�edm�t: "
								+ list.get(i).getMat().toString().toLowerCase() + " Po�et: "
								+ list.get(i).getCount() + " Cena: "
								+ list.get(i).getPrice());
					}
					player.sendMessage(ChatColor.AQUA + "---Konec seznamu---");
				}
				else if (args[0].equalsIgnoreCase("smazat")) {
					if (player.hasPermission("inzerat.admin")) {
						if(StringUtils.isNumeric(args[1])) {
							for(int i=0; i<list.size();i++) {
							if(Integer.parseInt(args[1])==list.get(i).getID()) {
							list.remove(i);
							
							player.sendMessage(ChatColor.GREEN + "Inzer�t �sp�n� odstran�n.");
							}
						
						}
						}
					}
					else {
						for(int i = 0; i<list.size(); i++) {
							if(StringUtils.isNumeric(args[1])) {
							if(Integer.parseInt(args[1])==list.get(i).getID()) {
								if(player.getName().equals(list.get(i).getAdvertiser().getName())) {
									list.remove(i);
									player.sendMessage(ChatColor.GREEN + "Inzer�t �sp�n� odstran�n.");
								}
								else {
									player.sendMessage(ChatColor.RED + "M��e� mazat jen vlastn� inzer�ty.");
									return true;
								}
							}
							else {
								player.sendMessage(ChatColor.RED + "Inzer�t s t�mto ID neexistuje.");
							}
						}
							else {
								player.sendMessage(ChatColor.RED + "ID mus� b�t ��slo ze seznamu (/inzerat vypsat)");
							}
					}
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
					} else {
						player.sendMessage("�cNa tento prikaz nemas opravneni!");
					}
				}
				else {
					help(player);
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
					PluginDescriptionFile description = this.getDescription();
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

	public static Inzerat prodam(Player player, String[] args,int count) {
		float price = 0;
		int pocet = 0;
		Material mat;
		ItemStack item = player.getItemInHand();
		Inzerat ad;
		if (!(item.getType().equals(Material.AIR))) {
			mat = player.getItemInHand().getType();
	
			if (StringUtils.isNumeric(args[2])) {
				price = Float.parseFloat(args[2]);
				if (StringUtils.isNumeric(args[1])) {
					pocet = Integer.parseInt(args[1]);
					ad = new Inzerat(count, player, mat, price, pocet, false);
				} else {
					player.sendMessage(ChatColor.RED + "Pocet mus� b�t cislo!");
					ad = new Inzerat(0, player, mat, price, pocet, false);
				}
			}

			else {
				player.sendMessage(ChatColor.RED + "Cena mus� b�t cislo!");
				ad = new Inzerat(0, player, mat, price, pocet, false);
			}
			
		} else {
			player.sendMessage(ChatColor.RED
					+ "Mus� dr�et v ruce n�jak� item!");
			ad = new Inzerat(0, player, Material.AIR, price, pocet, false);

		}
		return ad;
	}

}
