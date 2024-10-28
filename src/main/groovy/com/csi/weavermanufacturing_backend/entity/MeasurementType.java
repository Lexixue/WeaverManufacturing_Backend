package com.csi.weavermanufacturing_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MeasurementType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeId;

    @Column(nullable = false)
    private String measurementName;

    @Column(nullable = false)
    private String unit;
}
