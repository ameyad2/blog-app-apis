package com.adprojects.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adprojects.blog.payloads.ApiResponse;
import com.adprojects.blog.payloads.UserDto;
import com.adprojects.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	// POST -create user
	@PostMapping("/")
	public ResponseEntity<UserDto>	createUser(@RequestBody UserDto userDto){
		
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
		
	}
	
	// PUT - update user
	@PutMapping("/{userId}") // path url variable
	public ResponseEntity<UserDto>	updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer uid){
		UserDto updatedUser= this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}
	// DELETE - delete user
	
	@DeleteMapping("/{userId}") 
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
		this.userService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}
	
	// GET - user get multiple
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	// GET - user get single
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userService.getUserbyId(userId));
	}
	
}
