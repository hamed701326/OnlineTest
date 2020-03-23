package ir.management.onlinetest.core.config;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    AccountRepository accountRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils
                .authorityListToSet(authentication.getAuthorities());
        String userName = authentication.getName();
        Long accountId=(Long) httpServletRequest.getSession().getAttribute("accountId");
        if(accountId==null){
            accountId=accountRepository.findByUserName(userName).getId();
        }
        httpServletRequest.getSession().setAttribute("accountId",accountId);
        Map<String, String> authoriryMapper =
                Map.of(
                        "ROLE_Admin", "/admin/",
                        "ROLE_Master", "/master/",
                        "ROLE_Student", "/student/"
                );
        String role=roles.iterator().next();
        if (authoriryMapper.keySet().contains(role)) {
            httpServletResponse.sendRedirect(authoriryMapper.get(role) + userName);
        } else  {
            httpServletResponse.sendRedirect("/error/doesn't-exist-this-role/"+role);
        }
    }

}
