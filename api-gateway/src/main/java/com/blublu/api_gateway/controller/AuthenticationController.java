package com.blublu.api_gateway.controller;

import com.blublu.api_gateway.model.request.authentication.AuthenticationRequest;
import com.blublu.api_gateway.model.request.user.UserRequest;
import com.blublu.api_gateway.model.response.authentication.AuthenticationResponse;
import com.blublu.api_gateway.model.response.user.UserResponse;
import com.blublu.api_gateway.service.JwtService;
import com.blublu.api_gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class AuthenticationController {
  @Autowired
  private JwtService jwtService;
  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) throws Exception {
    String jwt = jwtService.createJwtToken(request);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

  @PostMapping("/sign-up")
  public ResponseEntity<?> signup(@RequestBody UserRequest user) {
    if (!Objects.isNull(userService.findByUsername(user.getUsername()))) {
      return ResponseEntity.badRequest().body("Username is already taken.");
    }
    UserResponse response = userService.save(user);
    return ResponseEntity.ok("User " + response.getUsername() + " registered successfully.");
  }
}
