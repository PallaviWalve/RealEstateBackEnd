package com.example.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.realestate.entity.Transaction;
import com.example.realestate.repository.TransactionRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: " + id));
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

//    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
//        Transaction transaction = findById(id);
//        transaction.setAmount(updatedTransaction.getAmount());
//        transaction.setTransactionDate(updatedTransaction.getTransactionDate());
//        transaction.setBooking(updatedTransaction.getBooking());
//        transaction.setStatus(updatedTransaction.getStatus());
//        return transactionRepository.save(transaction);
//    }
}
