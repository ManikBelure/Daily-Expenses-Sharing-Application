package com.dailyexpensesapp.expensesharing.Service;

import com.dailyexpensesapp.expensesharing.DTOs.ExpenseDTO;
import com.dailyexpensesapp.expensesharing.entity.Expense;
import com.dailyexpensesapp.expensesharing.entity.User;
import com.dailyexpensesapp.expensesharing.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashMap;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;

    public Expense createExpense(ExpenseDTO expenseDTO, Set<User> users) {
        if (users == null || users.isEmpty()) {
            throw new IllegalArgumentException("User list cannot be null or empty.");
        }

        Expense expense = new Expense();
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setUsers(users);
        expense.setSplitType(expenseDTO.getSplitType()); // Ensure splitType is set

        String splitType = expenseDTO.getSplitType();
        if (splitType == null || splitType.isEmpty()) {
            throw new IllegalArgumentException("Split type must be provided.");
        }

        switch (splitType.toLowerCase()) {
            case "equal":
                setEqualShares(expense, users);
                break;
            case "exact":
                setExactShares(expense, expenseDTO.getUsernames());
                break;
            case "percentage":
                setPercentageShares(expense, expenseDTO.getShares(), expenseDTO.getAmount());
                break;
            default:
                throw new IllegalArgumentException("Invalid split type: " + splitType);
        }

        return expenseRepository.save(expense);
    }

    private void setEqualShares(Expense expense, Set<User> users) {
        double share = expense.getAmount() / users.size();
        expense.setShares(users.stream()
                .collect(Collectors.toMap(User::getName, user -> share)));
    }

    private void setExactShares(Expense expense, List<String> usernames) {
        // Implement logic to set exact shares based on usernames and user input.
        throw new UnsupportedOperationException("Exact share logic not yet implemented.");
    }

    private void setPercentageShares(Expense expense, Map<String, Double> percentageShares, Double totalAmount) {
        double totalPercentage = percentageShares.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        if (totalPercentage != 100.0) {
            throw new IllegalArgumentException("Total percentage must be equal to 100.");
        }

        // Calculate shares based on the provided percentages
        Map<String, Double> calculatedShares = new HashMap<>();
        for (Map.Entry<String, Double> entry : percentageShares.entrySet()) {
            String username = entry.getKey();
            Double percentage = entry.getValue();
            if (percentage < 0 || percentage > 100) {
                throw new IllegalArgumentException("Percentage value out of range: " + percentage);
            }
            Double shareAmount = (percentage / 100) * totalAmount;
            calculatedShares.put(username, shareAmount);
        }

        expense.setShares(calculatedShares);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
}
