package com.canterita.challenge.backend.test.rest;

import ch.qos.logback.classic.gaffer.PropertyUtil;
import com.canterita.challenge.backend.test.dto.OrderDetailsDto;
import com.canterita.challenge.backend.test.model.OrderDetailsEntity;
import com.canterita.challenge.backend.test.model.OrderEntity;
import com.canterita.challenge.backend.test.repository.OrderDetailsRepository;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.PropertyAccessorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.canterita.challenge.backend.test.dto.OrderDto;
import com.canterita.challenge.backend.test.service.IOrderService;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8100", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;

	/*
	* metodo GET url: http://localhost:8100/orders/{idOrder}
	* */

	@GetMapping(value = "/{idOrder}")
	@ResponseStatus(HttpStatus.OK)
	public OrderDto getOrder(@PathVariable Long idOrder) {
		return orderService.getOrder(idOrder);
	}

	/*
	* Metodo POST url: http://localhost:8100/orders/addDetails
	* */

	@PostMapping("/addDetails")
	@ResponseStatus(HttpStatus.OK)
	public OrderDto createOrder(@RequestBody OrderDto orderDto){
		OrderEntity orderEntity = getOrderEntity(orderDto);
		orderService.createOrder(orderEntity);
		orderDto.setId(orderEntity.getId());

		/*agregacion de id en DTO tomado de ENTITY*/
		if(org.apache.commons.collections.CollectionUtils.isNotEmpty(orderEntity.getDetails())){
			for(int i=0; i<orderEntity.getDetails().size(); i++){
				orderDto.getDetails().get(i).setId(orderEntity.getDetails().get(i).getId());
				orderDto.getDetails().get(i).setIdOrder(orderEntity.getId());
			}
		}
		return orderDto;
	}

	/*
	* funciones de apoyo
	* transformar clases DTO a ENTITY
	* */

	public OrderEntity getOrderEntity(OrderDto order){
		OrderEntity orderEntity = new OrderEntity();
		try{
			PropertyUtils.copyProperties(orderEntity,order);
			if(!CollectionUtils.isEmpty(order.getDetails())){
				List<OrderDetailsEntity> entities = new ArrayList<>();
				for(OrderDetailsDto details: order.getDetails()){
					entities.add(getOrderDetailsEntity(details));
				}
				orderEntity.setDetails(entities);
			}
		}catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
			System.out.println(e);
		}
		return orderEntity;
	}


	public OrderDetailsEntity getOrderDetailsEntity(OrderDetailsDto details){
		OrderDetailsEntity detailsEntity = new OrderDetailsEntity();
		try {
			PropertyUtils.copyProperties(detailsEntity, details);
		}catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
			System.out.println(e);
		}
		return detailsEntity;
	}


}
