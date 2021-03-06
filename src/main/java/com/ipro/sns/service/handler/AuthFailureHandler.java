package com.ipro.sns.service.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        request.setAttribute("username", request.getParameter("username"));
        String errorMsg = null;
        if (exception instanceof InternalAuthenticationServiceException) {
            errorMsg = "account does not exist Please check again";
        }
        else if (exception instanceof BadCredentialsException) {
            errorMsg = "Wrong Password please try again";
        }

        request.setAttribute("errormsg", errorMsg);
        String DEFAULT_FAILURE_URL = "/login?error=true";
        request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
    }
}
