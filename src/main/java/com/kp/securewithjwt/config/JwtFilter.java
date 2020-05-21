package com.kp.securewithjwt.config;

import ch.qos.logback.core.db.dialect.MySQLDialect;
import com.kp.securewithjwt.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        /*httpServletResponse.addHeader("Access-Control-Allow-Origin","*");
        httpServletResponse.addHeader("Access-Control-Allow-Headers",
                "Origin,Accept,X-Requested-With,Content-Type,"
                        + "Acces-Control-Request-Method,"
                        + "Access-Control-Request-Headers,"
                        + "Authorization");
        httpServletResponse.addHeader("Access-Control-Expose-Headers",
                "Access-Control-Allow-Origin,"
                        + "Access-Control-Allow-Credentials,"
                        + "Authorization");
*/
        final String authorizatioHeader = httpServletRequest.getHeader("Authorization");
        if(httpServletRequest.getMethod().equals("OPTIONS")) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        }else {
            if (authorizatioHeader == null || !authorizatioHeader.startsWith("Bearer")) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }
        }

        String username = null;
        String jwt = null;
        if(authorizatioHeader!=null && authorizatioHeader.startsWith("Bearer")){
            jwt = authorizatioHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
            if(jwtUtil.validateToken(jwt,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
         filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
