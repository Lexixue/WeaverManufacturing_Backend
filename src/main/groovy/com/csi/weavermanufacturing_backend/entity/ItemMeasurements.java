package com.csi.weavermanufacturing_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ItemMeasurements {
    @Id
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Id
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private MeasurementType measurementType;

    private Double measurementValue;
}
