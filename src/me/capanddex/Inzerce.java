package me.capanddex;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Inzerce extends JavaPlugin {
	private ArrayList<Inzerat> list;
	private int counter = 1;

	@Override
	public void onEnable() {
		new LogoutListener(this);
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
<<<<<<< HEAD
				} else if (args[0].equalsIgnoreCase("prodam")) {
					String zprava = prodam(player, args, counter, list);
					if (zprava != null) {
=======
				}
				else if (args[0].equalsIgnoreCase("prodam")) {
					String zprava = prodam(player, args, counter, list);
					if(zprava!=null) {
>>>>>>> origin/master
						this.getServer().broadcastMessage(zprava);
						counter++;
						return true;
					}
				}

				else if (args[0].equalsIgnoreCase("help")) {
					help(player);
<<<<<<< HEAD
				} else if (args[0].equalsIgnoreCase("smazat")) {
					smazat(player, args, list);
					return true;
				} else if (args[0].equalsIgnoreCase("vypsat")) {
					vypsat(player, list);
=======
				} else if (args[0].equalsIgnoreCase("vypsat")) {
					vypsat(player, list);
				}

			} else if (args[0].equalsIgnoreCase("smazat")) {
				smazat(player, args, list);
			}

			/*
			 * else if (args[0].equalsIgnoreCase("reload")) { // plugin //
			 * disable if (player.hasPermission("inzerat.admin")) {
			 * this.setEnabled(false);
			 * player.sendMessage("§3Plugin §cReloaded!"); } }
			 */
			else if (args[0].equalsIgnoreCase("fake")) {
				if (player.hasPermission("inzerat.admin")) {
					addToList(player, list);
>>>>>>> origin/master
				}
			}

<<<<<<< HEAD
				/*
				 * else if (args[0].equalsIgnoreCase("reload")) { // plugin //
				 * disable if (player.hasPermission("inzerat.admin")) {
				 * this.setEnabled(false);
				 * player.sendMessage("§3Plugin §cReloaded!"); } }
				 */
				else if (args[0].equalsIgnoreCase("test")) {
					if (player.hasPermission("inzerat.admin")) {
						addToList(player, list);
						player.sendMessage(ChatColor.GREEN + "Testovací inzerát úspìšnì odeslán.");
					}
					else {
						player.sendMessage(ChatColor.RED + "Na tento pøíkaz nemáš pravomoce.");
					}
				}

				else if (args[0].equalsIgnoreCase("version")) { // version
					if (player.hasPermission("inzerat.admin")) {
						PluginDescriptionFile description = this
								.getDescription();
						player.sendMessage("§3Plugin §6§lInzerce §3verze: §a"
								+ description.getVersion());
					} else {
						player.sendMessage("§cNa tento prikaz nemas opravneni!");
					}
				}

				else if (args.length >= 1) {
					help(player);
=======
			else if (args[0].equalsIgnoreCase("version")) { // version
				if (player.hasPermission("inzerat.admin")) {
					PluginDescriptionFile description = this.getDescription();
					player.sendMessage("§3Plugin §6§lInzerce §3verze: §a"
							+ description.getVersion());
				} else {
					player.sendMessage("§cNa tento prikaz nemas opravneni!");
>>>>>>> origin/master
				}
			} else {
				help(player);
			}
<<<<<<< HEAD
=======

		}
>>>>>>> origin/master

			// pøíkazy pøes konzoli
			else {
				if (cmd.getName().equalsIgnoreCase("inzerat")) {
					if (args.length == 0) {
						help(sender);
					} else if (args[0].equalsIgnoreCase("disable")) { // plugin
																		// disable
						this.setEnabled(false);
						getLogger().info("§3Plugin §cDisabled!");
					}

					else if (args[0].equalsIgnoreCase("version")) { // version
						PluginDescriptionFile description = this
								.getDescription();
						getLogger().info(
								"§3Plugin §6§lInzerce §3verze: §a"
										+ description.getVersion());
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
		sender.sendMessage("§a/inzerat prodam [Pocet] [Cena] §b- vytvorí prodejní inzerát na item, který máte v ruce. (pouze in-game)");
		sender.sendMessage("§a/inzerat vypsat §b- vypíše aktivní inzeráty.");
		// sender.sendMessage("§a/inzerat reload §b- reloadne plugin.");
		// sender.sendMessage("§a/inzerat disable §b- vypne plugin.");
		sender.sendMessage("§a/inzerat version §b- zobrazí verzi pluginu.");
		sender.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");

	}

	// help pro hrace
	public static void help(Player player) {
		if (player.hasPermission("inzerat.admin")) {
			player.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
			player.sendMessage("§a/inzerat help §b- zobrazí tuto nápovedu.");
			player.sendMessage("§a/inzerat prodam [Pocet] [Cena] §b- vytvorí prodejní inzerát na item, který máte v ruce.");
			player.sendMessage("§a/inzerat vypsat §b- vypíše aktivní inzeráty.");
			// player.sendMessage("§a/inzerat koupim [item] [Pocet] [Cena]   §b- vytvorí kupní inzerát na zadaný item.");
			// player.sendMessage("§a/inzerat reload §b- reloadne plugin.");
			// player.sendMessage("§a/inzerat disable §b- vypne plugin.");
			player.sendMessage("§a/inzerat vypsat §b- zobrazí verzi pluginu.");
			player.sendMessage("§a/inzerat version §b- zobrazí verzi pluginu.");
			player.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
		} else {
			player.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
			player.sendMessage("§a/inzerat help §b- zobrazí tuto nápovedu.");
			player.sendMessage("§a/inzerat prodam [Pocet] [Cena] §b- vytvorí prodejní inzerát na item, který máte v ruce.");
			player.sendMessage("§a/inzerat vypsat §b- vypíše aktivní inzeráty.");
			// player.sendMessage("§a/inzerat koupim [item] [Pocet] [Cena] §b- vytvorí kupní inzerát na zadaný item.");
			player.sendMessage("§3[********************§6Plugin §lInzerce §6Help§3********************]");
		}

	}

<<<<<<< HEAD
	// tvorba prodeje
=======
>>>>>>> origin/master
	public static String prodam(Player player, String[] args, int count,
			ArrayList<Inzerat> list) {
		float price = 0;
		int pocet = 0;
		Material mat;
		ItemStack item = player.getItemInHand();
		Inzerat ad;
		if (!(item.getType().equals(Material.AIR))) {
			mat = player.getItemInHand().getType();
			if (args.length >= 3) {
				if (StringUtils.isNumeric(args[2])) {
					price = Float.parseFloat(args[2]);
					if (StringUtils.isNumeric(args[1])) {
						pocet = Integer.parseInt(args[1]);
<<<<<<< HEAD
						if (hasItem(player, item.getType(), pocet)) {
							ad = new Inzerat(count, player, mat, price, pocet,
									false);
							list.add(ad);
							String txt = "§6Hrac §d"
									+ ad.getAdvertiser().getDisplayName()
									+ "§6 prodává §d"
									+ ad.getCount()
									+ "§6 ks §d"
									+ ad.getMat().toString().replace("_", " ")
											.toLowerCase() + "§6. Cena: §d€"
									+ ad.getPrice() + "§6 ID: §d" + ad.getID();
							return txt;
						} else {
							player.sendMessage(ChatColor.RED
									+ "Nemáš dostateèné množství k prodeji. Vezmi si další do inventáøe nebo prodávej menší množství.");
=======
						if(hasItem(player, item.getType(), pocet)){
						ad = new Inzerat(count, player, mat, price, pocet,
								false);
						list.add(ad);
						String txt = "§6Hrac §d"
								+ ad.getAdvertiser().getDisplayName()
								+ "§6 prodává §d" + ad.getCount() + "§6 ks §d"
								+ ad.getMat().toString().replace("_"," ").toLowerCase()
								+ "§6. Cena: §d€" + ad.getPrice() + "§6 ID: §d"
								+ ad.getID();
						return txt;
						}
						else {
							player.sendMessage(ChatColor.RED + "Nemáš dostateèné množství k prodeji. Vezmi si další do inventáøe nebo prodávej menší množství.");
>>>>>>> origin/master
							return null;
						}
					} else {
						player.sendMessage(ChatColor.RED
								+ "Pocet musí být cislo!");
					}
				}

				else {
					player.sendMessage(ChatColor.RED + "Cena musí být cislo!");

				}
			} else {
				player.sendMessage(ChatColor.RED
						+ "Pøíklad musí být zadán ve tvaru §a/inzerat [Pocet] [Cena].");

			}
		} else {
			player.sendMessage(ChatColor.RED
					+ "Musíš držet v ruce nìjaký item!");

		}
		return null;

	}

<<<<<<< HEAD
	// mazani
	public static void smazat(Player player, String[] args,
			ArrayList<Inzerat> list) {
		if (args.length >= 2) {
			if (StringUtils.isNumeric(args[1])) {
				if (player.hasPermission("inzerat.admin")) {
=======
	public static void smazat(Player player, String[] args,
			ArrayList<Inzerat> list) {
		if (player.hasPermission("inzerat.admin")) {
			if (args.length >= 2) {
				if (StringUtils.isNumeric(args[1])) {
>>>>>>> origin/master
					for (int i = 0; i < list.size(); i++) {
						if (Integer.parseInt(args[1]) == list.get(i).getID()) {
							list.remove(i);
							player.sendMessage(ChatColor.GREEN
<<<<<<< HEAD
									+ "Inzerát úspìšnì smazán.");
							return;
						}
					}
					player.sendMessage("Inzerát s tímto ID neexistuje.");
				} else {
					if (StringUtils.isNumeric(args[1])) {
						for (int i = 0; i < list.size(); i++) {
							if (Integer.parseInt(args[1]) == list.get(i)
									.getID()) {
								if (player.equals(list.get(i).getAdvertiser())) {
									list.remove(i);
									player.sendMessage(ChatColor.GREEN
											+ "Inzerát úspìšnì smazán.");
									return;
								} else {
									player.sendMessage(ChatColor.RED
											+ "Mùžeš mazat jen vlastní inzeráty.");
								}
							}
						}
						player.sendMessage(ChatColor.RED
								+ "Inzerát s tímto ID neexistuje.");
					} else {
						player.sendMessage(ChatColor.RED
								+ "Pøíkaz musí být zadán ve tvaru §a/inzerat smazat [id]§c.");
					}
				}
			}
		} else {
			player.sendMessage(ChatColor.RED
					+ "Pøíkaz musí být zadán ve tvaru §a/inzerat smazat [id]§c.");
		}
	}

	// vypis inzeratu
	public static void vypsat(Player player, ArrayList<Inzerat> list) {

		player.sendMessage(ChatColor.AQUA + "---Výpis všech " + list.size()
				+ " inzerátù---");
		for (int i = 0; i < list.size(); i++) {
			player.sendMessage("§6ID: §d" + list.get(i).getID()
					+ "§6 Inzerent: §d"
					+ list.get(i).getAdvertiser().getDisplayName()
					+ "§6 Pøedmìt: §d"
					+ list.get(i).getMat().toString().toLowerCase()
					+ "§6 Poèet: §d" + list.get(i).getCount() + "§6 Cena: §d"
					+ list.get(i).getPrice());
		}
		player.sendMessage(ChatColor.AQUA + "---Konec seznamu---");
	}
	//fake 
	public static void addToList(Player player, ArrayList<Inzerat> list) {
		Inzerat ad = new Inzerat(0, player, Material.AIR, 1, 1, false);
		player.sendMessage(ChatColor.GREEN + "Pøidávám testovací inzerát s ID 0.");
		list.add(ad);
	}

	public static boolean hasItem(Player player, Material itemInHand, int count) {
		PlayerInventory inventory = player.getInventory();
		ItemStack[] items = inventory.getContents();
		int has = 0;
		for (ItemStack item : items) {
			if ((item != null) && (item.getType().equals(itemInHand))
					&& (item.getAmount() > 0)) {
				has += item.getAmount();
			}
		}
		if (has >= count) {
			return true;
		} else {
			return false;
		}

	}


	public  ArrayList<Inzerat> getList() {
		return list;
	}

public int getCounter() {
	return counter;
}
}
=======
									+ "Inzerát úspìšnì odstranìn.");
						}

					}
				}
			} else {
				player.sendMessage(ChatColor.RED
						+ "Pøíkaz musí být zadán ve tvaru §a/inzerat smazat [id]§c.");
			}

		} else {
			if (args.length >= 2) {
					if (StringUtils.isNumeric(args[1])) {
						for (int i = 0; i < list.size(); i++) {
						if (Integer.parseInt(args[1]) == list.get(i).getID()) {
							if (player.getName().equals(list.get(i).getAdvertiser().getName())) {
								list.remove(i);
								player.sendMessage(ChatColor.GREEN
										+ "Inzerát úspìšnì odstranìn.");
							}
							else {
								player.sendMessage(ChatColor.RED
										+ "Mùžeš mazat jen vlastní inzeráty.");
								return;
							}
						}
						else {
							player.sendMessage(ChatColor.RED
									+ "Inzerát s tímto ID neexistuje.");
						}
					}
					
				}
					else {
						player.sendMessage(ChatColor.RED
								+ "Pøíkaz musí být zadán ve tvaru §a/inzerat smazat [id]§c.");
					}

			} else {
				player.sendMessage(ChatColor.RED
						+ "Pøíkaz musí být zadán ve tvaru §a/inzerat smazat [id]§c.");
			}
		}
	}

	public static void vypsat(Player player, ArrayList<Inzerat> list) {

		player.sendMessage(ChatColor.AQUA + "---Výpis všech " + list.size()
				+ " inzerátù---");
		for (int i = 0; i < list.size(); i++) {
			player.sendMessage("§6ID: §d" + list.get(i).getID()
					+ "§6 Inzerent: §d"
					+ list.get(i).getAdvertiser().getDisplayName()
					+ "§6 Pøedmìt: §d"
					+ list.get(i).getMat().toString().toLowerCase()
					+ "§6 Poèet: §d" + list.get(i).getCount() + "§6 Cena: §d"
					+ list.get(i).getPrice());
		}
		player.sendMessage(ChatColor.AQUA + "---Konec seznamu---");
	}

	public static void addToList(Player player, ArrayList<Inzerat> list) {
		Inzerat ad = new Inzerat(0, player, Material.AIR, 1, 1, false);
		player.sendMessage(ChatColor.GREEN + "Pøidávám fejkový inzerát");
		list.add(ad);
	}
	
	public static boolean hasItem(Player player, Material itemInHand, int count) {
    PlayerInventory inventory = player.getInventory();
    ItemStack[] items = inventory.getContents();
    int has = 0;
    for (ItemStack item : items)
    {
        if ((item != null) && (item.getType().equals(itemInHand)) && (item.getAmount() > 0))
        {
            has += item.getAmount();
        }
    }
    if(has >= count) {
    	return true;
    }
    else {
    	return false;
    }
    
	}
    
}



>>>>>>> origin/master
