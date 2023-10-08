package ru.tentbest.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
    @Bean
    public MapReactiveUserDetailsService mapReactiveUserDetailsService(){
        UserDetails manager = User.builder()
                .username("manager")
                .password("manager")
                .roles("MANAGER")
                .passwordEncoder(passwordEncoder()::encode)
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password("user")
                .roles("USER")
                .passwordEncoder(passwordEncoder()::encode)
                .build();

        UserDetails root = User.builder()
                .username("root")
                .password("root")
                .roles("ROOT")
                .passwordEncoder(passwordEncoder()::encode)
                .build();

        return new MapReactiveUserDetailsService(user,manager,root);
    }

    @Bean
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange((authorize) -> authorize
                        .pathMatchers("/").authenticated()
                        .pathMatchers("/save").hasRole("MANAGER")
                        .pathMatchers("/*").hasAnyRole("MANAGER","ROOT")
                        .anyExchange().denyAll()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
