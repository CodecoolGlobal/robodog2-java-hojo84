package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.DogDAO;
import com.codecool.robodog2.dao.PedigreeDAO;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedigreeService {

    private final PedigreeDAO pedigreeDAO;
    private final DogCreator dogCreator;
    private final DogDAO dogDAO;

    @Autowired
    public PedigreeService(PedigreeDAO pedigreeDAO, DogCreator dogCreator, @Qualifier("dogJdbcDao") DogDAO dogDAO) {
        this.pedigreeDAO = pedigreeDAO;
        this.dogCreator = dogCreator;
        this.dogDAO = dogDAO;
    }

    public void addPedigree(Pedigree pedigree) {
        pedigreeDAO.addPedigree(pedigree);
    }

    public List<Pedigree> listPedigrees() {
        return pedigreeDAO.listPedigrees();
    }

    public Pedigree getPedigree(long id) {
        return pedigreeDAO.getPedigree(id).orElseThrow();
    }

    public void updatePedigree(Pedigree pedigree, long id) {
        pedigreeDAO.updatePedigree(pedigree, id);
    }

    public void deletePedigree(long id) {
        pedigreeDAO.deletePedigree(id);
    }

    public Pedigree getPedigreeByPuppyId(long id) {
        return pedigreeDAO.getPedigreeByPuppyId(id).orElseThrow();
    }

    public void addPedigreeByPuppyId(long id, Pedigree pedigree) {
        pedigree.setPuppyId(id);
        pedigreeDAO.addPedigree(pedigree);
    }

    public Dog addPedigreeWithNewlyCreatedPuppy(Pedigree parents) {
        final int newPuppyAge = 0;
        Dog newPuppy = dogCreator.createRandomDog();
        newPuppy.setAge(newPuppyAge);
        final Dog dad = dogDAO.getDog(parents.getDadId()).orElseThrow();
        final Dog mom = dogDAO.getDog(parents.getMomId()).orElseThrow();
        final double equalChance = 0.5;
        if (Math.random() > equalChance) {
            newPuppy.setBreed(dad.getBreed());
        } else {
            newPuppy.setBreed(mom.getBreed());
        }
        final long newPuppyId = dogDAO.addDogAndReturnId(newPuppy);
        parents.setPuppyId(newPuppyId);
        pedigreeDAO.addPedigree(parents);
        return newPuppy;
    }

    public Dog getParentDogByMomId(long puppyId) {
        final Pedigree pedigreeByPuppyId = pedigreeDAO.getPedigreeByPuppyId(puppyId).orElseThrow();
        final long momId = pedigreeByPuppyId.getMomId();
        return dogDAO.getDog(momId).orElseThrow();
    }

    public Dog getParentDogByDadId(long puppyId) {
        final Pedigree pedigreeByPuppyId = pedigreeDAO.getPedigreeByPuppyId(puppyId).orElseThrow();
        final long dadID = pedigreeByPuppyId.getDadId();
        return dogDAO.getDog(dadID).orElseThrow();
    }
}
