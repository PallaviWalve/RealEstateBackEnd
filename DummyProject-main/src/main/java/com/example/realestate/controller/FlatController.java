package com.example.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.realestate.entity.Flat;
import com.example.realestate.exception.CustomException;
import com.example.realestate.exception.FlatNotFoundExceptions;
import com.example.realestate.service.FlatService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/flats")
public class FlatController {

    @Autowired
    private FlatService flatService;

    @PostMapping("/register/{id}")
	public ResponseEntity<?> registerFlat(@RequestBody Flat flatRef, @PathVariable Long id) {
		return flatService.registerFlat(flatRef, id);
	}

    @GetMapping("/{id}")
    public ResponseEntity<Flat> getFlatById(@PathVariable Long id) {
        Flat flat = flatService.findById(id);
        return ResponseEntity.ok(flat);
    }

    @GetMapping("/get/{id}")
	public ResponseEntity<?> getAllFlats(@PathVariable Long id) {
		try {
			Flat flat = flatService.getOneFlat(id);
			return new ResponseEntity<>(flat, HttpStatus.OK);
		} catch (FlatNotFoundExceptions e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

    @GetMapping("/getflatbypropertyid/{id}")
	public ResponseEntity<?> getFlatByPropertyId(@PathVariable Long id){
		try {
			return new ResponseEntity<>(flatService.getOneFlat(id),HttpStatus.OK);
		}catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}catch (FlatNotFoundExceptions e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
    @PutMapping("/{id}")
    public ResponseEntity<Flat> updateFlat(@PathVariable Long id, @RequestBody Flat updatedFlat) {
        Flat flat = flatService.updateFlat(id, updatedFlat);
        return ResponseEntity.ok(flat);
    }

    @DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		return flatService.deleteRoom(id);
	}
}
