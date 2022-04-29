package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.PedigreeMapper;
import com.codecool.robodog2.model.Pedigree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PedigreeJdbcDao implements PedigreeDAO {

    private final JdbcTemplate jdbcTemplate;
    private final PedigreeMapper pedigreeMapper;

    @Autowired
    public PedigreeJdbcDao(JdbcTemplate jdbcTemplate, PedigreeMapper pedigreeMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.pedigreeMapper = pedigreeMapper;
    }

    @Override
    public void addPedigree(Pedigree pedigree) {

    }

    @Override
    public List<Pedigree> listPedigrees() {
        return null;
    }

    @Override
    public Optional<Pedigree> getPedigree(long id) {
        return Optional.empty();
    }

    @Override
    public void updatePedigree(Pedigree pedigree, long id) {

    }

    @Override
    public void deletePedigree(long id) {

    }
}
