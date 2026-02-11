package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SalarieAideADomicileTest {
    @Test
    public void testaLegalementDroitADesCongesPayes () {
        //Given
        SalarieAideADomicile aide = new SalarieAideADomicile();
        aide.setJoursTravaillesAnneeNMoins1(11);
        // WHEN
        boolean result = aide.aLegalementDroitADesCongesPayes();

        System.out.println(result);
        // THEN
        Assertions.assertTrue(result, "Result correct : 11 jours travaillés");
    }
    @Test
    public void testaLegalementDroitADesCongesPayesFalse() {
        // GIVEN
        SalarieAideADomicile aide = new SalarieAideADomicile();
        aide.setJoursTravaillesAnneeNMoins1(9);
        // WHEN
        boolean result = aide.aLegalementDroitADesCongesPayes();

        System.out.println(result);
        // THEN
        Assertions.assertFalse(result, "Le résultat devrait être faux pour 9 jours travaillés");
    }
}
