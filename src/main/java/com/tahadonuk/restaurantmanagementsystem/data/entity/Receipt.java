package com.tahadonuk.restaurantmanagementsystem.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "receipts")
@Data
public class Receipt {
    public Receipt() {};
    public Receipt(List<OrderItem> items) {
        this.items = items;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECEIPT_ID")
    private long receiptId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<OrderItem> items;

    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @Column(name = "ORDER_ID")
    private long orderId;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice = 0;
}
