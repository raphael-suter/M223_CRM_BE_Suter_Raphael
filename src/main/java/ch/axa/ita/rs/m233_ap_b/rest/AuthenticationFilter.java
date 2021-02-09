package ch.axa.ita.rs.m233_ap_b.rest;

import ch.axa.ita.rs.m233_ap_b.model.Message;
import ch.axa.ita.rs.m233_ap_b.repository.EmployeeRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ch.axa.ita.rs.m233_ap_b.utility.JsonTool.toJson;
import static org.springframework.http.HttpMethod.OPTIONS;

public class AuthenticationFilter extends OncePerRequestFilter {
    private final EmployeeRepository employeeRepository;

    public AuthenticationFilter(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (isAuthorized(httpServletRequest) || httpServletRequest.getMethod().equals(OPTIONS.name())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            String json = toJson(new Message("Du bist nicht berechtigt, auf diese Ressource zuzugreifen."));

            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.setContentType("application/json");

            httpServletResponse
                    .getWriter()
                    .write(json);

            httpServletResponse.flushBuffer();
        }
    }

    private String getToken(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("authorization");

        if (authorizationHeader == null || authorizationHeader.isEmpty() || authorizationHeader.length() < 7) {
            return null;
        }

        return authorizationHeader.substring(7);
    }

    public boolean isAuthorized(HttpServletRequest httpServletRequest) {
        return employeeRepository
                .findByToken(getToken(httpServletRequest))
                .isPresent();
    }
}
