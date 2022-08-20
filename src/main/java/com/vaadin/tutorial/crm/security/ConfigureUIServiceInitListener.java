package com.vaadin.tutorial.crm.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.tutorial.crm.ui.view.login.LoginView;
import org.springframework.stereotype.Component;

@Component // The @Component annotation registers the listener. Vaadin will pick it up on startup.
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

    // listen for the initialization of the UI
    @Override
    public void ServiceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    // In authenticate Navigation, we reroute all requests to the login, if the user is not logged in
    private void authenticateNavigation(BeforeEnterEvent event) {
        if(!LoginView.class.equals(event.getNavigationTarget()) && !SecurityUtils.isUserLoggedIn()) {
            event.rerouteTo(LoginView.class);
        }
    }
}
