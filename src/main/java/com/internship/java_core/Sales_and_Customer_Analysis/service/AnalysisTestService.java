package com.internship.java_core.Sales_and_Customer_Analysis.service;

import com.internship.java_core.Sales_and_Customer_Analysis.dto.Category;
import com.internship.java_core.Sales_and_Customer_Analysis.dto.Customer;
import com.internship.java_core.Sales_and_Customer_Analysis.dto.Order;
import com.internship.java_core.Sales_and_Customer_Analysis.dto.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.internship.java_core.Sales_and_Customer_Analysis.dto.OrderStatus.*;
import static com.internship.java_core.Sales_and_Customer_Analysis.dto.OrderStatus.CANCELLED;
import static com.internship.java_core.Sales_and_Customer_Analysis.dto.OrderStatus.DELIVERED;

public class AnalysisTestService {
    public List<Order> createOrders(){
        List<Order> orders = new ArrayList<>();

        // Create clients
        Customer sigma = new Customer("1", "Sigma", "sigma@mail.com",
                LocalDateTime.of(2020, 1, 5, 10, 0), 25, "New York");

        Customer svetlana = new Customer("2", "Svetlana", "kandibober@mail.com",
                LocalDateTime.of(2010, 2, 26, 15, 37), 54, "Minsk");

        Customer andrey = new Customer("3", "Andrey", "helicopter@mail.com",
                LocalDateTime.of(2016, 3, 15, 8, 0), 33, "Warsaw");

        Customer pupa = new Customer("4", "Pupa", "pupa@mail.com",
                LocalDateTime.of(2018, 8, 15, 14, 0), 20, "Lublin");

        Customer lupa = new Customer("5", "Lupa", "lupa@mail.com",
                LocalDateTime.of(2018, 8, 16, 10, 0), 20, "Lublin");

        // Create orders with order items
        orders.add(new Order(
                "1",
                LocalDateTime.of(2025, 6, 8, 14, 44),
                sigma,
                List.of(
                        new OrderItem("Vinyl Player Marantz TT-15S1", 1, 1495, Category.ELECTRONICS),
                        new OrderItem("Gel Ice Pack", 30, 9.95, Category.BEAUTY),
                        new OrderItem("Deep Pore Cleanser", 5, 35, Category.BEAUTY),
                        new OrderItem("Brazilian Play Moisturizing Shower Cream-Gel", 3, 26, Category.BEAUTY),
                        new OrderItem("Almond Milk Body Scrub", 3, 26, Category.BEAUTY),
                        new OrderItem("The Dewy Skin Cream Moisturizer", 2, 72, Category.BEAUTY)
                ),
                DELIVERED));

        orders.add(new Order("2",
                LocalDateTime.of(2025, 5, 31, 12, 0),
                sigma,
                List.of(
                        new OrderItem("Axe", 1, 125, Category.TOYS)
                ),
                CANCELLED));

        orders.add(new Order("3",
                LocalDateTime.of(2010, 3, 3, 18, 18),
                svetlana,
                List.of(
                        new OrderItem("Kandibober", 1, 215, Category.BEAUTY)
                ),
                DELIVERED));

        orders.add(new Order("4",
                LocalDateTime.of(2025, 8, 6, 9, 22),
                andrey,
                List.of(
                        new OrderItem("Retro TV", 1, 150, Category.ELECTRONICS)
                ),
                DELIVERED));

        orders.add(new Order("5",
                LocalDateTime.of(2024, 10, 1, 18, 1),
                andrey,
                List.of(
                        new OrderItem("Last Of Us T-Shirt", 1, 55, Category.CLOTHING)
                ),
                PROCESSING));

        // Create order item for pupa and lupa
        OrderItem piggyBank = new OrderItem("1920s ANTIQUE DECORATIVE BRASS PIGGY BANK", 1, 1285, Category.HOME);

        orders.add(new Order("6",
                LocalDateTime.of(2019, 8, 14, 21, 5),
                pupa,
                List.of(
                        piggyBank
                ),
                CANCELLED));

        orders.add(new Order("7",
                LocalDateTime.of(2019, 8, 14, 20, 5),
                lupa,
                List.of(
                        piggyBank
                ),
                DELIVERED));

        orders.add(new Order("8",
                LocalDateTime.of(2019, 9, 1, 20, 7),
                lupa,
                List.of(
                        new OrderItem("Collectable mixed Match Box Labels B263", 1, 2, Category.HOME)
                ),
                DELIVERED));

        orders.add(new Order("9",
                LocalDateTime.of(2019, 9, 2, 17, 7),
                lupa,
                List.of(
                        new OrderItem("Candle Matches", 1, 2, Category.HOME)
                ),
                DELIVERED));

        orders.add(new Order("10",
                LocalDateTime.of(2019, 9, 3, 16, 22),
                lupa,
                List.of(
                        new OrderItem("Jar Matches", 1, 2, Category.HOME)
                ),
                DELIVERED));

        orders.add(new Order("11",
                LocalDateTime.of(2019, 9, 4, 15, 23),
                lupa,
                List.of(
                        new OrderItem("2 Inch Matches", 1, 2, Category.HOME)
                ),
                DELIVERED));

        orders.add(new Order("12",
                LocalDateTime.of(2019, 9, 5, 14, 24),
                lupa,
                List.of(
                        new OrderItem("3 Inch Matches", 1, 2, Category.HOME)
                ),
                DELIVERED));

        return orders;
    }
}