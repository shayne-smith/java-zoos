package com.lambdaschool.javazoos.services;

import com.lambdaschool.javazoos.models.Animal;
import com.lambdaschool.javazoos.models.Telephone;
import com.lambdaschool.javazoos.models.Zoo;
import com.lambdaschool.javazoos.models.ZooAnimals;
import com.lambdaschool.javazoos.repositories.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "zooService")
public class ZooServiceImpl implements ZooService
{

    @Autowired
    private ZooRepository zoorepos;

    @Autowired
    private AnimalService animalService;

    @Override
    public List<Zoo> findAll()
    {
        List<Zoo> list = new ArrayList<>();

        zoorepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Zoo findZooById(long id) throws EntityNotFoundException
    {
        return zoorepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Zoo id " + id + " not found!"));
    }

    @Override
    public Zoo save(Zoo zoo)
    {
        Zoo newZoo = new Zoo();

        if (zoo.getZooid() != 0)
        {
            Zoo oldZoo = zoorepos.findById(zoo.getZooid())
                .orElseThrow(() -> new EntityNotFoundException("Zoo id " + zoo.getZooid() + " not found!"));

            // delete animals for the old zoo
            for (ZooAnimals za : oldZoo.getAnimals())
            {
                deleteZooAnimal(za.getZoo()
                        .getZooid(),
                    za.getAnimal()
                        .getAnimalid());
            }
            newZoo.setZooid(zoo.getZooid());
        }

        newZoo.setZooname(zoo.getZooname()
            .toLowerCase());

        newZoo.getAnimals()
            .clear();
        if (zoo.getZooid() == 0)
        {
            for (ZooAnimals za : zoo.getAnimals())
            {
                Animal newAnimal = animalService.findAnimalById(za.getAnimal()
                    .getAnimalid());

                newZoo.addAnimal(newAnimal);
            }
        } else
        {
            for (ZooAnimals za : zoo.getAnimals())
            {
                addZooAnimal(za.getZoo()
                        .getZooid(),
                    za.getAnimal()
                        .getAnimalid());
            }
        }

        newZoo.getTelephones()
            .clear();
        for (Telephone t : zoo.getTelephones())
        {
            newZoo.getTelephones()
                .add(new Telephone(t.getPhonetype(),
                    t.getPhonenumber(),
                    newZoo));
        }

        return null;
    }

    @Override
    public Zoo update(
        Zoo zoo,
        long id)
    {
        return null;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        zoorepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Zoo id " + id + " not found!"));
        zoorepos.deleteById(id);
    }

    @Override
    public void deleteZooAnimal(
        long zooid,
        long animalid)
    {

    }

    @Override
    public void addZooAnimal(
        long zooid,
        long animalid)
    {

    }
}
