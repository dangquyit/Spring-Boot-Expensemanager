package com.junior.expensemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseFilterDTO {
    private String keyword;

    private String sortBy;

    private String fromDate;;

    private String toDate;
}
