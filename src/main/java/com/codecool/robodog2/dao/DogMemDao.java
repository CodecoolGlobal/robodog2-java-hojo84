package com.codecool.robodog2.dao;

import com.codecool.robodog2.model.Dog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class DogMemDao implements DogDAO {

    private final List<Dog> dogs;
    private final AtomicLong idCounter;

    public DogMemDao() {
        this.idCounter = new AtomicLong(0);
        this.dogs = new ArrayList<>();
    }

    @Override
    public void addDog(Dog dog) {
        dog.setId(idCounter.incrementAndGet());
        dogs.add(dog);
    }

    @Override
    public List<Dog> listDogs() {
        return dogs;
    }

    @Override
    public Optional<Dog> getDog(long id) {
        return dogs.stream()
                .filter(dog -> dog.getId() == id)
                .findFirst();
    }

    @Override
    public void updateDog(Dog dog, long id) {
        Dog dogToBeChanged = getDog(id).orElseThrow();
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
        dog.setId(idCounter.incrementAndGet());
        dogs.add(dog);
        return dog.getId();
    }
}
