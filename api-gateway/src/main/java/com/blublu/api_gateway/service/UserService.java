package com.blublu.api_gateway.service;

import com.blublu.api_gateway.entity.User;
import com.blublu.api_gateway.model.request.user.UserRequest;
import com.blublu.api_gateway.model.response.user.UserResponse;
import com.blublu.api_gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserResponse save(UserRequest user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    User userResponse =
        userRepository.save(User.builder().username(user.getUsername()).password(user.getPassword()).id(null).build());
    return UserResponse.builder().username(userResponse.getUsername()).build();
  }

  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
