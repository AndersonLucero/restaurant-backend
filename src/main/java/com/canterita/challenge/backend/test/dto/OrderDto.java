package com.canterita.challenge.backend.test.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class OrderDto {

	private Long id;
	
	private String number;

	private String client;
	
	private Double total;

	private LocalDateTime dateOrder;

	private List<OrderDetailsDto> details;

}
