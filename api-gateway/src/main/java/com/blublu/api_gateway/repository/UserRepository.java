package com.blublu.api_gateway.repository;

import com.blublu.api_gateway.entity.User;
import com.blublu.api_gateway.model.response.user.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
}
