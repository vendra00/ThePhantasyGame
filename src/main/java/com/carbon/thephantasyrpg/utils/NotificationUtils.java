package com.carbon.thephantasyrpg.utils;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

import org.springframework.stereotype.Component;

/**
 * Utility class to show notifications and create dialogs
 */
@Component
public class NotificationUtils {

    /**
     * Show a notification with the given message, duration and position
     * @param message the message to show
     * @param duration the duration of the notification
     * @param position the position of the notification
     */
    public void showNotification(String message, int duration, Notification.Position position) {
        Notification notification = new Notification(message, duration);
        notification.setPosition(position);
        notification.open();
    }

    /**
     * Create a success dialog with the given message and close button text
     * @param message the message to show in the dialog
     * @param closeButtonText the text of the close button in the dialog
     * @return the created dialog with the given message and close button
     */
    public Dialog createSuccessDialog(String message, String closeButtonText) {
        Dialog dialog = new Dialog();
        VerticalLayout layout = new VerticalLayout();

        // Add the success message
        layout.add(new Text(message));

        // Add the close button
        Button closeButton = new Button(closeButtonText, event -> dialog.close());
        closeButton.setWidthFull(); // Make the button full width

        // Align items to center for the layout
        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        // Add components to the layout
        layout.add(closeButton);

        // Set the size of the layout to fit the components
        layout.setSizeUndefined();

        // Add layout to the dialog
        dialog.add(layout);
        return dialog;
    }

    /**
     * Create an error dialog with the given error message and close button text
     * @param errorMessage the error message to show in the dialog
     * @param closeButtonText the text of the close button in the dialog
     * @return the created dialog with the given error message and close button
     */
    public Dialog createErrorDialog(String errorMessage, String closeButtonText) {
        Dialog dialog = new Dialog();
        VerticalLayout dialogLayout = new VerticalLayout();

        dialogLayout.add(new Text("Error: " + errorMessage));

        Button closeButton = new Button(closeButtonText, event -> dialog.close());
        dialogLayout.add(closeButton);

        dialogLayout.setAlignItems(Alignment.CENTER); // Center alignment for all items
        dialogLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END); // Push items to the end on the main axis

        closeButton.setWidthFull(); // Make the button full width

        dialog.add(dialogLayout);
        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(false);

        return dialog;
    }
}
