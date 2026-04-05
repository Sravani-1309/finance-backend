package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FinancialRecord;
import com.example.demo.model.RecordType;
import com.example.demo.model.Role;
import com.example.demo.service.RecordService;

@RestController
@RequestMapping("/records")
public class RecordController {

    @Autowired
    private RecordService service;

    // CREATE RECORD (WITH ROLE CHECK)
    @PostMapping
    public FinancialRecord createRecord(
            @RequestBody FinancialRecord record,
            @RequestHeader("role") Role role) {

        return service.createRecord(record, role);
    }

    // GET ALL RECORDS
    @GetMapping
    public List<FinancialRecord> getAllRecords() {
        return service.getAllRecords();
    }

    @GetMapping("/filter")
    public List<FinancialRecord> filter(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) RecordType type) {

        if (category != null) {
             return service.getByCategory(category);
        }

        if (type != null) {
            return service.getByType(type);
        }

    return service.getAllRecords();
    }

    @PutMapping("/{id}")
public FinancialRecord updateRecord(
        @PathVariable Long id,
        @RequestBody FinancialRecord record,
        @RequestHeader("role") Role role) {

    if (!Role.ADMIN.equals(role)) {
        throw new RuntimeException("Only ADMIN can update records");
    }

    record.setId(id);
    return service.save(record);
}

@DeleteMapping("/{id}")
public String deleteRecord(
        @PathVariable Long id,
        @RequestHeader("role") Role role) {

    if (!Role.ADMIN.equals(role)) {
        throw new RuntimeException("Only ADMIN can delete records");
    }

    service.delete(id);
    return "Deleted successfully";
}

@DeleteMapping("/delete-all")
public String deleteAll(@RequestHeader("role") Role role) {

    if (!Role.ADMIN.equals(role)) {
        throw new RuntimeException("Only ADMIN can delete all records");
    }

    service.deleteAll();
    return "All records deleted";
}
}