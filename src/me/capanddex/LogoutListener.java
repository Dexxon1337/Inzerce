package me.capanddex;

import java.util.ArrayList;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LogoutListener implements Listener {
	Inzerce plg;

	public LogoutListener(Inzerce plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		plg = plugin;
	}

	@EventHandler
	public void onLogout(PlayerQuitEvent event) {
		ArrayList<Inzerat> list = plg.getList();
		Player player = event.getPlayer();
		for (int i = list.size()-1; i >= 0; i--) {
				if (player.equals(list.get(i).getAdvertiser())) {
					list.remove(i);
				}
			}
		}
}