package me.capanddex;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Inzerat {
	private int ID;
	private Player advertiser;
	private Material mat;
	private double price;
	private int count;
	
	//kind of advert - sell = false, buy = true
	private boolean buy;

	
	Inzerat(int serial, Player player, Material item, double aPrice, int number, boolean isBuy) {
		ID = serial;
		advertiser = player;
		mat = item;
		price = aPrice;
		count = number;
		buy = isBuy;
	}
	public int getID() {
		return ID;
	}
	public Player getAdvertiser() {
		return advertiser;
	}
	public Material getMat() {
		return mat;
	}
	public double getPrice() {
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
	public void setMat(Material mat) {
		this.mat = mat;
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
