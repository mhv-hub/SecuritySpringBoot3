package com.mhv.testsecurity.testsecurity.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhv.testsecurity.testsecurity.config.CustomUserDetailsService;
import com.mhv.testsecurity.testsecurity.util.TokenUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class RequestFilterToken extends OncePerRequestFilter {

    @Autowired
    private TokenUtility tokenUtility;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorisationHeader = request.getHeader("Token");
        String userName = null;
        String token = null;
        short checkFlag=0;
        try{
            if(authorisationHeader != null){
                token = authorisationHeader;
                userName = tokenUtility.extractUsername(token);
            }
            if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
                checkFlag++;
                if (tokenUtility.validateToken(token, userDetails)) {
                       UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }
        }catch (ExpiredJwtException expiredJwtException){
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("message", "Token has expired !!");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            mapper.writeValue(response.getWriter(), errorDetails);
        }catch (Exception jwtException){
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("message", "Invalid token !!");
            response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            mapper.writeValue(response.getWriter(), errorDetails);
        }
        filterChain.doFilter(request, response);
    }
}
