package com.internship.java_core.Sales_and_Customer_Analysis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OrderItem {
    private String productName;
    private int quantity;
    private double price;
    private Category category;
}
