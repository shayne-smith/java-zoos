package com.lambdaschool.javazoos.services;

import com.lambdaschool.javazoos.models.Animal;

import java.util.List;

public interface AnimalService
{

    List<Animal> findAll();

    Animal findAnimalById(long id);

    Animal save(Animal animal);

}
