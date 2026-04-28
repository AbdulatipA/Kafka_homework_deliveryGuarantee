package org.example.order_service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderDTO saveOrder(EventPerson eventPerson){

        Order order = orderMapper.toEntity(eventPerson);
        orderRepo.save(order);
        OrderDTO orderDTO = orderMapper.toDto(order);
        return orderDTO;
    }

    public OrderDTO findById(Long id) {
        Order order = orderRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Пользователя с id " + id + "не существует"));

        OrderDTO orderDTO = orderMapper.toDto(order);

        return orderDTO;
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> ordersList = orderRepo.findAll();

        List<OrderDTO> orderDTOList = ordersList.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());

        return orderDTOList;
    }

    public void deleteOrderById(long id) {
        orderRepo.deleteById(id);
    }
}


//    public OrderDTO save(Order order) {
//        Order orderSaved = orderRepo.save(order);
//        OrderDTO orderDTO = orderMapper.toDto(orderSaved);
//        return orderDTO;
//    }