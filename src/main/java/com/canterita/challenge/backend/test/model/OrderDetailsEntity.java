package com.canterita.challenge.backend.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ORDER_DETAIL_ENTITY")
public class OrderDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ORDER_ENTITY_id")
    private Long idOrder;

    @Column(name = "detail")
    private String detail;

    @Column(name = "cantidad")
    private Double cantidad;

    @Column(name = "precio_unitario")
    private Double precio_unitario;

    @Column(name = "total_detail")
    private Double total_detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ENTITY_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private OrderEntity orderEntity;


}
