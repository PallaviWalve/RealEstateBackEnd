package com.example.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.realestate.entity.Flat;
import com.example.realestate.entity.Property;
import com.example.realestate.exception.CustomException;
import com.example.realestate.exception.FlatNotFoundExceptions;
import com.example.realestate.repository.FlatRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class FlatService {

    @Autowired
    private FlatRepository flatRepository;
    
    @Autowired 
    private PropertyService propertyServiceRef;

    public Flat getOneFlat(Long id) {
    	return flatRepository.findById(id).orElseThrow(() -> new FlatNotFoundExceptions("Flat Not Found"));
	
	}
    public ResponseEntity<?> registerFlat(Flat flatRef, Long id) {
		try {
			Property propertyReg = propertyServiceRef.getOneFlat(id);
			flatRef.setProperty(propertyReg);
			Flat flat = flatRepository.save(flatRef);
			if (flat != null)
				return new ResponseEntity<>(flat, HttpStatus.OK);
			else
				throw new CustomException("unable to register");
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

    

	public Flat findById(Long id) {
        return flatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flat not found with id: " + id));
    }

    public List<Flat> getAllFlat(){
		List<Flat> flats=flatRepository.findAllByIsAvailable(true);
		if(flats.isEmpty()) {
			throw new CustomException("Rooms are not available");
		}else {
			return flats;
		}
	}

    public String deleteRoom(Long id) {
		try {

			Flat flat = flatRepository.findById(id).orElseThrow(() -> new FlatNotFoundExceptions("Flat Not Found"));
			if (flat != null) {
				flatRepository.deleteById(id);
				return "Room deleted";
			} else {
				throw new CustomException("Unable to delete");
			}
		} catch (CustomException e) {
			return e.getMessage();
		} catch (FlatNotFoundExceptions e) {
			return e.getMessage();
		}
	}

    public Flat updateFlat(Long id, Flat updatedFlat) {
        Flat flat = findById(id);
        flat.setFlatNumber(updatedFlat.getFlatNumber());
        flat.setSize(updatedFlat.getSize());
        flat.setBhk(updatedFlat.getBhk());
        flat.setProperty(updatedFlat.getProperty());
        flat.setPrice(updatedFlat.getPrice());
        return flatRepository.save(flat);
    }
}
