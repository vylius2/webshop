package com.webshop.service;

import com.webshop.exception.UserAlreadyExists;
import com.webshop.persistance.entity.User;
import com.webshop.persistance.repository.RoleRepository;
import com.webshop.persistance.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public User getById(Long id){
        return userRepository.getById(id);
    }

    public User createUser(User user){
        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExists(user.getUsername());
        }
        user.setRoles(Set.of(roleRepository.findRoleByName("USER").get()));
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
