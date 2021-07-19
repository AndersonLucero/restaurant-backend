package com.canterita.challenge.backend.test.service;


import com.canterita.challenge.backend.test.dto.OrderDetailsDto;
import com.canterita.challenge.backend.test.model.OrderDetailsEntity;
import com.canterita.challenge.backend.test.model.OrderEntity;
import com.canterita.challenge.backend.test.repository.OrderDetailsRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canterita.challenge.backend.test.dto.OrderDto;
import com.canterita.challenge.backend.test.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	/*
	* metodo para obtener orden a partir de un id
	* */

	@Override
	public OrderDto getOrder(Long id) {
		return orderRepository.findById(id)
				.map(order -> {
					List<OrderDetailsDto> details = new ArrayList<>();
					for(OrderDetailsEntity o: order.getDetails()){
						OrderDetailsDto detail = new OrderDetailsDto(o.getId(),o.getIdOrder(),o.getDetail(),o.getCantidad(),o.getPrecio_unitario(),o.getTotal_detail());
						details.add(detail);
					}

					return new OrderDto(order.getId(), order.getNumber(), order.getClient(), order.getTotal(), order.getDateOrder(), details);
				})
				.orElse(null);
	}

	/*
	* metodo para crear nueva orden
	* */

	public void createOrder(OrderEntity orderEntity){
		if(null==orderEntity.getId()){
			orderRepository.save(orderEntity);
			if(CollectionUtils.isNotEmpty(orderEntity.getDetails())){
				for(OrderDetailsEntity orderDetailsEntity: orderEntity.getDetails()){
					orderDetailsEntity.setIdOrder(orderEntity.getId());
					System.out.println(orderDetailsEntity);
					orderDetailsRepository.save(orderDetailsEntity);
				}
			}
		}else{
			if(CollectionUtils.isNotEmpty(orderEntity.getDetails())){
				for(OrderDetailsEntity orderDetailsEntity: orderEntity.getDetails()){
					if(null==orderDetailsEntity.getId()){
						orderDetailsEntity.setIdOrder(orderEntity.getId());
						orderDetailsRepository.save(orderDetailsEntity);
					}
				}
			}
		}
	}



}
