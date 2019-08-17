package dao;

import java.util.List;

import models.Book;
import models.BookShop;
import models.Shop;

public interface BookShopDAO {
	
	public List<BookShop> findAssortmentOfShop(Shop shop);
	
	public void update(BookShop bs);
	
	public BookShop findBookShopById(Shop shop, Book book);
}
