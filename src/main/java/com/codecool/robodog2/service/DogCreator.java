package com.codecool.robodog2.service;

import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DogCreator {

    private static final Random RANDOM = new Random();
    private static final String[] LIST_OF_DOG_NAMES = {
            "Fergus",
            "Flynn",
            "Jesse",
            "Goofy",
            "Max",
            "Mickey",
            "Pumbaa",
            "Nemo",
            "Snoopy",
            "Tarzan",
            "Pongo"
    };

    public Dog createRandomDog() {
        return new Dog(generateBreed(), generateName(), generateAge());
    }

    private Breed generateBreed() {
        return Breed.values()[RANDOM.nextInt(Breed.values().length)];
    }

    private int generateAge() {
        final int ageUpperBound = 12;
        final int ageLowerBound = 1;
        return RANDOM.nextInt(ageUpperBound) + ageLowerBound;
    }

    private String generateName() {
        return LIST_OF_DOG_NAMES[RANDOM.nextInt(LIST_OF_DOG_NAMES.length)];
    }
}
