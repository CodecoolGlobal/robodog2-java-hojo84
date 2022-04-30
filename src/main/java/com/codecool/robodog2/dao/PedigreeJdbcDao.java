package com.codecool.robodog2.dao;

import com.codecool.robodog2.dao.mapper.PedigreeMapper;
import com.codecool.robodog2.model.Pedigree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        String sql = "INSERT INTO pedigree(puppy_id, mom_id, dad_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, pedigree.getPuppyId(), pedigree.getMomId(), pedigree.getDadId());
    }

    @Override
    public List<Pedigree> listPedigrees() {
        String sql = "SELECT id, puppy_id, mom_id, dad_id FROM pedigree";
        return jdbcTemplate.query(sql, pedigreeMapper);
    }

    @Override
    public Optional<Pedigree> getPedigree(long id) {
        String sql = "SELECT id, puppy_id, mom_id, dad_id FROM pedigree WHERE id=?";
        Pedigree pedigree = null;
        try {
            pedigree = jdbcTemplate.queryForObject(sql, pedigreeMapper, id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(pedigree);
    }

    @Override
    public void updatePedigree(Pedigree pedigree, long id) {
        String sql = "UPDATE pedigree\n" +
                "SET puppy_id=?,\n" +
                "    mom_id=?,\n" +
                "    dad_id=?\n" +
                "WHERE id = ?";
        jdbcTemplate.update(sql, pedigree.getPuppyId(), pedigree.getMomId(), pedigree.getDadId(), id);
    }

    @Override
    public void deletePedigree(long id) {
        String sql = "DELETE FROM pedigree WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Pedigree> getPedigreeByPuppyId(long id) {
        String sql = "SELECT id, puppy_id, mom_id, dad_id FROM pedigree WHERE puppy_id=?";
        Pedigree pedigree = null;
        try {
            pedigree = jdbcTemplate.queryForObject(sql, pedigreeMapper, id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(pedigree);
    }
}
