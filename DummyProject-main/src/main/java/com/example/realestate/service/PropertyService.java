package com.example.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.realestate.entity.Property;
import com.example.realestate.exception.CustomException;
import com.example.realestate.repository.PropertyRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public ResponseEntity<?> registerProperty(Property propertyRef) {
		try {
			Property property = propertyRepository.save(propertyRef);
			return new ResponseEntity<>(property, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("unable to Register try again", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
    
    public Property getOneFlat(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + id));
    }

    public ResponseEntity<?> getAllProperty() {
		try {
			List<Property> propertyList = propertyRepository.findAll();
			if (propertyList.size() != 0) {
				return new ResponseEntity<>(propertyList, HttpStatus.OK);
			} else {
				throw new CustomException("no pg found");
			}
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
    
    public ResponseEntity<?> getPropertyByCity(String city) {
		try {
			List<Property> propertyList = propertyRepository.findByCity(city);
			if (propertyList.size() != 0) {
				return new ResponseEntity<>(propertyList, HttpStatus.OK);
			} else {
				throw new CustomException("no Property found");
			}
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
    
    
    public ResponseEntity<?> delete(Long id) {
		try {
			Property property = propertyRepository.findById(id).orElseThrow(() -> new CustomException("PG not found"));
			if (property != null) {
				propertyRepository.deleteById(id);
				return new ResponseEntity<>("Property deleted Successful", HttpStatus.OK);
			} else {
				throw new CustomException("Unable to delete");
			}
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

    public ResponseEntity<?> update(Property newProperty) {
		try {
			Property property = propertyRepository.findById(newProperty.getId()).orElseThrow(() -> new CustomException("PG not found"));
			if (property != null) {
				property.setAddress(newProperty.getAddress());
				property.setName(newProperty.getName());;
				property.setAmenities(newProperty.getAmenities());
				property.setBuilder(newProperty.getBuilder());
				property.setCity(newProperty.getCity());
				property.setDescription(newProperty.getDescription());
				property.setFlats(newProperty.getFlats());
				property.setPrice(newProperty.getPrice());
				property.setPropertyType(newProperty.getPropertyType());
				property.setState(newProperty.getState());
				property.setZipcode(newProperty.getZipcode());
				propertyRepository.save(property);
				return new ResponseEntity<>("info updated Successful", HttpStatus.OK);
			} else {
				throw new CustomException("Unable to update");
			}
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
}
