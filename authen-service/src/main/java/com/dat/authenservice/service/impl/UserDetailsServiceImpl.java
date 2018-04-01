package com.dat.authenservice.service.impl;

import com.dat.authenservice.domain.User;
import com.dat.authenservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new User(user.getId(), user.getUsername(), user.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException("Could not find the user '" + username + "'"));
    }
}
