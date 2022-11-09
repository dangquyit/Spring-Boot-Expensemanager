package com.junior.expensemanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExpenseNotFoundException extends RuntimeException {
    private String message;
}
