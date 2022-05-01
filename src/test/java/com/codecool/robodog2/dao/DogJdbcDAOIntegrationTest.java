package com.codecool.robodog2.dao;

import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JdbcTest
//Use the schema built in this .sql file put in resources
@Sql(scripts = {"classpath:schema.sql"})
@ComponentScan
//@SpringBootTest
//As the context becomes dirty - database modifications happen, etc. - this method restores it to the original state
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DogJdbcDAOIntegrationTest {

    @Qualifier("dogJdbcDao")
    @Autowired
    DogDAO dogDAO;

    @Test
    public void testAddOneDog() {
        final Dog newDog = new Dog(Breed.BULLDOG, "Baltazar", 3);
        dogDAO.addDog(newDog);
        final List<Dog> dogs = dogDAO.listDogs();
        assertEquals(1, dogs.size());
        assertTrue(dogs.contains(newDog));
    }

    @Test
    public void testGetAllDogs() {
        final List<Dog> expected = List.of(
                new Dog(Breed.BULLDOG, "Baltazar", 3),
                new Dog(Breed.SPANIEL, "Lucy", 7),
                new Dog(Breed.VIZSLA, "Nemo", 1)
        );
        expected.forEach(dogDAO::addDog);
        List<Dog> actualDogs = dogDAO.listDogs();
        assertEquals(3, actualDogs.size());
        assertEquals(expected, actualDogs);
    }

    @Test
    public void getDogById() {
        final List<Dog> expected = List.of(
                new Dog(Breed.BULLDOG, "Baltazar", 3),
                new Dog(Breed.SPANIEL, "Lucy", 7),
                new Dog(Breed.VIZSLA, "Nemo", 1)
        );
        expected.forEach(dogDAO::addDog);
        assertEquals("Lucy", dogDAO.getDog(2).orElseThrow().getName());
    }

    @Test
    public void testUpdateDog() {
        final Dog newDog = new Dog(Breed.BULLDOG, "Baltazar", 3);
        dogDAO.addDog(newDog);
        dogDAO.updateDog(new Dog(Breed.GOLDEN_RETRIEVER, "Ubul", 10), 1);
        assertEquals("Ubul", dogDAO.getDog(1).orElseThrow().getName());
        assertEquals(Breed.GOLDEN_RETRIEVER, dogDAO.getDog(1).orElseThrow().getBreed());
    }

    @Test
    public void deleteDog() {
        final Dog newDog = new Dog(Breed.BULLDOG, "Baltazar", 3);
        dogDAO.addDog(newDog);
        assertEquals(1, dogDAO.listDogs().size());
        dogDAO.deleteDog(1);
        assertEquals(0, dogDAO.listDogs().size());
    }
}
