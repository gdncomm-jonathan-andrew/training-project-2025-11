package com.blublu.api_gateway.service;

import com.blublu.api_gateway.model.request.authentication.AuthenticationRequest;
import com.blublu.api_gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private CustomUserDetailsService userDetailsService;
  @Autowired
  private JwtUtil jwtUtil;

  public String createJwtToken(AuthenticationRequest request) throws Exception {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
        request.getPassword()));
    UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
    return jwtUtil.generateToken(userDetails);
  }
}
