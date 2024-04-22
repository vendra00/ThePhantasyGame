package com.carbon.thephantasyrpg.view;

import com.carbon.thephantasyrpg.enums.I18N.MainLayoutViewI18N;
import com.carbon.thephantasyrpg.utils.MainLayoutUtils;
import com.carbon.thephantasyrpg.utils.MessageUtils;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.RouterLink;

@CssImport("./styles/main-layout.css")
public class MainLayout extends AppLayout implements AfterNavigationObserver {


    private RouterLink homeLink;
    private RouterLink gameLink;
    private RouterLink settingsLink;
    private RouterLink characterCreationLink;
    private Button logoutButton;

    private final MainLayoutUtils mainLayoutUtils;

    public MainLayout(MessageUtils messageUtils) {
        this.mainLayoutUtils = new MainLayoutUtils(messageUtils);
        createHeader();
        createDrawer();

    }

    private void createHeader() {
        H1 logo = new H1(mainLayoutUtils.getMessage(MainLayoutViewI18N.DASHBOARD_TITLE));
        logo.addClassNames("text-l", "m-m");

        DrawerToggle toggle = new DrawerToggle();
        addToNavbar(toggle, logo);

    }

    private void createDrawer() {
        homeLink = new RouterLink(mainLayoutUtils.getMessage(MainLayoutViewI18N.HOME_LABEL), HomeView.class);
        gameLink = new RouterLink(mainLayoutUtils.getMessage(MainLayoutViewI18N.PLAY_GAME_LABEL), GameView.class);
        characterCreationLink = new RouterLink(mainLayoutUtils.getMessage(MainLayoutViewI18N.CHARACTER_CREATION_LABEL), PlayerCreationView.class);
        settingsLink = new RouterLink(mainLayoutUtils.getMessage(MainLayoutViewI18N.SETTINGS_LABEL), SettingsView.class);

        logoutButton = new Button(mainLayoutUtils.getMessage(MainLayoutViewI18N.LOGOUT_LABEL), VaadinIcon.SIGN_OUT.create());
        logoutButton.addClickListener(e -> logout());

        // Add the logout button to the drawer
        VerticalLayout layout = new VerticalLayout(homeLink, gameLink, characterCreationLink, settingsLink, logoutButton);
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);

        addToDrawer(layout);
    }

    private void logout() {
        // Navigate to the logout endpoint
        getUI().ifPresent(ui -> ui.getPage().setLocation("/logout"));
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        // Get current active route
        String activeRoute = event.getLocation().getFirstSegment();

        // Set active style class based on the current route
        homeLink.setClassName("menu-link" + (activeRoute.isEmpty() ? " active" : ""));
        gameLink.setClassName("menu-link" + (activeRoute.equals("game") ? " active" : ""));
        characterCreationLink.setClassName("menu-link" + (activeRoute.equals("character-creation") ? " active" : ""));
        settingsLink.setClassName("menu-link" + (activeRoute.equals("settings") ? " active" : ""));
    }
}
