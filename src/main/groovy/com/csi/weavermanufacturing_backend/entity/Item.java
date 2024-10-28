package com.csi.weavermanufacturing_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

    @Column(nullable = false)
    private String itemType;

    @Column(nullable = false)
    private String dateCode;

    @Column(nullable = false)
    private String revisionLevel;

    @Column(nullable = false)
    private String customerCode;

    @Column(nullable = false)
    private String sequenceNumber;

    private String itemName;
    private String approvalStatus;

    @Column(columnDefinition = "TEXT")
    private String description;
}

