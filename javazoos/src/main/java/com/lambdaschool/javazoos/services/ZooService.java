package com.lambdaschool.javazoos.services;

import com.lambdaschool.javazoos.models.Zoo;

import java.util.List;

public interface ZooService
{

    List<Zoo> findAll();

    Zoo findZooById(long id);

    Zoo save(Zoo zoo);

    Zoo update(
        Zoo zoo,
        long id);

    void delete(long id);

    void deleteZooAnimal(
        long zooid,
        long animalid);

    void addZooAnimal(
        long zooid,
        long animalid);

}
