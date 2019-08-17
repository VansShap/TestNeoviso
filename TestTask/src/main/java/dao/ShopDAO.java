package dao;

import java.util.List;

import models.Shop;

public interface ShopDAO {
	
	public Shop findShopById(int id);
	
	public List<Shop> findAll();

	public void update(Shop shop);
}
