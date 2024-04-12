package com.carbon.thephantasyrpg.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "game", layout = MainLayout.class)
@PageTitle("Play Game")
public class GameView extends VerticalLayout {

    public GameView() {
        add(new H1("Game Interface"));
        // Add game controls or interfaces
    }
}
