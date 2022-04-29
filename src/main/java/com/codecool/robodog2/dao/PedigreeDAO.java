package com.codecool.robodog2.dao;

import com.codecool.robodog2.model.Pedigree;

import java.util.List;
import java.util.Optional;

public interface PedigreeDAO {

    void addPedigree(Pedigree pedigree);

    List<Pedigree> listPedigrees();

    Optional<Pedigree> getPedigree(long id);

    void updatePedigree(Pedigree pedigree, long id);

    void deletePedigree(long id);
}
