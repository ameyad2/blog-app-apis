package com.adprojects.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adprojects.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
