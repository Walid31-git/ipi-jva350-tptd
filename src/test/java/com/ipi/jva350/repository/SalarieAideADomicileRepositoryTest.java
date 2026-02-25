package com.ipi.jva350.repository;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.BeforeEach;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SalarieAideADomicileRepositoryTest {

    @Autowired
    private SalarieAideADomicileRepository repository; // injection du repository

    @BeforeEach
    public void before() {
        repository.deleteAll();
    }
    @Test
    void testFindByNom_shouldReturnSalarie() {
        // Création et sauvegarde d'un salarié
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom("Dupont");
        repository.save(salarie);

        // Recherche du salarié par nom
        SalarieAideADomicile result = repository.findByNom("Dupont");

        // Vérification
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Dupont", result.getNom());
    }

    @Test
    void testFindByNom_shouldReturnNull() {
        // Création et sauvegarde d'un salarié
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom("Guetôme");
        repository.save(salarie);

        // Recherche d'un nom inexistant
        SalarieAideADomicile result = repository.findByNom("Gautier");

        // Vérification : aucun salarié trouvé
        Assertions.assertNull(result);
    }
    @Test
    public void testPartCongesPrisTotauxAnneeNMoins1() {
        // GIVEN : création de deux salariés avec congés
        SalarieAideADomicile salarie1 = new SalarieAideADomicile();
        salarie1.setNom("Dupont");
        salarie1.setCongesPayesPrisAnneeNMoins1(10.0);
        salarie1.setCongesPayesAcquisAnneeNMoins1(20.0);

        SalarieAideADomicile salarie2 = new SalarieAideADomicile();
        salarie2.setNom("Durand");
        salarie2.setCongesPayesPrisAnneeNMoins1(5.0);
        salarie2.setCongesPayesAcquisAnneeNMoins1(15.0);

        repository.save(salarie1);
        repository.save(salarie2);

        // WHEN : on calcule la part de congés pris
        Double result = repository.partCongesPrisTotauxAnneeNMoins1();

        // THEN : comparaison avec tolérance pour les nombres à virgule
        Double attendu = 15.0 / 35.0;
        Assertions.assertEquals(attendu, result, 0.0001);
    }
}