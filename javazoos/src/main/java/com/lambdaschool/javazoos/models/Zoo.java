package com.lambdaschool.javazoos.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zoos")
public class Zoo extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long zooid;

    @Column(nullable = false,
        unique = true)
    private String zooname;

    @OneToMany(mappedBy = "zoo",
        cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "zoos")
    private List<ZooAnimals> animals = new ArrayList<>();

    @OneToMany(mappedBy = "zoo",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "zoos")
    private List<Telephone> telephones = new ArrayList<>();

    public Zoo()
    {
    }

    public Zoo(String zooname)
    {
        this.zooname = zooname;
    }

    public long getZooid()
    {
        return zooid;
    }

    public void setZooid(long zooid)
    {
        this.zooid = zooid;
    }

    public String getZooname()
    {
        return zooname;
    }

    public void setZooname(String zooname)
    {
        this.zooname = zooname;
    }

    public List<Telephone> getTelephones()
    {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones)
    {
        this.telephones = telephones;
    }

    public List<ZooAnimals> getAnimals()
    {
        return animals;
    }

    public void setAnimals(List<ZooAnimals> animals)
    {
        this.animals = animals;
    }

    public void addAnimal(Animal animal)
    {
        animals.add(new ZooAnimals(this,
            animal));
    }
}
