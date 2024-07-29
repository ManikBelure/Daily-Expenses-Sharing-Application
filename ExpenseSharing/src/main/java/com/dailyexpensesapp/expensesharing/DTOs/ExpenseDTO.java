package com.dailyexpensesapp.expensesharing.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

public class ExpenseDTO {

    @NotBlank
    private String description;

    @NotNull
    private Double amount;

    @NotBlank
    private String splitType; 

    @NotNull
    private List<String> usernames;

   
    private Map<String, Double> shares;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSplitType() {
        return splitType;
    }

    public void setSplitType(String splitType) {
        this.splitType = splitType;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    public Map<String, Double> getShares() {
        return shares;
    }

    public void setShares(Map<String, Double> shares) {
        this.shares = shares;
    }
}
