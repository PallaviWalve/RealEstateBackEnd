package com.example.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.realestate.entity.Flat;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {
    List<Flat> findByBhk(int bhk);

	List<Flat> findAllByIsAvailable(boolean isAvailable);
}
