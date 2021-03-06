package com.codecool.robodog2.controller;

import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import com.codecool.robodog2.service.PedigreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dog")
public class PedigreeController {

    private final PedigreeService pedigreeService;

    @Autowired
    public PedigreeController(PedigreeService pedigreeService) {
        this.pedigreeService = pedigreeService;
    }

    @GetMapping("/{puppyId}/pedigree")
    public Pedigree getPedigreeByPuppyId(@PathVariable long puppyId) {
        return pedigreeService.getPedigreeByPuppyId(puppyId);
    }

    @PostMapping("/{puppyId}/pedigree")
    public void addPedigreeByPuppyId(@PathVariable long puppyId, @RequestBody Pedigree pedigree) {
        pedigreeService.addPedigreeByPuppyId(puppyId, pedigree);
    }

    @GetMapping("/puppy")
    public Dog addPedigreeWithNewlyCreatedPuppy(@RequestBody Pedigree parentsId) {
        return pedigreeService.addPedigreeWithNewlyCreatedPuppy(parentsId);
    }

    @GetMapping("/{puppyId}/pedigree/mom")
    public Dog getParentDogByMomId(@PathVariable long puppyId) {
        return pedigreeService.getParentDogByMomId(puppyId);
    }

    @GetMapping("/{puppyId}/pedigree/dad")
    public Dog getParentDogByDadId(@PathVariable long puppyId) {
        return pedigreeService.getParentDogByDadId(puppyId);
    }

    @GetMapping("/{puppyId}/pedigree/siblings")
    public List<Dog> getSiblingsByPuppyId(@PathVariable long puppyId) {
        return pedigreeService.getSiblingsByPuppyId(puppyId);
    }
}
