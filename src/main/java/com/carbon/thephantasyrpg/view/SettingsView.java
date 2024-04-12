package com.carbon.thephantasyrpg.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "settings", layout = MainLayout.class)
@PageTitle("Settings")
public class SettingsView extends VerticalLayout {

    public SettingsView() {
        add(new H1("Settings"));
        // Add settings controls or forms
    }
}
