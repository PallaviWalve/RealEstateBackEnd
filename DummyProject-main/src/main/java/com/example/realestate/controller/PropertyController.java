package com.example.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.realestate.entity.Property;
import com.example.realestate.service.PropertyService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/register")
    public ResponseEntity<?> registerProperty(@RequestBody Property property){
		  return propertyService.registerProperty(property);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Property property = propertyService.getOneFlat(id);
        return ResponseEntity.ok(property);
    }

    @GetMapping("/getAllPropertyByCity/{city}")
	  public ResponseEntity<?> getAllPgByLocation(@PathVariable String city){
		  return propertyService.getPropertyByCity(city);
	  }
	  
    @GetMapping("/getAllProperties")
    public ResponseEntity<?> getAllProperties() {
    	return propertyService.getAllProperty();
    }

    @PutMapping("/update")
	  public ResponseEntity<?> update(@RequestBody Property property){
		  return propertyService.update(property);
	  }
	  
	  @DeleteMapping("/delete/{id}")
	  public ResponseEntity<?> delete(@PathVariable  Long id){
		  return propertyService.delete(id);
	  }
}
