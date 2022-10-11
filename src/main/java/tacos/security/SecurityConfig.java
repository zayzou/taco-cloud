package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        List<UserDetails> userList = new ArrayList<>();
        userList.add(new User(
                "zayzou",
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
    }

}
