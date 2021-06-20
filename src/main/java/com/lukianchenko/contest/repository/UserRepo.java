package com.lukianchenko.contest.repository;

import com.lukianchenko.contest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
  User findByUsername(String username);
}
