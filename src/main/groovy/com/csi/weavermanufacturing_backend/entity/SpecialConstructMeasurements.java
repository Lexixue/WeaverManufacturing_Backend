package com.csi.weavermanufacturing_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class SpecialConstructMeasurements {
    @Id
    @ManyToOne
    @JoinColumn(name = "special_id", nullable = false)
    private SpecialConstruction specialConstruction;

    @Id
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private MeasurementType measurementType;

    private Double measurementValue;
}
