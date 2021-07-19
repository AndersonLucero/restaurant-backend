package com.canterita.challenge.backend.test.service;

import com.canterita.challenge.backend.test.dto.OrderDetailsDto;
import com.canterita.challenge.backend.test.dto.OrderDto;
import com.canterita.challenge.backend.test.model.OrderDetailsEntity;
import com.canterita.challenge.backend.test.model.OrderEntity;

import java.util.List;

public interface IOrderService {
	
	public OrderDto getOrder(Long id);

	public void createOrder(OrderEntity orderEntity);

}
