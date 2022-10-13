package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tacos.data.UserRepository;
import tacos.model.User;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/design", "/orders").hasRole("USER")
                .antMatchers("/", "/**").permitAll()
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repos) {
        System.out.println("Finding user in the store");
        return username -> {
            User user = repos.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException(username + "not found");
            }
            return user;
        };
    }


    /**
     * In Memory User management for Spring Security
     */
    /*@Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        List<UserDetails> userList = new ArrayList<>();
        userList.add(new User(
                "user",
                passwordEncoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_USER"),
                        new SimpleGrantedAuthority("ROLE_STANDARD")
                )));

        userList.add(new User(
                "user",
                passwordEncoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
        ));

        userList.add(new User(
                "standard",
                passwordEncoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_STANDARD"))
        ));
        return new InMemoryUserDetailsManager(userList);
    }*/

}
