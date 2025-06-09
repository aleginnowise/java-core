package com.internship.java_core.Sales_and_Customer_Analysis.service;

import com.internship.java_core.Sales_and_Customer_Analysis.dto.Customer;
import com.internship.java_core.Sales_and_Customer_Analysis.dto.Order;
import com.internship.java_core.Sales_and_Customer_Analysis.dto.OrderItem;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import static com.internship.java_core.Sales_and_Customer_Analysis.dto.OrderStatus.DELIVERED;

public class SalesAndCustomerAnalysis {
    // List of unique cities where orders came from
    public List<String> uniqueCities(List<Order> orders) {
        return orders.stream()
                // Returns all cities
                .map(order -> order.getCustomer().getCity())
                // Returns unique cities
                .distinct()
                .collect(Collectors.toList());
    }

    // Total income for all completed orders
    public double totalIncomeDeliveredOrders(List<Order> orders) {
        return orders.stream()
                // Returns only delivered orders
                .filter(order -> order.getStatus().equals(DELIVERED))
                // Returns stream with order items
                .flatMap(order -> order.getItems().stream())
                // Returns price * quantity for each item
                .mapToDouble(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                // Sum all items income
                .sum();
    }

    // The most popular product by sales
    public Optional<String> mostPopularProduct(List<Order> orders) {
        return orders.stream()
                // Returns stream with order items
                .flatMap(order -> order.getItems().stream())
                // Returns map with grouping by product name and quantity sum
                .collect(Collectors.groupingBy(
                        OrderItem::getProductName,
                        Collectors.summingInt(OrderItem::getQuantity)))
                // Converts map to entry set
                .entrySet()
                .stream()
                // Finds the max quantity value in the entry set
                .max(Map.Entry.comparingByValue())
                // Returns the most popular product name as Optional<String>
                .map(Map.Entry::getKey);
    }

    // Average check for successfully delivered orders
    public OptionalDouble avgOfDeliveredOrders(List<Order> orders) {
        return orders.stream()
                // Returns only delivered orders
                .filter(order -> order.getStatus().equals(DELIVERED))
                // Sum items total income in each order
                .mapToDouble(o ->
                o.getItems().stream()
                        .mapToDouble(i -> i.getPrice() * i.getQuantity())
                        .sum())
                // Average of orders income
                .average();
    }

    // Customers who have more than 5 orders
    public List<Customer> customersWithFiveOrdersOrMore(List<Order> orders){
        return orders.stream()
                // Returns map with grouping by customer and orders quantity. Key - customers, value - quantity
                .collect(Collectors.groupingBy(
                        Order::getCustomer,
                        Collectors.counting()))
                // Converts map to entry set
                .entrySet()
                .stream()
                // Returns entries with order quantity > 5
                .filter(entry -> entry.getValue() > 5)
                // Returns customers
                .map(Map.Entry::getKey)
                // To list parsing
                .collect(Collectors.toList());
    }
}