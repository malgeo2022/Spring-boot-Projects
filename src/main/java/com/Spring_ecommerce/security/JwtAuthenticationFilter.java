package com.Spring_ecommerce.security;


import com.Spring_ecommerce.service.user.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
             try{
                 String jwtToken = extractFromRequest(request);
                 if (StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)){
                     int id = jwtTokenProvider.getUserIdFromJwt(jwtToken);
                     UserDetails userDetails = userDetailsService.loadUserById(id);
                     if (userDetails != null){
                         UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                         auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                         SecurityContextHolder.getContext().setAuthentication(auth);
                     }
                 }
             }catch (Exception e){
                 return;
             }
             filterChain.doFilter(request, response);
    }

    private String extractFromRequest(HttpServletRequest request){
        String bearer = request.getHeader("Authorization");
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer"))
            return bearer.substring("Bearer".length() + 1);
        return null;
    }




}
