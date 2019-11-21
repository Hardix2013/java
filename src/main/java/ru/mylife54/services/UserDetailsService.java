package ru.mylife54.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService extends UserDetails {
    void autologin(String login, String password);
}
