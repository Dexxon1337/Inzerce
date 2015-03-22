package me.capanddex;

import java.util.ArrayList;
import java.util.logging.Logger;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;



public class Inzerce extends JavaPlugin {
    private static final Logger log = Logger.getLogger("Minecraft");
    public static Economy econ = null;
    public static Permission perms = null;
    public static Chat chat = null;
    
	private ArrayList<Inzerat> list;
	private int counter = 1;
	
	@Override
	public void onEnable() {
		  if (!setupEconomy() ) {
	            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
	            getServer().getPluginManager().disablePlugin(this);
	            return;
	        }
	        setupPermissions();
	        setupChat();
		new LogoutListener(this);
		getLogger().info("Plugin spusten.");
		list = new ArrayList<Inzerat>();
		
	}


	@Override
	public void onDisable() {
		getLogger().info("Plugin vypnut.");

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("inzerat")) {
				if (args.length == 0) {
					help(player);
				}
				else if (args[0].equalsIgnoreCase("prodam")) {
					String zprava = prodam(player, args, counter, list);
					if (zprava != null) {
						this.getServer().broadcastMessage(zprava);
						counter++;
						return true;
					}
				}
				else if (args[0].equalsIgnoreCase("odp") || args[0].equalsIgnoreCase("odpoved") ||  args[0].equalsIgnoreCase("odpovedet")) {
					answer(player, args, list);
				}
				
				else if (args[0].equalsIgnoreCase("help")) {
					help(player);
				}
				else if (args[0].equalsIgnoreCase("seznam") || args[0].equalsIgnoreCase("list")) {
					vypsat(player, list);
				}

				}
				else if (args[0].equalsIgnoreCase("smazat")) {
				smazat(player, args, list);
				}

				else if (args[0].equalsIgnoreCase("fake")) {
					if (player.hasPermission("inzerat.admin")) {
						addToList(player, list);
					}
				}

				else if (args[0].equalsIgnoreCase("version")) { // version
					if (player.hasPermission("inzerat.admin")) {
						PluginDescriptionFile description = this.getDescription();
						player.sendMessage("�3Plugin �6�lInzerce �3verze: �a"
							+ description.getVersion());
					}
					else {
					player.sendMessage("�cNa tento prikaz nemas opravneni!");
					}
				} 
				else {
				help(player);
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

	// help in console
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
	// help for players
	public static void help(Player player) {
		if (player.hasPermission("inzerat.admin")) {
			player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
			player.sendMessage("�a/inzerat help �b- zobraz� tuto n�povedu.");
			player.sendMessage("�a/inzerat prodam [Pocet] [Cena] �b- vytvor� prodejn� inzer�t na item, kter� m�te v ruce.");
			// player.sendMessage("�a/inzerat koupim [item] [Pocet] [Cena]   �b- vytvor� kupn� inzer�t na zadan� item.");
			// player.sendMessage("�a/inzerat reload �b- reloadne plugin.");
			// player.sendMessage("�a/inzerat disable �b- vypne plugin.");
			player.sendMessage("�a/inzerat vypsat �b- zobraz� verzi pluginu.");
			player.sendMessage("�a/inzerat version �b- zobraz� verzi pluginu.");
			player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
		} else {
			player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
			player.sendMessage("�a/inzerat help �b- zobraz� tuto n�povedu.");
			player.sendMessage("�a/inzerat prodam [Pocet] [Cena] �b- vytvor� prodejn� inzer�t na item, kter� m�te v ruce.");
			// player.sendMessage("�a/inzerat koupim [item] [Pocet] [Cena] �b- vytvor� kupn� inzer�t na zadan� item.");
			player.sendMessage("�3[********************�6Plugin �lInzerce �6Help�3********************]");
		}

	}
	//create a SELL announcement
	public static String prodam(Player player, String[] args, int count,
			ArrayList<Inzerat> list) {
		double price = 0;
		int pocet = 0;
		Material mat;
		ItemStack item = player.getItemInHand();
		Inzerat ad;
		if(overitPocet(player, list)) {
		if (!(item.getType().equals(Material.AIR))) {
			mat = player.getItemInHand().getType();
			if (args.length >= 3) {
				if (StringUtils.isNumeric(args[2])) {
					price = Double.parseDouble(args[2]);
					if (StringUtils.isNumeric(args[1])) {
						pocet = Integer.parseInt(args[1]);
						if (hasItem(player, item.getType(), pocet)) {
							ad = new Inzerat(count, player, mat, price, pocet,
									false);
							list.add(ad);
							String txt = "�6Hrac �d"
									+ ad.getAdvertiser().getDisplayName()
									+ "�6 prod�v� �d"
									+ ad.getCount()
									+ "�6 ks �d"
									+ ad.getMat().toString().replace("_", " ")
											.toLowerCase() + "�6. Cena: �d�"
									+ ad.getPrice() + "�6 ID: �d" + ad.getID();
							return txt;
						} else {
							player.sendMessage(ChatColor.RED
									+ "Nem� dostate�n� mno�stv� k prodeji. Vezmi si dal�� do invent��e nebo prod�vej men�� mno�stv�.");
							return null;
						}
					} else {
						player.sendMessage(ChatColor.RED
								+ "Pocet mus� b�t cislo!");
					}
				}

				else {
					player.sendMessage(ChatColor.RED + "Cena mus� b�t cislo!");

				}
			} else {
				player.sendMessage(ChatColor.RED
						+ "P��klad mus� b�t zad�n ve tvaru �a/inzerat [Pocet] [Cena].");

			}
		} else {
			player.sendMessage(ChatColor.RED
					+ "Mus� dr�et v ruce n�jak� item!");

		}
		}
		else {
			player.sendMessage(ChatColor.RED
					+ "M��e� poslat maxim�ln� �a5 �cinzer�t� najednou!");
		}
		return null;

	
	}
	//delete an announcement
	public static void smazat(Player player, String[] args,
			ArrayList<Inzerat> list) {
		if (player.hasPermission("inzerat.admin")) {
			if (args.length >= 2) {
				if (StringUtils.isNumeric(args[1])) {
					for (int i = 0; i < list.size(); i++) {
						if (Integer.parseInt(args[1]) == list.get(i).getID()) {
							list.remove(i);
							player.sendMessage(ChatColor.GREEN
									+ "Inzer�t �sp�n� odstran�n.");
						}

					}
				}
			} else {
				player.sendMessage(ChatColor.RED
						+ "P��kaz mus� b�t zad�n ve tvaru �a/inzerat smazat [id]�c.");
			}

		} else {
			if (args.length >= 2) {
				if (StringUtils.isNumeric(args[1])) {
					for (int i = 0; i < list.size(); i++) {
						if (Integer.parseInt(args[1]) == list.get(i).getID()) {
							if (player.getName().equals(
									list.get(i).getAdvertiser().getName())) {
								list.remove(i);
								player.sendMessage(ChatColor.GREEN
										+ "Inzer�t �sp�n� odstran�n.");
							} else {
								player.sendMessage(ChatColor.RED
										+ "M��e� mazat jen vlastn� inzer�ty.");
								return;
							}
						} else {
							player.sendMessage(ChatColor.RED
									+ "Inzer�t s t�mto ID neexistuje.");
						}
					}

				} else {
					player.sendMessage(ChatColor.RED
							+ "P��kaz mus� b�t zad�n ve tvaru �a/inzerat smazat [id]�c.");
				}

			} else {
				player.sendMessage(ChatColor.RED
						+ "P��kaz mus� b�t zad�n ve tvaru �a/inzerat smazat [id]�c.");
			}
		}
	}
	//print the list
	public static void vypsat(Player player, ArrayList<Inzerat> list) {

		player.sendMessage(ChatColor.AQUA + "---V�pis v�ech " + list.size()
				+ " inzer�t�---");
		for (int i = 0; i < list.size(); i++) {
			player.sendMessage("�6ID: �d" + list.get(i).getID()
					+ "�6 Inzerent: �d"
					+ list.get(i).getAdvertiser().getDisplayName()
					+ "�6 P�edm�t: �d"
					+ list.get(i).getMat().toString().toLowerCase()
					+ "�6 Po�et: �d" + list.get(i).getCount() + "�6 Cena: �d"
					+ list.get(i).getPrice());
		}
		player.sendMessage(ChatColor.AQUA + "---Konec seznamu---");
	}
	//add a testing instance to the list
	public static void addToList(Player player, ArrayList<Inzerat> list) {
		Inzerat ad = new Inzerat(0, player, Material.AIR, 1, 1, false);
		player.sendMessage(ChatColor.GREEN + "P�id�v�m testovac� inzer�t");
		list.add(ad);
	}
//check if player has the item
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
	//answer an advertisement
	
	
	
	
	public static void answer(Player player, String [] args, ArrayList<Inzerat> list) {
		if(args.length>=1 && StringUtils.isNumeric(args[1])) {
			int id = Integer.parseInt(args[1]);
			Inzerat currentAd = null;
			for(int i = 0; i < list.size();i++) {
				if(list.get(i).getID()==id) {
					currentAd = list.get(i);
				}
			}
			if(!currentAd.equals(null)) {
			if(!player.equals(currentAd.getAdvertiser()))	{
				if(currentAd.isBuy()) {
					
				}
			
				else {
			if(econ.getBalance(player)>=currentAd.getPrice()) {	
				if(hasItem(currentAd.getAdvertiser(),currentAd.getMat(),currentAd.getCount())) {
					int count = 0;
					for (ItemStack i : player.getInventory()) {
						if (i == null) {
							count++;
						}
					}
					if(count>=Math.ceil(currentAd.getCount()/64)) {
						currentAd.getAdvertiser().getInventory().removeItem(new ItemStack[] {new ItemStack(currentAd.getMat(), currentAd.getCount()) });
						player.getInventory().addItem(new ItemStack(currentAd.getMat(),currentAd.getCount()));
					econ.withdrawPlayer(player, currentAd.getPrice());
					econ.depositPlayer(currentAd.getAdvertiser(), currentAd.getPrice());

					currentAd.getAdvertiser().sendMessage(ChatColor.GREEN + "Prodej �a" + currentAd.getCount() + " " + currentAd.getMat().toString().toLowerCase().replace("_", " ") +  "hr��i �a" + player.getDisplayName() + " byl �sp�n�!");
					player.sendMessage(ChatColor.GREEN + "N�kup �a" + currentAd.getCount() + " " + currentAd.getMat().toString().replace("_", " ") + " od hr��e " + currentAd.getAdvertiser().getDisplayName() + " byl �sp�n�!");
				
					for(int i = list.size()-1; i>=0;i--) {
						if(list.get(i).getID()==id) {
							list.remove(i);
						}
					}
					
					}
					else {
						player.sendMessage(ChatColor.RED + "Pro n�kup tohoto po�tu v�c� pot�ebuje� alespo�" + Math.ceil(currentAd.getCount()/64) + "m�sta v invent��i.");
					}
				}
				else {
					currentAd.getAdvertiser().sendMessage("�aHr�� �c" + player.getDisplayName() + "�ase pokou�� odpoved�t na tv�j inzer�t s ID �d" + currentAd.getID() + "�a, ale nem� dostatek �d" + currentAd.getMat().toString().replace("_", " ") +"�a.");
					currentAd.getAdvertiser().sendMessage("�aPot�ebn� po�et: �c" + currentAd.getCount());
					player.sendMessage("�aHr�� �c" + currentAd.getAdvertiser().getDisplayName() + "�a nem� dostatek itemu na prodej. Zkus ho kontaktovat pomoc� /msg �c" + currentAd.getAdvertiser().getName());
				}
			}
			}
			}
			else {
				player.sendMessage("�cNem��e� odpov�d�t na sv�j inzer�t!");
			}
			}
			else {
				player.sendMessage("�cInzer�t s t�mto ID neexistuje.");	
			}
		}
		
		else {
				player.sendMessage("�cP��kaz mus� b�t zad�n ve tvaru �a/inzerat odpovedet [id]�c.");

		}
	}
	//check if number of items is right
    public static boolean overitPocet(Player player, ArrayList<Inzerat> list) {
    	int counter = 0;
    	for(int i = 0; i < list.size(); i++) {
    		if (list.get(i).getAdvertiser().equals(player)) {
    			counter++;
    		}
    		
    	}
    	if(counter < 5) {
			return true;
		}
		else {
			return false;
		}
    }
	public ArrayList<Inzerat> getList() {
		return this.list;
	}
	
	
	
	
	//Vault linking
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
}
