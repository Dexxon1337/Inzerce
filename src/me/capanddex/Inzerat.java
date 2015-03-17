package me.capanddex;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Inzerat {
	private Player advertiser;
	private Material itemInHand;
	private float price;
	private int count;
	
	//kind of advert - sell = false, buy = true
	private boolean buy;

	
	Inzerat(Player player, Material item, float aPrice, int number, boolean isBuy) {
		advertiser = player;
		itemInHand = item;
		price = aPrice;
		count = number;
		buy = isBuy;
	}

	public Player getAdvertiser() {
		return advertiser;
	}
	public Material getItemInHand() {
		return itemInHand;
	}
	public float getPrice() {
		return price;
	}
	public int getCount() {
		return count;
	}
	public boolean isBuy() {
		return buy;
	}
	public void setAdvertiser(Player advertiser) {
		this.advertiser = advertiser;
	}
	public void setItemInHand(Material itemInHand) {
		this.itemInHand = itemInHand;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setType(boolean buy) {
		this.buy = buy;
	}
}
