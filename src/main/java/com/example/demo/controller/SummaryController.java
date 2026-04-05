package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FinancialRecord;
import com.example.demo.model.RecordType;
import com.example.demo.repository.RecordRepository;

@RestController
@RequestMapping("/summary")
public class SummaryController {

    @Autowired
    private RecordRepository repo;

    // TOTAL INCOME
    @GetMapping("/total-income")
    public Double totalIncome() {
        return repo.findAll().stream()
                .filter(r -> r.getType() == RecordType.INCOME)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }

    // TOTAL EXPENSE
    @GetMapping("/total-expense")
    public Double totalExpense() {
        return repo.findAll().stream()
                .filter(r -> r.getType() == RecordType.EXPENSE)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }

    // NET BALANCE
    @GetMapping("/net-balance")
    public Double netBalance() {
        double income = totalIncome();
        double expense = totalExpense();
        return income - expense;
    }

    @GetMapping("/category-wise")
    public Map<String, Double> categoryWise() {

        Map<String, Double> result = new HashMap<>();

        for (FinancialRecord r : repo.findAll()) {
            result.put(
                r.getCategory(),
                result.getOrDefault(r.getCategory(), 0.0) + r.getAmount()
            );
        }

        return result;
    }
}