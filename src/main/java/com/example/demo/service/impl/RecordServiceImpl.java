package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.FinancialRecord;
import com.example.demo.model.RecordType;
import com.example.demo.model.Role;
import com.example.demo.repository.RecordRepository;
import com.example.demo.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository repo;

    @Override
    public FinancialRecord createRecord(FinancialRecord record, Role role) {
        
        //  ACCESS CONTROL
        if (!Role.ADMIN.equals(role)) {
            throw new RuntimeException("Access Denied: Only ADMIN can create records");
        }

        //  VALIDATION
        if (record.getAmount() == null || record.getAmount() <= 0) {
            throw new RuntimeException("Invalid amount");
        }

        if (record.getType() == null) {
            throw new RuntimeException("Type cannot be null");
        }

        if (record.getUserId() == null) {
            throw new RuntimeException("UserId is required");
        }

        return repo.save(record);
    }

    @Override
    public List<FinancialRecord> getByCategory(String category) {
        return repo.findByCategory(category);
    }

    @Override
    public List<FinancialRecord> getByType(RecordType type) {
        return repo.findByType(type);
    }

    @Override
public List<FinancialRecord> getAllRecords() {
    return repo.findAll();
}

@Override
public FinancialRecord save(FinancialRecord record) {
    return repo.save(record);
}

@Override
public void delete(Long id) {
    repo.deleteById(id);
}
    
@Override
public void deleteAll() {
    repo.deleteAll();
}
}