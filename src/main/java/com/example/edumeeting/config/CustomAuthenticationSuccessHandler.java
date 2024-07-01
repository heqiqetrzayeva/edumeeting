package com.example.edumeeting.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ADMIN\n")) {
            response.sendRedirect("/admin");
        } else {
            response.sendRedirect("meetings");
        }

//       Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        String redirectUrl = "/";
//
//        for (GrantedAuthority authority : authorities) {
//            String authorityName = authority.getAuthority();
//            if (authorityName.equals("ROLE_ADMIN")) {
//                redirectUrl = "/admin";
//                break;
//            } else if (authorityName.equals("ROLE_MODERATOR")) {
//                redirectUrl = "/admin";
//                break;
//            } else if (authorityName.equals("ROLE_USER")) {
//                redirectUrl = "/";
//                break;
//            }
//        }
//
//        response.sendRedirect(request.getContextPath() + redirectUrl);
//    }
    }
}
