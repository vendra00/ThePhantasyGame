package com.carbon.thephantasyrpg.view;

import com.carbon.thephantasyrpg.enums.I18N.LoginViewI18N;
import com.carbon.thephantasyrpg.utils.LoginViewUtils;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.router.RouterLink;

/**
 * The Login view.
 */
@Route("login")
@PageTitle("Login")
@CssImport("./styles/login-view.css")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm login = new LoginForm();
    private final LoginViewUtils loginViewUtils;

    public LoginView(LoginViewUtils loginViewUtils) {
        this.loginViewUtils = loginViewUtils;
        addClassName("login-view");
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        login.setAction("login");

        RouterLink registerLink = new RouterLink(loginViewUtils.getMessage(LoginViewI18N.ROUTER_LINK), UserRegisterView.class);
        registerLink.getElement().setAttribute("theme", "tertiary");
        registerLink.addClassName("register-link");

        add(new H1(loginViewUtils.getMessage(LoginViewI18N.LOGIN_TITLE)), login, registerLink);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (event.getLocation().getQueryParameters().getParameters().containsKey("error")) {
            login.setError(true);
        }

        if (event.getLocation().getQueryParameters().getParameters().containsKey("success")) {
            Notification notification = Notification.show("Login successful!");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        }
    }
}
