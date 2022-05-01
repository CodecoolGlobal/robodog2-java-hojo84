package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.DogDAO;
import com.codecool.robodog2.dao.PedigreeDAO;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DogServiceTest {

    @MockBean
    private DogCreator dogCreatorMock;

    @Qualifier("dogJdbcDao")
    @MockBean
    private DogDAO dogDaoMock;

    @MockBean
    private PedigreeDAO pedigreeDaoMock;

    @Autowired
    private PedigreeService pedigreeService;

    @Test
    public void testCreateNewPuppy() {
        Dog newPuppy = new Dog(Breed.BULLDOG, "Alex", 3);
        when(dogCreatorMock.createRandomDog()).thenReturn(newPuppy);
        Pedigree parents = new Pedigree();
        parents.setMomId(3);
        parents.setDadId(4);
        Dog dad = new Dog(Breed.VIZSLA, "Tango", 2);
        Dog mom = new Dog(Breed.SPANIEL, "Molly", 4);
        when(dogDaoMock.getDog(4)).thenReturn(Optional.of(dad));
        when(dogDaoMock.getDog(3)).thenReturn(Optional.of(mom));
        when(dogDaoMock.addDogAndReturnId(any(Dog.class))).thenReturn(5L);
        final Dog actualNewPuppy = pedigreeService.addPedigreeWithNewlyCreatedPuppy(parents);
        List<Breed> expectedPuppyBreed = List.of(Breed.VIZSLA, Breed.SPANIEL);
        assertEquals("Alex", actualNewPuppy.getName());
        assertEquals(0, actualNewPuppy.getAge());
        assertTrue(expectedPuppyBreed.contains(actualNewPuppy.getBreed()));
        assertEquals(5L, parents.getPuppyId());
    }
}
