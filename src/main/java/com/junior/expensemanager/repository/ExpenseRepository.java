package com.junior.expensemanager.repository;

import com.junior.expensemanager.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByNameContainingAndDateBetween(String name, Date fromDate, Date toDate);
}
