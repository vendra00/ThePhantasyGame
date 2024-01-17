package com.carbon.thephantasyrpg.view;

import com.carbon.thephantasyrpg.dto.PlayerCreationDTO;
import com.carbon.thephantasyrpg.enums.Races;
import com.carbon.thephantasyrpg.service.PlayerService;
import com.carbon.thephantasyrpg.utils.DoubleToIntegerConverter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("player-creation")
public class PlayerCreationView extends VerticalLayout {

    private final PlayerService playerService;

    private final Binder<PlayerCreationDTO> binder = new Binder<>(PlayerCreationDTO.class);

    // Declare fields as instance variables
    private final TextField nameField = new TextField("Name");
    private final NumberField strengthField = new NumberField("Strength");
    private final NumberField dexterityField = new NumberField("Dexterity");
    private final NumberField constitutionField = new NumberField("Constitution");
    private final NumberField intelligenceField = new NumberField("Intelligence");
    private final NumberField wisdomField = new NumberField("Wisdom");
    private final NumberField charismaField = new NumberField("Charisma");
    private final ComboBox<Races> raceField = new ComboBox<>("Race");

    public PlayerCreationView(PlayerService playerService) {
        this.playerService = playerService;

        // Initialize raceField
        raceField.setItems(Races.values());

        // Bind fields
        binder.forField(nameField).bind(PlayerCreationDTO::getName, PlayerCreationDTO::setName);

        binder.forField(strengthField)
                .withConverter(new DoubleToIntegerConverter("Must enter a number"))
                .bind(PlayerCreationDTO::getStrength, PlayerCreationDTO::setStrength);

        binder.forField(dexterityField)
                .withConverter(new DoubleToIntegerConverter("Must enter a number"))
                .bind(PlayerCreationDTO::getDexterity, PlayerCreationDTO::setDexterity);

        binder.forField(constitutionField)
                .withConverter(new DoubleToIntegerConverter("Must enter a number"))
                .bind(PlayerCreationDTO::getConstitution, PlayerCreationDTO::setConstitution);

        binder.forField(intelligenceField)
                .withConverter(new DoubleToIntegerConverter("Must enter a number"))
                .bind(PlayerCreationDTO::getIntelligence, PlayerCreationDTO::setIntelligence);

        binder.forField(wisdomField)
                .withConverter(new DoubleToIntegerConverter("Must enter a number"))
                .bind(PlayerCreationDTO::getWisdom, PlayerCreationDTO::setWisdom);

        binder.forField(charismaField)
                .withConverter(new DoubleToIntegerConverter("Must enter a number"))
                .bind(PlayerCreationDTO::getCharisma, PlayerCreationDTO::setCharisma);

        binder.forField(raceField).bind(PlayerCreationDTO::getRace, PlayerCreationDTO::setRace);

        Button submitButton = new Button("Create Player", event -> createPlayer());

        // Add fields and button to the layout
        add(nameField, raceField, strengthField, dexterityField, constitutionField, intelligenceField, wisdomField, charismaField, submitButton);
    }

    private void createPlayer() {
        PlayerCreationDTO playerDTO = new PlayerCreationDTO();
        if (binder.writeBeanIfValid(playerDTO)) {
            // Call service to create player
            playerService.createPlayer(playerDTO);
            // Handle success or failure
        } else {
            // Handle validation errors
            throw new RuntimeException("Invalid input");
        }
    }
}


