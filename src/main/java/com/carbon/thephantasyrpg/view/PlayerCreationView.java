package com.carbon.thephantasyrpg.view;

import com.carbon.thephantasyrpg.controller.PlayerController;
import com.carbon.thephantasyrpg.dto.PlayerCreationDTO;
import com.carbon.thephantasyrpg.enums.PlayerCreationViewI18N;
import com.carbon.thephantasyrpg.enums.RaceServiceI18N;
import com.carbon.thephantasyrpg.enums.Races;
import com.carbon.thephantasyrpg.record.AccordionsSetUp;
import com.carbon.thephantasyrpg.record.BasicAttributesSection;
import com.carbon.thephantasyrpg.record.CharacterBasicInformation;
import com.carbon.thephantasyrpg.service.RaceService;
import com.carbon.thephantasyrpg.utils.MessageUtils;
import com.carbon.thephantasyrpg.utils.NotificationUtils;
import com.carbon.thephantasyrpg.utils.PlayerCreationViewUtils;
import com.carbon.thephantasyrpg.utils.RaceServiceUtils;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Map;

/**
 * The PlayerCreationView class is a view that allows the user to create a player character
 */
@PageTitle("Phantasy RPG - Player Creation")
@CssImport("./styles/player-creation-view.css")
@Route("player-creation")
public class PlayerCreationView extends VerticalLayout {

    private final Map<Races, Map<String, Double>> raceAttributes;

    // Constants for the fields (basic attributes)
    private static final double DEFAULT_VALUE = 5D;
    private static final double MIN_VALUE = 1D;
    private static final double MAX_VALUE = 30D;

    // Constants for validation (in the binder method)
    private static final int MAX_NAME_LENGTH = 20;
    private static final int MIN_NAME_LENGTH = 3;

    private final PlayerController playerController;
    private final RaceService raceService;
    private final PlayerCreationViewUtils playerCreationViewUtils;
    private final RaceServiceUtils raceServiceUtils;
    private final NotificationUtils notificationUtils;

    // Create a binder for the PlayerCreationDTO
    private final Binder<PlayerCreationDTO> binder = new Binder<>(PlayerCreationDTO.class);

    // Declare fields as instance variables
    private final TextField nameField = new TextField();
    private final NumberField strengthField = new NumberField();
    private final NumberField dexterityField = new NumberField();
    private final NumberField constitutionField = new NumberField();
    private final NumberField intelligenceField = new NumberField();
    private final NumberField wisdomField = new NumberField();
    private final NumberField charismaField = new NumberField();
    private final ComboBox<Races> raceField = new ComboBox<>();
    private final TextArea raceDescriptionArea = new TextArea();

    /**
     * Constructor for the PlayerCreationView
     * @param messageUtils the message source
     * @param playerController the player controller
     */
    @Autowired
    public PlayerCreationView(RaceService raceService, MessageUtils messageUtils, PlayerController playerController, RaceService raceService1, RaceServiceUtils raceServiceUtils, NotificationUtils notificationUtils) {
        this.raceAttributes = raceService.fetchRaceAttributes();
        this.playerCreationViewUtils = new PlayerCreationViewUtils(messageUtils);
        this.playerController = playerController;
        this.raceService = raceService1;
        this.raceServiceUtils = raceServiceUtils;
        this.notificationUtils = notificationUtils;

        // Set default alignment and size for the VerticalLayout
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setSizeUndefined();

        // Fields Labels
        fieldsLabelsSetUp();

        // Description text Area setup
        raceDescriptionAreaSetUp();

        // Set up the basic attributes fields values
        basicAttributesValuesSetUp();

        // Create Accordion Sections
        AccordionsSetUp accordionSectionSetUp = getAccordionSectionsSetUp();

        // Set up the combo boxes with the enum values
        comboBoxSetUp();

        // Bind fields needed for validation and conversion
        fieldBinder();

        // Create the submit button
        Button submitButton = getSubmitBtnSetUp();

        // Set up the main layout
        mainLayoutSetUp(accordionSectionSetUp);

        // Add the sections and button to the layout
        add(accordionSectionSetUp.characterBasicInformation().accordion(), accordionSectionSetUp.basicAttributesSection().accordion(), submitButton);

    }

    private void raceDescriptionAreaSetUp() {
        raceDescriptionArea.setVisible(false);
        raceDescriptionArea.setReadOnly(true);
    }

    /**
     * Set up the basic attributes fields values
     */
    private void basicAttributesValuesSetUp() {
        setUpBasicAttributeField(strengthField);
        setUpBasicAttributeField(dexterityField);
        setUpBasicAttributeField(constitutionField);
        setUpBasicAttributeField(intelligenceField);
        setUpBasicAttributeField(wisdomField);
        setUpBasicAttributeField(charismaField);
    }

    /**
     * Set up the basic attribute field
     * @param field the field to set up the basic attributes
     */
    private void setUpBasicAttributeField(NumberField field) {

        // Set the default values for the fields
        field.setValue(DEFAULT_VALUE);
        field.setStepButtonsVisible(true);
        field.setMin(MIN_VALUE);
        field.setMax(MAX_VALUE);
    }

    /**
     * Set up the submit button
     * @return the submit button
     */
    private Button getSubmitBtnSetUp() {
        Button submitButton = new Button(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.CREATE_CHARACTER_BUTTON), event -> createPlayer());
        HorizontalLayout buttonLayout = new HorizontalLayout(submitButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        submitButton.addClassName("create-character-btn");
        return submitButton;
    }

    /**
     * Set up the main layout
     * @param accordionSectionSetUp the AccordionsSetUp object
     */
    private void mainLayoutSetUp(AccordionsSetUp accordionSectionSetUp) {
        accordionSectionSetUp.characterBasicInformation().characterBasicInformationFormLayout().add(nameField, raceField, raceDescriptionArea);
        accordionSectionSetUp.basicAttributesSection().basicAttributesFormLayout().add(strengthField, dexterityField, constitutionField, intelligenceField, wisdomField, charismaField);
    }

    /**
     * Set up the combo boxes with the enum values
     */
    private void comboBoxSetUp() {
        raceComboboxSetUp();
    }

    /**
     * Set up the race combo box with the enum values
     */
    private void raceComboboxSetUp() {
        raceField.setItems(Races.values());
        raceField.setItemLabelGenerator(Races::getDisplayName);

        raceField.addValueChangeListener(event -> {
            Races selectedRace = event.getValue();
            if (selectedRace != null) {
                Map<String, Double> attributes = raceAttributes.getOrDefault(selectedRace, Collections.emptyMap());
                String description = raceService.fetchRaceDescription(selectedRace);

                // Update the UI components with the fetched attributes and description
                updateAttributeFields(attributes);
                raceDescriptionArea.setValue(description);
                raceDescriptionArea.setVisible(true);
            } else {
                // Handle case when no race is selected, for example by clearing the description
                raceDescriptionArea.setValue("");
                raceDescriptionArea.setVisible(false);
            }
        });
    }

    /**
     * Update the attribute fields with the modifiers from the selected race
     * @param attributes the attributes to update the fields with
     */
    private void updateAttributeFields(Map<String, Double> attributes) {

        resetAttributeFieldsToDefault();

        // Apply race attribute modifiers to the default value
        strengthField.setValue(DEFAULT_VALUE + attributes.getOrDefault(raceServiceUtils.getMessage(RaceServiceI18N.STRENGTH_MODIFIER), 0.0));
        dexterityField.setValue(DEFAULT_VALUE + attributes.getOrDefault(raceServiceUtils.getMessage(RaceServiceI18N.DEXTERITY_MODIFIER), 0.0));
        constitutionField.setValue(DEFAULT_VALUE + attributes.getOrDefault(raceServiceUtils.getMessage(RaceServiceI18N.CONSTITUTION_MODIFIER), 0.0));
        intelligenceField.setValue(DEFAULT_VALUE + attributes.getOrDefault(raceServiceUtils.getMessage(RaceServiceI18N.INTELLIGENCE_MODIFIER), 0.0));
        wisdomField.setValue(DEFAULT_VALUE + attributes.getOrDefault(raceServiceUtils.getMessage(RaceServiceI18N.WISDOM_MODIFIER), 0.0));
        charismaField.setValue(DEFAULT_VALUE + attributes.getOrDefault(raceServiceUtils.getMessage(RaceServiceI18N.CHARISMA_MODIFIER), 0.0));
    }

    /**
     * Reset the attribute fields to the default value
     */
    private void resetAttributeFieldsToDefault() {
        strengthField.setValue(DEFAULT_VALUE);
        dexterityField.setValue(DEFAULT_VALUE);
        constitutionField.setValue(DEFAULT_VALUE);
        intelligenceField.setValue(DEFAULT_VALUE);
        wisdomField.setValue(DEFAULT_VALUE);
        charismaField.setValue(DEFAULT_VALUE);
    }

    /**
     * Set up the Accordion sections
     * @return the Result object containing the CharacterBasicInformation and BasicAttributesSection objects
     */
    private AccordionsSetUp getAccordionSectionsSetUp() {
        CharacterBasicInformation characterBasicInformation = getCharacterBasicInformation();
        BasicAttributesSection basicAttributesSection = getBasicAttributesSection();

        return new AccordionsSetUp(characterBasicInformation, basicAttributesSection);
    }

    /**
     * Get the CharacterBasicInformation object
     * @return the CharacterBasicInformation object
     */
    private CharacterBasicInformation getCharacterBasicInformation() {

        Accordion accordion = new Accordion();
        FormLayout characterBasicInformationFormLayout = new FormLayout();

        AccordionPanel characterBasicInformationInfoPanel = accordion.add(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.CHARACTER_BASIC_INFORMATION_INFO_PANEL), characterBasicInformationFormLayout);
        characterBasicInformationInfoPanel.setTooltipText(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.CHARACTER_BASIC_INFORMATION_TOOLTIP));
        characterBasicInformationInfoPanel.addThemeVariants(DetailsVariant.FILLED);

        return new CharacterBasicInformation(accordion, characterBasicInformationFormLayout);
    }

    /**
     * Get the BasicAttributesSection object
     * @return the BasicAttributesSection object
     */
    private BasicAttributesSection getBasicAttributesSection() {
        Accordion accordion = new Accordion();
        FormLayout basicAttributesFormLayout = new FormLayout();

        AccordionPanel basicAttributesInfoPanel = accordion.add(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.CHARACTER_BASIC_ATTRIBUTES_INFO_PANEL), basicAttributesFormLayout);
        basicAttributesInfoPanel.setTooltipText(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.CHARACTER_BASIC_ATTRIBUTES_TOOLTIP));
        basicAttributesInfoPanel.addThemeVariants(DetailsVariant.FILLED);
        return new BasicAttributesSection(accordion, basicAttributesFormLayout);
    }

    /**
     * Set up the labels for the fields in the view using the PlayerCreationViewI18N enum
     */
    private void fieldsLabelsSetUp() {

        // Add the class name to the fields for styling purposes
        nameField.addClassName(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.FORM_ITEM_KEY));
        strengthField.addClassName(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.FORM_ITEM_KEY));
        dexterityField.addClassName(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.FORM_ITEM_KEY));
        constitutionField.addClassName(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.FORM_ITEM_KEY));
        intelligenceField.addClassName(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.FORM_ITEM_KEY));
        wisdomField.addClassName(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.FORM_ITEM_KEY));
        charismaField.addClassName(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.FORM_ITEM_KEY));
        raceField.addClassName(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.FORM_ITEM_KEY));
        raceDescriptionArea.addClassName(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.FORM_ITEM_KEY));

        // Set the labels for the fields
        nameField.setLabel(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.NAME_LABEL));
        strengthField.setLabel(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.STRENGTH_LABEL));
        dexterityField.setLabel(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.DEXTERITY_LABEL));
        constitutionField.setLabel(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.CONSTITUTION_LABEL));
        intelligenceField.setLabel(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.INTELLIGENCE_LABEL));
        wisdomField.setLabel(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.WISDOM_LABEL));
        charismaField.setLabel(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.CHARISMA_LABEL));
        raceField.setLabel(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.RACE_LABEL));
        raceDescriptionArea.setLabel(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.RACE_DESCRIPTION_LABEL));
    }

    /**
     * Bind the fields to the PlayerCreationDTO using the binder and set up the validation
     */
    private void fieldBinder() {

        binder.forField(nameField)
                .withValidator(name -> name.length() >= MIN_NAME_LENGTH, playerCreationViewUtils.getMessage(PlayerCreationViewI18N.NAME_FIELD_MIN_LENGTH_ERROR))
                .withValidator(name -> name.length() <= MAX_NAME_LENGTH, playerCreationViewUtils.getMessage(PlayerCreationViewI18N.NAME_FIELD_MAX_LENGTH_ERROR))
                .asRequired(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.NAME_FIELD_REQUIRED))
                .bind(PlayerCreationDTO::getName, PlayerCreationDTO::setName);

        playerCreationViewUtils.basicAttributesFieldValidator(binder, strengthField, PlayerCreationDTO::getStrength, PlayerCreationDTO::setStrength);
        playerCreationViewUtils.basicAttributesFieldValidator(binder, dexterityField, PlayerCreationDTO::getDexterity, PlayerCreationDTO::setDexterity);
        playerCreationViewUtils.basicAttributesFieldValidator(binder, constitutionField, PlayerCreationDTO::getConstitution, PlayerCreationDTO::setConstitution);
        playerCreationViewUtils.basicAttributesFieldValidator(binder, intelligenceField, PlayerCreationDTO::getIntelligence, PlayerCreationDTO::setIntelligence);
        playerCreationViewUtils.basicAttributesFieldValidator(binder, wisdomField, PlayerCreationDTO::getWisdom, PlayerCreationDTO::setWisdom);
        playerCreationViewUtils.basicAttributesFieldValidator(binder, charismaField, PlayerCreationDTO::getCharisma, PlayerCreationDTO::setCharisma);

        binder.forField(raceField).bind(PlayerCreationDTO::getRace, PlayerCreationDTO::setRace);
    }

    /**
     * Create a player using the PlayerCreationDTO and the playerService
     */
    private void createPlayer() {
        PlayerCreationDTO playerDTO = new PlayerCreationDTO();
        if (binder.writeBeanIfValid(playerDTO)) {
            try {
                playerController.createPlayer(playerDTO);
                Dialog successDialog = notificationUtils.createSuccessDialog(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.CHARACTER_SUCCESS_DIALOG), playerCreationViewUtils.getMessage(PlayerCreationViewI18N.CLOSE_DIALOG_BUTTON));
                successDialog.open();
                binder.readBean(null); // Clear the form
            } catch (Exception e) {
                Dialog errorDialog = notificationUtils.createErrorDialog(e.getMessage(), playerCreationViewUtils.getMessage(PlayerCreationViewI18N.CLOSE_DIALOG_BUTTON));
                errorDialog.open();
            }
        } else {
            notificationUtils.showNotification(playerCreationViewUtils.getMessage(PlayerCreationViewI18N.CHARACTER_FAIL_DIALOG), 3000, Notification.Position.BOTTOM_START);
        }
    }
}


