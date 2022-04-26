package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.DogDAO;
import com.codecool.robodog2.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogService {

    private final DogDAO dogDAO;

    @Autowired
    public DogService(DogDAO dogDAO) {
        this.dogDAO = dogDAO;
    }

    public void addDog(Dog dog) {
        dogDAO.addDog(dog);
    }

    public List<Dog> listDogs() {
        return dogDAO.listDogs();
    }

    public Dog getDog(long id) {
        return dogDAO.getDog(id).orElseThrow();
    }

    public void updateDog(Dog dog, long id) {
        dogDAO.updateDog(dog, id);
    }

    public void deleteDog(long id) {
        dogDAO.deleteDog(id);
    }

    public long addDogAndReturnId(Dog dog) {
        return dogDAO.addDogAndReturnId(dog);
    }
}
