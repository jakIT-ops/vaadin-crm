package com.vaadin.tutorial.crm.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.saverequest.HttpSessionRequestCache;

public class CustomRequestCache extends HttpsSessionRequestCache{


    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
        // Saves unauthenticated requests so we can redirect the user to the page they were trying to access once they're logged in.
        if(!SecurityUtils.isFrameworkInternalRequest(request)) {
            super.saveRequest(request, response);
        }
    }
}
