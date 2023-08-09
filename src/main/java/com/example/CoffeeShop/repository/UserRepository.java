package com.example.CoffeeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.CoffeeShop.model.User.User;;

public interface UserRepository extends JpaRepository<User, Integer> {
  UserDetails findByLogin(String login);
}
