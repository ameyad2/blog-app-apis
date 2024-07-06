package com.adprojects.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adprojects.blog.entities.User;
import com.adprojects.blog.exceptions.ResourceNotFoundException;
import com.adprojects.blog.payloads.UserDto;
import com.adprojects.blog.repositories.UserRepo;
import com.adprojects.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user= this.dtoToUser(userDto);
		
		User savedUser= this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " id ",userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		User updatedUser= this.userRepo.save(user);
		UserDto userDto1=  this.userToDto(updatedUser);
		
		return userDto1;
		
	}

	@Override
	public UserDto getUserbyId(Integer userId) {

	
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " id ",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {


		List<User> users= this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {


		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " id ",userId));
		this.userRepo.delete(user);
		
	}
	
	public User dtoToUser(UserDto userDto) {
		
		// Manual Conversion 
//		User user= new User();
//		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		// Model mapper Conversion
		
		User user = this.modelMapper.map(userDto, User.class);
		
		return user;
	}
	
	public UserDto userToDto(User user)
	{
		// Manual Conversion 
//		UserDto userDto= new UserDto();
//		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
		return userDto;
	}
}
