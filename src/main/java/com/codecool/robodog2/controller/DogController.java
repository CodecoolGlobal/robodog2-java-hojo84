package com.codecool.robodog2.controller;

import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dog")
public class DogController {

    private final DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping
    public void addDog(@RequestBody Dog dog) {
        dogService.addDog(dog);
    }

    @GetMapping
    public List<Dog> listDogs() {
        return dogService.listDogs();
    }

    @GetMapping("/{dog_id}")
    public Dog getDog(@PathVariable(name = "dog_id") long id) {
        return dogService.getDog(id);
    }

    @PutMapping("/{dog_id}")
    public void updateDog(@RequestBody Dog dog, @PathVariable(name = "dog_id") long id) {
        dogService.updateDog(dog, id);
    }

    @DeleteMapping("/{dog_id}")
    public void deleteDog(@PathVariable(name = "dog_id") long id) {
        dogService.deleteDog(id);
    }

    @PostMapping("/returnId")
    public long addDogAndReturnId(@RequestBody Dog dog) {
        return dogService.addDogAndReturnId(dog);
    }

    @GetMapping("/random")
    public Dog getRandomlyCreatedDog() {
        return dogService.getRandomlyCreatedDog();
    }
}
