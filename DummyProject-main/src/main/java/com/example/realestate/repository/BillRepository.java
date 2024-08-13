package com.example.realestate.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.realestate.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByUserRefId(Long userId);
}
