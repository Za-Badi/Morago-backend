package com.habsida.morago.config;

import com.habsida.morago.model.entity.User;
import com.habsida.morago.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.stream.Collectors;

@Configuration
public class ApplicationConfiguration {
    private final UserRepository userRepository;

    public ApplicationConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return phone -> {
            User user = userRepository.findByPhoneWithRoles(phone)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return user.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList());
                }
                @Override
                public String getPassword() {
                    return user.getPassword();
                }
                @Override
                public String getUsername() {
                    return user.getPhone();
                }
                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }
                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }
                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }
                @Override
                public boolean isEnabled() {
                    return true;
                }
            };
        };
    }
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        int strength = 12;
        return new BCryptPasswordEncoder(strength);
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}