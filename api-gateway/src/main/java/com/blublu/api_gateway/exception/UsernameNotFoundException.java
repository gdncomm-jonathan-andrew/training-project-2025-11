package com.blublu.api_gateway.exception;

public class UsernameNotFoundException extends org.springframework.security.core.userdetails.UsernameNotFoundException {
  public UsernameNotFoundException(String message) {
    super(message);
  }
}
