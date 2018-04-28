package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.Writer;


public interface UserService {
	
	User findById(long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);
	
	String writerResult(Writer writer);
	
	String test();
	
}
