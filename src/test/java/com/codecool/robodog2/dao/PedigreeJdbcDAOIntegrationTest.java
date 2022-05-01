package com.codecool.robodog2.dao;

import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Use the schema built in the .sql file put in resources
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
@ComponentScan
@SpringBootTest
//As the context becomes dirty - database modifications happen, etc. - this method restores it to the original state
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PedigreeJdbcDAOIntegrationTest {

    @Autowired
    private PedigreeDAO pedigreeDAO;
    @Qualifier("dogJdbcDao")
    @Autowired
    DogDAO dogDAO;

    @Test
    public void testGetPedigreeByDogId() {
        Pedigree expected = new Pedigree();
        expected.setPuppyId(3);
        expected.setMomId(1);
        expected.setDadId(2);
        final Pedigree actual = pedigreeDAO.getPedigreeByPuppyId(3).orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetMomByDogId() {
        Dog expectedMom = new Dog(Breed.SPANIEL, "Lucy", 2);
        final Pedigree pedigreeByPuppyId = pedigreeDAO.getPedigreeByPuppyId(3).orElseThrow();
        final long momId = pedigreeByPuppyId.getMomId();
        final Dog actualMom = dogDAO.getDog(momId).orElseThrow();
        assertEquals(expectedMom, actualMom);
    }

    @Test
    public void testGetDadByDogId() {
        Dog expectedDad = new Dog(Breed.LABRADOR, "Molly", 1);
        final Pedigree pedigreeByPuppyId = pedigreeDAO.getPedigreeByPuppyId(3).orElseThrow();
        final long dadId = pedigreeByPuppyId.getDadId();
        final Dog actualDad = dogDAO.getDog(dadId).orElseThrow();
        assertEquals(expectedDad, actualDad);
    }

    @Test
    public void getAllSiblingsByDogId() {
        Pedigree newPedigree = new Pedigree();
        newPedigree.setPuppyId(4);
        newPedigree.setMomId(5);
        newPedigree.setDadId(2);
        pedigreeDAO.addPedigree(newPedigree);
        final List<Long> expectedSiblingIds = List.of(3L, 4L);
        final List<Long> actualSiblingIds = pedigreeDAO.getSiblingsByPuppyId(3);
        assertEquals(expectedSiblingIds, actualSiblingIds);
    }
}
