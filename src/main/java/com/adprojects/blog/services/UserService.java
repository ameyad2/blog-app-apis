package com.adprojects.blog.services;

import java.util.List;

import com.adprojects.blog.payloads.UserDto;

public interface UserService {

	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserbyId(Integer userId);
	List<UserDto>	getAllUsers();
	void deleteUser(Integer userId);
}
