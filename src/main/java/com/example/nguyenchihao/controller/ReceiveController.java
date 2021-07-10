package com.example.nguyenchihao.controller;

import com.example.nguyenchihao.entity.ReceiveEntity;
import com.example.nguyenchihao.service.ReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReceiveController {
    @Autowired
    private ReceiveService service;

    @GetMapping("/receives/{pageNo}/{pageSize}")
    public ResponseEntity<List<ReceiveEntity>> getAllReceives(@PathVariable int pageNo,@PathVariable int pageSize) {
        try {
            List<ReceiveEntity> receives = service.getAll(pageNo,pageSize);
            if (receives.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(receives, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/receives/{id}")
    public ResponseEntity<ReceiveEntity> getReceiveById(@PathVariable("id") Integer id) {
        Optional<ReceiveEntity> receive = service.findById(id);
        if (!receive.isPresent()) {
            return new ResponseEntity<>(receive.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(receive.get(), HttpStatus.OK);
    }

    @PostMapping("/receives")
    public ResponseEntity<ReceiveEntity> createReceive(@RequestBody ReceiveEntity receive, UriComponentsBuilder builder) {
        try {
            service.save(receive);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(builder.path("/persons/{id}").buildAndExpand(receive.getId()).toUri());
            return new ResponseEntity<>(receive, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/receives/{id}")
    public ResponseEntity<ReceiveEntity> updateReceive(@PathVariable("id") Integer id, @RequestBody ReceiveEntity receiveEntity) {
        Optional<ReceiveEntity> currentReceive = service.findById(id);
        if (!currentReceive.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentReceive.get().setName(receiveEntity.getName());
        currentReceive.get().setUnit(receiveEntity.getUnit());
        currentReceive.get().setQuantity(receiveEntity.getQuantity());
        currentReceive.get().setPrice(receiveEntity.getPrice());
        service.save(currentReceive.get());
        return new ResponseEntity<>(currentReceive.get(), HttpStatus.OK);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<ReceiveEntity> deletePerson(@PathVariable("id") Integer id) {
        Optional<ReceiveEntity> person = service.findById(id);
        if (!person.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
