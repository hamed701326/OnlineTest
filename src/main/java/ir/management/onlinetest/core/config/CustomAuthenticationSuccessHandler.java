package ir.management.onlinetest.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils
                .authorityListToSet(authentication.getAuthorities());
        String userName = authentication.getName();
        Map<String, String> authoriryMapper =
                Map.of(
                        "ROLE_Admin", "/admin/",
                        "ROLE_Master", "/master",
                        "ROLE_Student", "/student"
                );
        String role=roles.iterator().next();
        if (authoriryMapper.keySet().contains(role)) {
            httpServletResponse.sendRedirect(authoriryMapper.get(role) + userName);
        } else  {
            httpServletResponse.sendRedirect("/error/doesn't-exist-this-role/"+role);
        }
    }

}
