package com.nelkinda.training;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL
}

class Expense {
    ExpenseType type;
    int amount;
    String name = "NOT_DEFINED";

    public Boolean isMeal() {
        return (this.type != ExpenseType.CAR_RENTAL);
    }
}

public class ExpenseReport {
    public void printReport(List<Expense> expenses) {
        printReport(expenses, new Date().toString());
    }
    public void printReport(List<Expense> expenses, String date) {
        int total = 0;
        int mealExpenses = 0;

        System.out.println("Expenses " + date);

        for (Expense expense : expenses) {
            if (expense.isMeal()){
                mealExpenses += expense.amount;
            }

            String mealOverExpensesMarker = getMealOverExpensesMarker(expense);

            System.out.println(expense.name + "\t" + expense.amount + "\t" + mealOverExpensesMarker);

            total += expense.amount;
        }

        System.out.println("Meal expenses: " + mealExpenses);
        System.out.println("Total expenses: " + total);
    }

    private String getMealOverExpensesMarker(Expense expense) {
        return expense.type == ExpenseType.DINNER && expense.amount > 5000 || expense.type == ExpenseType.BREAKFAST && expense.amount > 1000 ? "X" : " ";
    }

}
