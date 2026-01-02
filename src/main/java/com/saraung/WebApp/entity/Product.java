package com.saraung.WebApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    private int prodId;
    private String prodName;
    private String desc;
    private String brand;
    private BigDecimal price;
    private String category;

    private Date createdAt;
    private boolean available;
    private int qty;

    @PrePersist
    protected void onCreate() {
        ZonedDateTime istTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        this.createdAt = Date.from(istTime.toInstant());
    }
    private String imageUrl;

}
