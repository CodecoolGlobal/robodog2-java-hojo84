package com.codecool.robodog2.dao;

import com.codecool.robodog2.model.Dog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DogMemDao implements DogDAO {

    private final List<Dog> dogs;

    public DogMemDao() {
        this.dogs = new ArrayList<>();
    }

    @Override
    public void addDog(Dog dog) {
        dog.setId(dogs.size());
        dogs.add(dog);
    }

    @Override
    public List<Dog> listDogs() {
        return dogs;
    }

    @Override
    public Dog getDog(long id) {
        return dogs.stream()
                .filter(dog -> dog.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    @Override
    public void updateDog(Dog dog, long id) {
        Dog dogToBeChanged = getDog(id);
        dogToBeChanged.setBreed(dog.getBreed());
        dogToBeChanged.setName(dog.getName());
        dogToBeChanged.setAge(dog.getAge());
    }

    @Override
    public void deleteDog(long id) {
        dogs.removeIf(dog -> dog.getId() == id);
    }

    @Override
    public long addDogAndReturnId(Dog dog) {
        dog.setId(dogs.size());
        dogs.add(dog);
        return dog.getId();
    }
}
