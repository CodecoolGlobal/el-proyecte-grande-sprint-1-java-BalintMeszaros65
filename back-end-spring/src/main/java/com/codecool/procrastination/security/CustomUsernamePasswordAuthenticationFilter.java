package com.codecool.procrastination.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtUtil jwtUtil;

    private AuthenticationManager authenticationManager;

    public CustomUsernamePasswordAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        // generate token and write it in response body
        String token = jwtUtil.createToken(new HashMap<>(), authResult.getName());
        response.getWriter().println(token);
        if (super.logger.isDebugEnabled()) {
            super.logger.debug(LogMessage.format("Set SecurityContextHolder to %s", authResult));
        }

        super.getRememberMeServices().loginSuccess(request, response, authResult);
        if (super.eventPublisher != null) {
            super.eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
        }

        super.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    @Autowired
    public void setAuthenticationManager(@Lazy AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
