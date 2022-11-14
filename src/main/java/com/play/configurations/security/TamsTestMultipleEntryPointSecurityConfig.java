package com.play.configurations.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;


@Configuration
@EnableWebSecurity
public class TamsTestMultipleEntryPointSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Configuration
    @Order(1)
    public static class VendorWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {


        @Autowired
        private JwtFilter jwtFilter;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        UserDetailsService userDetailsService;

        private final String[] AUTH_WHITELIST = {

        };

        @Override
        public void configure(WebSecurity web) {
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors();
            http.csrf().disable().antMatcher("/**").authorizeRequests()
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    .anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        }

        @Bean("vendorAuthenticationManager")
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        @Autowired
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }
    }

    @Configuration
    @Order(2)
    public static class AffiliateSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        String path = "AFFILIATE_URI";

        @Autowired
        private JwtFilter jwtFilter;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        UserDetailsService affiliateUserDetailsService;

        private final String[] AUTH_WHITELIST = {};

        @Override
        public void configure(WebSecurity web) {
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors();
            http.csrf().disable()
                    .antMatcher(path+"/**").authorizeRequests()
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    .anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        }

        @Bean("affiliateAuthenticationManager")
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        @Autowired
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(affiliateUserDetailsService).passwordEncoder(passwordEncoder);
        }
    }

    /*
     * For admin login
     */
    @Configuration
    @Order(3)
    public static class AdminWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        String path = "ADMIN_URI";
        @Autowired
        private JwtFilter jwtFilter;
        @Autowired
        private PasswordEncoder passwordEncoder;
        @Autowired
        UserDetailsService adminUserDetailsService;
        private final String[] AUTH_WHITELIST = {};

        @Override
        public void configure(WebSecurity web) {

        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors();
            http.csrf().disable().antMatcher(path+"/**").authorizeRequests()
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    .anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        }

        @Bean("adminAuthenticationManager")
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        @Autowired
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(adminUserDetailsService).passwordEncoder(passwordEncoder);
        }

    }

    /*
     * For all routes
     */
    @Configuration
    @Order(4)
    public static class OtherWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        String path = "BASE_URI";

        @Autowired
        private JwtFilter jwtFilter;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        UserDetailsService userDetailsService;

        private final String[] AUTH_WHITELIST = {
                //  "/uploads/**",
                path+"/forms/**",
//                path+"/forgot-password/**",
//                path+"/reset-password/**",
                path+"/reference/**",
                path+"/health/**",
                path+"/custom/**"
                // "/static/**",
                // "/images/**",
                // "/manifest.json"
        };


        @Override
        public void configure(WebSecurity web) {
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors();
            http.csrf().disable().antMatcher(path+"/**").authorizeRequests()
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    .anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        }

        @Bean("defaultAuthenticationManager")
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        @Autowired
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }
    }
}