package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.PedigreeDAO;
import com.codecool.robodog2.model.Pedigree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedigreeService {

    private final PedigreeDAO pedigreeDAO;

    @Autowired
    public PedigreeService(PedigreeDAO pedigreeDAO) {
        this.pedigreeDAO = pedigreeDAO;
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
}
