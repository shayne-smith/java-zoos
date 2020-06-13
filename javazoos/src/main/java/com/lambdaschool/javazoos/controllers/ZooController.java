package com.lambdaschool.javazoos.controllers;

import com.lambdaschool.javazoos.models.Zoo;
import com.lambdaschool.javazoos.services.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/zoos")
public class ZooController
{

    @Autowired
    private ZooService zooService;

    @GetMapping(value = "/zoos",
        produces = {"application/json"})
    public ResponseEntity<?> listAllZoos()
    {
        List<Zoo> myZoos = zooService.findAll();
        return new ResponseEntity<>(myZoos,
            HttpStatus.OK);
    }

    @GetMapping(value = "/zoo/{zooId}",
        produces = {"application/json"})
    public ResponseEntity<?> getZooById(
        @PathVariable
            long zooId)
    {
        Zoo z = zooService.findZooById(zooId);
        return new ResponseEntity<>(z,
            HttpStatus.OK);
    }

    @PostMapping(value = "/zoo",
        consumes = {"application/json"})
    public ResponseEntity<?> addNewZoo(
        @Valid
        @RequestBody
            Zoo newzoo) throws URISyntaxException
    {
        newzoo.setZooid(0);
        newzoo = zooService.save(newzoo);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{zooid}")
            .buildAndExpand(newzoo.getZooid())
            .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

    @PutMapping(value = "/zoo/{zooid}",
        consumes = {"application/json"})
    public ResponseEntity<?> updateFullZoo(
        @Valid
        @RequestBody
            Zoo updateZoo,
        @PathVariable
            long zooid)
    {
        updateZoo.setZooid(zooid);
        zooService.save(updateZoo);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/zoo/{zooid}",
        consumes = {"application/json"})
    public ResponseEntity<?> updateZoo(
        @RequestBody
            Zoo updateZoo,
        @PathVariable
            long id)
    {
        zooService.update(updateZoo,
            id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/zoo/{zooid}")
    public ResponseEntity<?> deleteZooById(
        @PathVariable
            long id)
    {
        zooService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
