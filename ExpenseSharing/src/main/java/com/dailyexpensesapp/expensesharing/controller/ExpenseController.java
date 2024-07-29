package com.dailyexpensesapp.expensesharing.controller;

import com.dailyexpensesapp.expensesharing.DTOs.ExpenseDTO;
import com.dailyexpensesapp.expensesharing.Service.ExpenseService;
import com.dailyexpensesapp.expensesharing.Service.UserService;
import com.dailyexpensesapp.expensesharing.entity.Expense;
import com.dailyexpensesapp.expensesharing.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<?> createExpense(@RequestBody ExpenseDTO expenseDTO) {
        try {
            Set<User> users = fetchUsersByUsernames(expenseDTO.getUsernames());
            Expense expense = expenseService.createExpense(expenseDTO, users);
            return ResponseEntity.ok(expense);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    private Set<User> fetchUsersByUsernames(List<String> usernames) {
        return usernames.stream()
                .map(userService::getUserByName)
                .collect(Collectors.toSet());
    }
}
