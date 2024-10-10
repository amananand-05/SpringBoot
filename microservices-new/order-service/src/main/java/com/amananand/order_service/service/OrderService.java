package com.amananand.order_service.service;

import com.amananand.order_service.dto.InventoryResponse;
import com.amananand.order_service.dto.OrderLineItemsDto;
import com.amananand.order_service.dto.OrderRequest;
import com.amananand.order_service.model.Order;
import com.amananand.order_service.model.OrderLineItems;
import com.amananand.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItemsList);
        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();
        // check product is in stock from the inventory service
        InventoryResponse[] inventoryResponseArr = webClient.get()
                .uri("http://localhost:8082/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allInStock = false;
        if (inventoryResponseArr != null) {
            allInStock = Arrays.stream(inventoryResponseArr).allMatch(InventoryResponse::isInStock);
        }
        if (Boolean.TRUE.equals(allInStock && skuCodes.size() == inventoryResponseArr.length))
            orderRepository.save(order);
        else
            throw new IllegalArgumentException("Product is not in stock");
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }
}

