package com.lambdaschool.javazoos.services;

import com.lambdaschool.javazoos.models.Animal;
import com.lambdaschool.javazoos.repositories.AnimalRepository;
import com.lambdaschool.javazoos.repositories.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "animalService")
public class AnimalServiceImpl implements AnimalService
{

    @Autowired
    AnimalRepository animalrepos;

    @Autowired
    ZooRepository zoorepos;

    @Override
    public List<Animal> findAll()
    {
        List<Animal> list = new ArrayList<>();

        animalrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Animal findAnimalById(long id)
    {
        return animalrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Animal id " + id + " not found!"));
    }

    @Override
    public Animal save(Animal animal)
    {
        if (animal.getZoos()
            .size() > 0)
        {
            throw new EntityExistsException("Zoo Animals are not updated through Animal.");
        }

        return animalrepos.save(animal);
    }
}
