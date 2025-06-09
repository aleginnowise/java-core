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
    public List<String> uniqueCities(List<Order> orders) {
        return orders.stream()
                .map(order -> order.getCustomer().getCity())
                .distinct()
                .collect(Collectors.toList());
    }

    public double totalIncomeDeliveredOrders(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.getStatus().equals(DELIVERED))
                .flatMap(order -> order.getItems().stream())
                .mapToDouble(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                .sum();
    }

    public Optional<String> mostPopularProduct(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        OrderItem::getProductName,
                        Collectors.summingInt(OrderItem::getQuantity)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    public OptionalDouble avgOfDeliveredOrders(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.getStatus().equals(DELIVERED))
                .mapToDouble(o ->
                o.getItems().stream()
                        .mapToDouble(i -> i.getPrice() * i.getQuantity())
                        .sum())
                .average();
    }

    public List<Customer> customersWithFiveOrdersOrMore(List<Order> orders){
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomer,
                        Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}