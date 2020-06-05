package app.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        HttpSession session = httpServletRequest.getSession();

        GrantedAuthority gaAdmin = new SimpleGrantedAuthority("ADMIN");
        if (authentication != null) {
            UserDetails authUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            session.setAttribute("authUsername", authUser.getUsername());
            session.setAttribute("authorities", authentication.getAuthorities());

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            if (authentication.getAuthorities().contains(gaAdmin)) {
                httpServletResponse.sendRedirect("/admin");
            } else {
                httpServletResponse.sendRedirect("/user");
            }
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.sendRedirect("/login");
        }
    }
}