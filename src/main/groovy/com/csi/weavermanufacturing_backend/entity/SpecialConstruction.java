package com.csi.weavermanufacturing_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class SpecialConstruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer specialId;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String notes;
}