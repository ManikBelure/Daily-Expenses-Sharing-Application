package com.dailyexpensesapp.expensesharing.repository;

import com.dailyexpensesapp.expensesharing.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
