package egg.proyectofinal;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import egg.proyectofinal.service.UsuarioService;

/**
 *
 * @author 54113
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SeguridadWeb {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        http.authorizeHttpRequests((requests) -> {
            try {
                requests
                        .requestMatchers("/css/*","/js/*","/img/*", "/**")
                        .permitAll()
                        .and().formLogin()
                        .loginPage("/homecontroller/login")
                        .loginProcessingUrl("/homecontroller/logincheck")
                        .usernameParameter("nombre")
                        .passwordParameter("contrasena")
                        .defaultSuccessUrl("/homecontroller/inicio")
                        .and().logout()
                        .logoutSuccessUrl("/homecontroller/inicio")
                        .permitAll();
            } catch (Exception ex) {
                Logger.getLogger(SeguridadWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return http.build();        
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
} 
