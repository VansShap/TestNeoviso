package dao;

import models.User;

public interface UserDAO {
	
	public User findUserByLoginAndPass(String username, String pass);
	
	public User findUserById (int id);
	
	public void update(User user);
}
