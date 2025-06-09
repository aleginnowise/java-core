package com.internship.java_core.Sales_and_Customer_Analysis;

import com.internship.java_core.Sales_and_Customer_Analysis.dto.Customer;
import com.internship.java_core.Sales_and_Customer_Analysis.dto.Order;
import com.internship.java_core.Sales_and_Customer_Analysis.service.AnalysisTestService;
import com.internship.java_core.Sales_and_Customer_Analysis.service.SalesAndCustomerAnalysis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SalesAndCustomerAnalysisTest {
    SalesAndCustomerAnalysis analysis;
    private List<Order> orders;

    // Set up all object
    @BeforeEach
    public void setUp() {
        analysis = new SalesAndCustomerAnalysis();
        orders = new AnalysisTestService().createOrders();
    }

    // List of unique cities where orders came from
    @Test
    public void givenOrderList_whenGetUniqueCities_thenReturnsUniqueCities() {
        List<String> uniqueCities = analysis.uniqueCities(orders);

        List<String> expectedCities = List.of("New York", "Minsk", "Warsaw", "Lublin");

        assertTrue(uniqueCities.containsAll(expectedCities));
    }

    // Total income for all completed orders
    @Test
    public void givenOrderList_whenTotalIncomeDeliveredOrders_thenReturnsTotalIncome() {
        double totalIncome = analysis.totalIncomeDeliveredOrders(orders);

        // 1495 + (30 × 9.95) + (5 × 35) + (3 × 26) + (3 × 26) + (2 × 72) + 215 + 150 + 1285 + 1*5 = 3923.5
        double expectedIncome = 3923.5;

        assertEquals(expectedIncome, totalIncome);
    }

    // The most popular product by sales
    @Test
    public void givenOrderList_whenGetMostPopularProduct_thenReturnsMostPopularProduct() {
        Optional<String> expectedItem = "Gel Ice Pack".describeConstable();

        assertEquals(expectedItem, analysis.mostPopularProduct(orders));
    }

    // Average check for successfully delivered orders
    @Test
    public void givenOrderList_whenGetAverageCheckForDeliveredOrders_thenReturnsAverageCheckValue() {
        // 3923.5 / 4 = 980.875
        OptionalDouble expectedItem = OptionalDouble.of(980.875);

        assertEquals(expectedItem, analysis.avgOfDeliveredOrders(orders));
    }

    // Customers who have more than 5 orders
    @Test
    public void givenOrderList_whenGetCustomersWithFiveOrdersOrMore_thenReturnsCustomers() {
        List<Customer> customersAnalysis = analysis.customersWithFiveOrdersOrMore(orders);
        List<Customer> expectedCustomer = List.of(new Customer("5", "Lupa", "lupa@mail.com",
                LocalDateTime.of(2018, 8, 16, 10, 0), 20, "Lublin"));

        customersAnalysis.stream()
                .map(Customer::getName)
                .forEach(System.out::println);

        //TODO add assertation
//        assertTrue(customersAnalysis.containsAll(expectedCustomer));
    }
}
