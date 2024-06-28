package com.example.edumeeting.config.auth;//package com.example.demo.config.auth;

import com.example.edumeeting.models.UserEntity;
import com.example.edumeeting.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username);

        if(user != null){
            User loginUser = new User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getEmailConfirmed(),
                    true,
                    true,
                    true,
                    user.getRoles().stream().map(m-> new SimpleGrantedAuthority(m.getName())).collect(Collectors.toList())
            );
            return loginUser;

        }else{
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
