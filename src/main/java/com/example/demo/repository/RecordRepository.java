package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.FinancialRecord;
import com.example.demo.model.RecordType;

public interface RecordRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findByType(RecordType type);

    List<FinancialRecord> findByCategory(String category);
}