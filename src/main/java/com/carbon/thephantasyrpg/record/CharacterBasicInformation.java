package com.carbon.thephantasyrpg.record;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.formlayout.FormLayout;

/**
 * Record to hold the basic information section of the player creation view
 */
public record CharacterBasicInformation(Accordion accordion, FormLayout characterBasicInformationFormLayout) {
}
