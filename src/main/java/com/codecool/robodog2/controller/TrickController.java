package com.codecool.robodog2.controller;

import com.codecool.robodog2.model.Trick;
import com.codecool.robodog2.service.TrickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trick")
public class TrickController {

    private final TrickService trickService;

    @Autowired
    public TrickController(TrickService trickService) {
        this.trickService = trickService;
    }

    @PostMapping
    public void addTrick(@RequestBody Trick trick) {
        trickService.addTrick(trick);
    }

    @GetMapping
    public List<Trick> listTricks() {
        return trickService.listTricks();
    }

    @GetMapping("/{id}")
    public Trick getTrick(@PathVariable long id) {
        return trickService.getTrick(id);
    }

    @PutMapping("/{id}")
    public void updateTrick(@RequestBody Trick trick, @PathVariable long id) {
        trickService.updateTrick(trick, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTrick(@PathVariable long id) {
        trickService.deleteTrick(id);
    }
}
