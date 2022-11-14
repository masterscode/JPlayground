package com.play.configurations.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.play.payloads.response.RESTRespone;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class JwtFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);
    private final JwtUtil jwtUtil;
    private final Map<String, UserDetailsService> userDetailsServiceStrategies;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            String token = jwtUtil.extractTokenFromRequest(request);
            if (StringUtils.hasText(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                //extract the username from token
                String userName = jwtUtil.extractUsername(token);
                String userType = jwtUtil.extractAttributeFromToken(token, "USER_TYPE");
                UserDetails userDetails = loadUserByUsername(userName, userType);
                if (jwtUtil.validateToken(token, userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                //this.writeErrorResponse("Invalid Token",response);
                //return;
            }
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            SecurityContextHolder.clearContext();
            this.writeErrorResponse("Invalid Token", response, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            this.writeErrorResponse("Unknown error has occurred", response, HttpStatus.INTERNAL_SERVER_ERROR);
            LOGGER.error("Unknown error", e);
        }
    }

    private void writeErrorResponse(String errMsg, HttpServletResponse response, HttpStatus httpStatus) {
        try {
            RESTRespone<Object> ar = RESTRespone.builder().message(errMsg).build();

            response.setStatus(httpStatus.value());
            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            PrintWriter out = response.getWriter();
            out.write(mapper.writeValueAsString(ar));
        } catch (Exception e) {
            LOGGER.error("Unknown error", e);
        }
    }


    private UserDetails loadUserByUsername(String userName, String userType) {
        //use strategy here to fetch appropriate user detail service to use to load user
        //based on usertype store in token
        String strategyKey = "";
//        if (userType.equals(UserType.ADMIN.name())) {
//            strategyKey = "adminUserDetailsService";
//        } else {
//            strategyKey = "clientUserDetailsService";
//        }
        return userDetailsServiceStrategies.get(strategyKey).loadUserByUsername(userName);
    }

}