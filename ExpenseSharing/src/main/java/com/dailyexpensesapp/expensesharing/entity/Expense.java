package com.dailyexpensesapp.expensesharing.entity;

import jakarta.persistence.*;
import java.util.Map;
import java.util.Set;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String splitType; 

    @ManyToMany
    @JoinTable(name = "expense_users",
               joinColumns = @JoinColumn(name = "expense_id"),
               inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    @ElementCollection
    @MapKeyColumn(name = "user_name")
    @Column(name = "share")
    @CollectionTable(name = "expense_shares", joinColumns = @JoinColumn(name = "expense_id"))
    private Map<String, Double> shares;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Map<String, Double> getShares() {
        return shares;
    }

    public void setShares(Map<String, Double> shares) {
        this.shares = shares;
    }
}
