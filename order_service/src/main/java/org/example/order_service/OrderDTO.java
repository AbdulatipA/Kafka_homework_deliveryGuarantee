package org.example.order_service;

import lombok.Data;

@Data
public class OrderDTO {
    private Long clientId;
    private String clientName;
    private String orderName;
}