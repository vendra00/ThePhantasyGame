package com.carbon.thephantasyrpg.view;

import com.carbon.thephantasyrpg.utils.SecurityUtils;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "game", layout = MainLayout.class)
@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
@PageTitle("Play Game")
public class GameView extends VerticalLayout implements BeforeEnterObserver {

    public GameView() {
        add(new H1("Game Interface"));
        // Add game controls or interfaces
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!SecurityUtils.isUserLoggedIn()) {
            event.rerouteTo(LoginView.class); // Use the class reference instead of the string "login"
        }
    }
}
