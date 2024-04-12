package com.carbon.thephantasyrpg.view;
import com.carbon.thephantasyrpg.utils.SecurityUtils;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Home Page")
@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
@Route(value = "home", layout = MainLayout.class)
public class HomeView extends VerticalLayout implements BeforeEnterObserver{

    public HomeView() {
        // Component initialization
        add(new H1("Welcome to Your Game Dashboard!"));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!SecurityUtils.isUserLoggedIn()) {
            event.rerouteTo(LoginView.class); // Use the class reference instead of the string "login"
        }
    }

}
