package com.lambdaschool.javazoos.services;

import com.lambdaschool.javazoos.repositories.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "zooService")
public class ZooServiceImpl implements ZooService
{

    @Autowired
    private ZooRepository zoorepos;


}
