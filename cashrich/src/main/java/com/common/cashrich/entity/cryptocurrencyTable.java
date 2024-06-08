package com.common.cashrich.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class cryptocurrencyTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String data;
    Instant createdOn;

}
