package com.canterita.challenge.backend.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderDetailsDto {

	private Long id;
	
	private Long idOrder;

	private String detail;
	
	private Double cantidad;

	private Double precio_unitario;

	private Double total_detail;
	
}
