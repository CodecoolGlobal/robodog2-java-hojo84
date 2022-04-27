package com.codecool.robodog2.dao;

import com.codecool.robodog2.model.Trick;

import java.util.List;
import java.util.Optional;

public interface TrickDAO {

    void addTrick(Trick trick);

    List<Trick> listTricks();

    Optional<Trick> getTrick(long id);

    void updateTrick(Trick trick, long id);

    void deleteTrick(long id);
}
