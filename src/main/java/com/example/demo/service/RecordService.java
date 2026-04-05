package com.example.demo.service;

import java.util.List;

import com.example.demo.model.FinancialRecord;
import com.example.demo.model.RecordType;
import com.example.demo.model.Role;

public interface RecordService {

    FinancialRecord createRecord(FinancialRecord record, Role role);
    FinancialRecord save(FinancialRecord record);
    void delete(Long id);
    void deleteAll();

    List<FinancialRecord> getByCategory(String category);
    List<FinancialRecord> getByType(RecordType type);
    List<FinancialRecord> getAllRecords();
}