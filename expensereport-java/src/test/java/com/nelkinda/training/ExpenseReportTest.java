package com.nelkinda.training;


import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseReportTest {

    private final ByteArrayOutputStream interceptedSystemOut = new ByteArrayOutputStream();
    private final String date = "Sat Sep 24 10:15:30 IST 2022";

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(interceptedSystemOut));
    }

    @AfterEach
    public void teardown() {
        System.setOut(System.out);
    }

    @Test
    void withNoExpenses() {
        ExpenseReport expenseReport = new ExpenseReport();
        List<Expense> expenseList = new ArrayList<>();
        expenseReport.printReport(expenseList, date);
        String expected = String.format(
                "Expenses %s%n" +
                        "Meal expenses: 0%n" +
                        "Total expenses: 0%n",
                date
        );
        assertEquals(String.format(expected, date), interceptedSystemOut.toString());
    }

    @Test
    void withOneExpense() {
        ExpenseReport expenseReport = new ExpenseReport();
        Expense carRental = new Expense();
        carRental.amount = 1000;
        carRental.type = ExpenseType.CAR_RENTAL;
        carRental.name = "Car Rental";
        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(carRental);
        expenseReport.printReport(expenseList,date);
        String expected = String.format(
                "Expenses %s%n" +
                        "Car Rental\t1000\t %n"+
                        "Meal expenses: 0%n" +
                        "Total expenses: 1000%n",
                date
        );
        assertEquals(String.format(expected, date).translateEscapes(), interceptedSystemOut.toString());
    }

    @Test
    void withManyExpenses() {
        List<Expense> expenseList = new ArrayList<>();

        Expense dinner = new Expense();
        dinner.type = ExpenseType.DINNER;
        dinner.amount = 5001;
        dinner.name = "Dinner";

        expenseList.add(dinner);

        Expense breakfast = new Expense();
        breakfast.type = ExpenseType.BREAKFAST;
        breakfast.amount = 500;
        breakfast.name = "Breakfast";
        expenseList.add(breakfast);

        ExpenseReport expenseReport = new ExpenseReport();
        expenseReport.printReport(expenseList, date);

        String expected = String.format(
                "Expenses %s%n" +
                        "Dinner\t5001\tX%n"+
                        "Breakfast\t500\t %n"+
                        "Meal expenses: 5501%n" +
                        "Total expenses: 5501%n",
                date
        );

        assertEquals(String.format(expected, date).translateEscapes(), interceptedSystemOut.toString());
    }

}