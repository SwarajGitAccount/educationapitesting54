package com.education.security;

import com.education.entity.Role;
import com.education.entity.User;
import com.education.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("Username not found with this EmailId :" + usernameOrEmail));


        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),MapToGetAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> MapToGetAuthorities(Set<Role> roles) {
        return roles.stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}