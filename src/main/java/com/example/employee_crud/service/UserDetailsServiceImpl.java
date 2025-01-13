package com.example.employee_crud.service;


import com.example.employee_crud.entities.User;
import com.example.employee_crud.entities.UserPrincipal;
import com.example.employee_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if(user !=null){

            return new UserPrincipal(user);
        }
        throw new UsernameNotFoundException("User not found with username : "+username);
    }
}
