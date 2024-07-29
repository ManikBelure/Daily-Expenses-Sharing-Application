package com.dailyexpensesapp.expensesharing.repository;

import com.dailyexpensesapp.expensesharing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByMobileNumber(String mobileNumber);
    
    Optional<User> findByName(String name); 
}
