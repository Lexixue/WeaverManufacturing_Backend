package com.csi.weavermanufacturing_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ItemMetal {
    @Id
    private String metalCode;

    private String metalType;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
}

