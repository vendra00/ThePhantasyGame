package com.carbon.thephantasyrpg.view;

import com.carbon.thephantasyrpg.dto.PlayerCreationDTO;
import com.carbon.thephantasyrpg.enums.PlayerCreationViewI18N;
import com.carbon.thephantasyrpg.enums.Races;
import com.carbon.thephantasyrpg.record.AccordionsSetUp;
import com.carbon.thephantasyrpg.record.BasicAttributesSection;
import com.carbon.thephantasyrpg.record.CharacterBasicInformation;
import com.carbon.thephantasyrpg.service.PlayerService;
import com.carbon.thephantasyrpg.utils.DoubleToIntegerConverter;
import com.carbon.thephantasyrpg.utils.NotificationUtils;
import com.carbon.thephantasyrpg.utils.PlayerCreationViewUtils;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * The PlayerCreationView class is a view that allows the user to create a player character
 */
@PageTitle("Phantasy RPG - Player Creation")
@Route("player-creation")
public class PlayerCreationView extends VerticalLayout {
    private final PlayerService playerService;
    private final PlayerCreationViewUtils viewUtils;
    private final NotificationUtils notificationUtils;

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

    /**
     * Constructor for the PlayerCreationView
     * @param messageSource the message source
     * @param playerService the player service
     */
    @Autowired
    public PlayerCreationView(MessageSource messageSource, PlayerService playerService, NotificationUtils notificationUtils) {
        this.playerService = playerService;
        this.viewUtils = new PlayerCreationViewUtils(messageSource);
        this.notificationUtils = notificationUtils;

        // Fields Labels
        fieldsLabelsSetUp();

        // Create Accordion Sections
        AccordionsSetUp accordionSectionSetUp = getAccordionSectionsSetUp();

        // Set up the combo boxes with the enum values
        comboBoxSetUp();

        // Bind fields needed for validation and conversion
        fieldBinder();

        // Create the submit button
        Button submitButton = new Button(viewUtils.getMessage(PlayerCreationViewI18N.CREATE_CHARACTER_BUTTON), event -> createPlayer());

        // Set up the main layout
        mainLayoutSetUp(accordionSectionSetUp);

        // Add the sections and button to the layout
        add(accordionSectionSetUp.characterBasicInformation().accordion(), accordionSectionSetUp.basicAttributesSection().accordion(), submitButton);

    }

    /**
     * Set up the main layout
     * @param accordionSectionSetUp the AccordionsSetUp object
     */
    private void mainLayoutSetUp(AccordionsSetUp accordionSectionSetUp) {
        accordionSectionSetUp.characterBasicInformation().characterBasicInformationFormLayout().add(nameField, raceField);
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

        AccordionPanel characterBasicInformationInfoPanel = accordion.add(viewUtils.getMessage(PlayerCreationViewI18N.CHARACTER_BASIC_INFORMATION_INFO_PANEL), characterBasicInformationFormLayout);
        characterBasicInformationInfoPanel.setTooltipText(viewUtils.getMessage(PlayerCreationViewI18N.CHARACTER_BASIC_INFORMATION_TOOLTIP));
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

        AccordionPanel basicAttributesInfoPanel = accordion.add(viewUtils.getMessage(PlayerCreationViewI18N.CHARACTER_BASIC_ATTRIBUTES_INFO_PANEL), basicAttributesFormLayout);
        basicAttributesInfoPanel.setTooltipText(viewUtils.getMessage(PlayerCreationViewI18N.CHARACTER_BASIC_ATTRIBUTES_TOOLTIP));
        basicAttributesInfoPanel.addThemeVariants(DetailsVariant.FILLED);
        return new BasicAttributesSection(accordion, basicAttributesFormLayout);
    }

    /**
     * Set up the labels for the fields in the view using the PlayerCreationViewI18N enum
     */
    private void fieldsLabelsSetUp() {
        nameField.setLabel(viewUtils.getMessage(PlayerCreationViewI18N.NAME_LABEL));
        strengthField.setLabel(viewUtils.getMessage(PlayerCreationViewI18N.STRENGTH_LABEL));
        dexterityField.setLabel(viewUtils.getMessage(PlayerCreationViewI18N.DEXTERITY_LABEL));
        constitutionField.setLabel(viewUtils.getMessage(PlayerCreationViewI18N.CONSTITUTION_LABEL));
        intelligenceField.setLabel(viewUtils.getMessage(PlayerCreationViewI18N.INTELLIGENCE_LABEL));
        wisdomField.setLabel(viewUtils.getMessage(PlayerCreationViewI18N.WISDOM_LABEL));
        charismaField.setLabel(viewUtils.getMessage(PlayerCreationViewI18N.CHARISMA_LABEL));
        raceField.setLabel(viewUtils.getMessage(PlayerCreationViewI18N.RACE_LABEL));
    }

    /**
     * Bind the fields to the PlayerCreationDTO using the binder object and set up validators and converters for the fields as needed
     */
    private void fieldBinder() {
        final int MAX_NAME_LENGTH = 20;
        final int MIN_NAME_LENGTH = 3;
        binder.forField(nameField)
                .withValidator(name -> name.length() >= MIN_NAME_LENGTH, viewUtils.getMessage(PlayerCreationViewI18N.NAME_FIELD_MIN_LENGTH_ERROR))
                .withValidator(name -> name.length() <= MAX_NAME_LENGTH, viewUtils.getMessage(PlayerCreationViewI18N.NAME_FIELD_MAX_LENGTH_ERROR))
                .asRequired(viewUtils.getMessage(PlayerCreationViewI18N.NAME_FIELD_REQUIRED))
                .bind(PlayerCreationDTO::getName, PlayerCreationDTO::setName);

        binder.forField(strengthField)
                .withConverter(new DoubleToIntegerConverter(viewUtils.getMessage(PlayerCreationViewI18N.NUMBER_FIELD_ERROR)))
                .bind(PlayerCreationDTO::getStrength, PlayerCreationDTO::setStrength);

        binder.forField(dexterityField)
                .withConverter(new DoubleToIntegerConverter(viewUtils.getMessage(PlayerCreationViewI18N.NUMBER_FIELD_ERROR)))
                .bind(PlayerCreationDTO::getDexterity, PlayerCreationDTO::setDexterity);

        binder.forField(constitutionField)
                .withConverter(new DoubleToIntegerConverter(viewUtils.getMessage(PlayerCreationViewI18N.NUMBER_FIELD_ERROR)))
                .bind(PlayerCreationDTO::getConstitution, PlayerCreationDTO::setConstitution);

        binder.forField(intelligenceField)
                .withConverter(new DoubleToIntegerConverter(viewUtils.getMessage(PlayerCreationViewI18N.NUMBER_FIELD_ERROR)))
                .bind(PlayerCreationDTO::getIntelligence, PlayerCreationDTO::setIntelligence);

        binder.forField(wisdomField)
                .withConverter(new DoubleToIntegerConverter(viewUtils.getMessage(PlayerCreationViewI18N.NUMBER_FIELD_ERROR)))
                .bind(PlayerCreationDTO::getWisdom, PlayerCreationDTO::setWisdom);

        binder.forField(charismaField)
                .withConverter(new DoubleToIntegerConverter(viewUtils.getMessage(PlayerCreationViewI18N.NUMBER_FIELD_ERROR)))
                .bind(PlayerCreationDTO::getCharisma, PlayerCreationDTO::setCharisma);

        binder.forField(raceField).bind(PlayerCreationDTO::getRace, PlayerCreationDTO::setRace);
    }

    /**
     * Create a player using the PlayerCreationDTO and the playerService
     */
    private void createPlayer() {
        PlayerCreationDTO playerDTO = new PlayerCreationDTO();
        if (binder.writeBeanIfValid(playerDTO)) {
            try {
                playerService.createPlayer(playerDTO);
                Dialog successDialog = notificationUtils.createSuccessDialog(viewUtils.getMessage(PlayerCreationViewI18N.CHARACTER_SUCCESS_DIALOG), viewUtils.getMessage(PlayerCreationViewI18N.CLOSE_DIALOG_BUTTON));
                successDialog.open();
                binder.readBean(null); // Clear the form
            } catch (Exception e) {
                Dialog errorDialog = notificationUtils.createErrorDialog(e.getMessage(), viewUtils.getMessage(PlayerCreationViewI18N.CLOSE_DIALOG_BUTTON));
                errorDialog.open();
            }
        } else {
            notificationUtils.showNotification(viewUtils.getMessage(PlayerCreationViewI18N.CHARACTER_FAIL_DIALOG), 3000, Notification.Position.BOTTOM_START);
        }
    }
}


