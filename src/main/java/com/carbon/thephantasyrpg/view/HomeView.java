package com.carbon.thephantasyrpg.view;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

@PageTitle("Home Page")
@Route(value = "home", layout = MainLayout.class)
public class HomeView extends VerticalLayout implements BeforeEnterObserver {

    public HomeView() {
        // Component initialization
        add(new H1("Welcome to Your Game Dashboard!"));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (SecurityContextHolder.getContext().getAuthentication() == null ||
                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated() ||
                "anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())) {
            event.rerouteTo("login");
        }
    }
}
