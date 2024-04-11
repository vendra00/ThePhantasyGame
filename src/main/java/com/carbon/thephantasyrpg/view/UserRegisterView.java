package com.carbon.thephantasyrpg.view;

import com.carbon.thephantasyrpg.dto.UserRegistrationDTO;
import com.carbon.thephantasyrpg.enums.Roles;
import com.carbon.thephantasyrpg.model.User;
import com.carbon.thephantasyrpg.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Route("register")
@PageTitle("Register New User")
@AnonymousAllowed
public class UserRegisterView extends VerticalLayout {

    @Autowired
    public UserRegisterView(UserService userService) {
        // Assume this is your service with the registerUser method

        // Create form fields
        TextField usernameField = new TextField("Username");
        PasswordField passwordField = new PasswordField("Password");

        // Initialize the registration button with a click listener
        Button registerButton = new Button("Register", event -> {
            // Retrieve the values from the form fields
            String username = usernameField.getValue();
            String password = passwordField.getValue();

            // Construct the DTO
            UserRegistrationDTO registrationDto = new UserRegistrationDTO(username, password, Set.of(Roles.USER.getRoleName())); // Replace with actual DTO constructor

            // Call the registration method
            try {
                User registeredUser = userService.registerUser(registrationDto);
                Notification.show("User registered successfully! Welcome, " + registeredUser.getUsername());

                clearRegistrationForm(usernameField, passwordField);

                UI.getCurrent().access(() -> UI.getCurrent().navigate(LoginView.class));

            } catch (Exception e) {
                Notification.show("Registration failed: " + e.getMessage(), 5000, Notification.Position.MIDDLE);
            }
        });

        // Add components to the layout
        add(new H1("Register New User"), usernameField, passwordField, registerButton);

        // Style the layout
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private static void clearRegistrationForm(TextField usernameField, PasswordField passwordField) {
        usernameField.clear();
        passwordField.clear();
    }
}
