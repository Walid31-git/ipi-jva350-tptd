package com.ipi.jva350.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.Assertions;

public class SalarieAideADomicileTest {

    @Test
    public void testALegalementDroitADesCongesPayesRight() {
        // Given : salarié ayant suffisamment de jours travaillés
        SalarieAideADomicile unSalarie = new SalarieAideADomicile();
        unSalarie.setJoursTravaillesAnneeNMoins1(11);
        Assertions.assertNotNull(unSalarie);

        // When : on vérifie le droit aux congés payés
        Boolean droitCongesPayesTrue = unSalarie.aLegalementDroitADesCongesPayes();

        // Then : le salarié doit avoir droit
        Assertions.assertEquals(true, droitCongesPayesTrue);
    }

    @Test
    public void testALegalementDroitADesCongesPayesWrong() {
        // Given : salarié ayant moins de jours travaillés
        SalarieAideADomicile unSalarie = new SalarieAideADomicile();
        unSalarie.setJoursTravaillesAnneeNMoins1(9);
        Assertions.assertNotNull(unSalarie);

        // When
        Boolean droitCongesPayesTrue = unSalarie.aLegalementDroitADesCongesPayes();

        // Then : le salarié n’a pas droit
        Assertions.assertEquals(false, droitCongesPayesTrue);
    }

    @ParameterizedTest
    @CsvSource({
            "2025-11-01,2025-12-01"
    })
    void testCalculeJoursDeCongeDecomptesPourPlageRight(String debut, String fin) {
        // Given : salarié et plage de congés
        SalarieAideADomicile unSalarie = new SalarieAideADomicile();
        LocalDate dateDebut = LocalDate.parse(debut);
        LocalDate dateFin = LocalDate.parse(fin);

        // When : calcul des jours de congés
        LinkedHashSet<LocalDate> joursDeConges =
                unSalarie.calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin);

        // Then : vérification du nombre attendu
        Assertions.assertEquals(24, joursDeConges.size());
    }

    @ParameterizedTest
    @CsvSource({
            "2025-11-01,2025-12-01"
    })
    void testCalculeJoursDeCongeDecomptesPourPlageWrong(String debut, String fin) {
        // Given : salarié et plage de congés
        SalarieAideADomicile unSalarie = new SalarieAideADomicile();
        LocalDate dateDebut = LocalDate.parse(debut);
        LocalDate dateFin = LocalDate.parse(fin);

        // When
        LinkedHashSet<LocalDate> joursDeConges =
                unSalarie.calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin);

        // Then : vérifier que ce n’est pas un nombre incorrect
        Assertions.assertNotEquals(20, joursDeConges.size());
    }

    @ParameterizedTest
    @CsvSource({
            "2025-11-01,2025-12-01,24"
    })
    public void testCalculeJoursDeCongeDecomptesPourPlage(String debut, String fin, int nbJoursDeConges) {
        // Given : salarié et plage de congés
        SalarieAideADomicile unSalarie = new SalarieAideADomicile();
        LocalDate dateDebut = LocalDate.parse(debut);
        LocalDate dateFin = LocalDate.parse(fin);

        // When
        LinkedHashSet<LocalDate> joursDeConges =
                unSalarie.calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin);

        // Then : le nombre de jours doit correspondre
        Assertions.assertEquals(nbJoursDeConges, joursDeConges.size());
    }

    @Test
    public void testFindByNom() {
        // Given : à compléter selon la logique de repository/service
        // When
        // Then
    }
}