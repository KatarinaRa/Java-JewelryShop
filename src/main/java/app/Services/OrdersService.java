package app.Services;

import app.Persistance.Entities.Orders;
import app.Persistance.Entities.Product;
import app.Persistance.Repositories.OrdersRepository;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrdersService {
    private final OrdersRepository repository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.repository = ordersRepository;
    }
    public List<Orders> getOrders() {

        return repository.findAll();
    }
    @Transactional
    public Optional<Orders> getOrderById(UUID id){
        return repository.findById(id);
    }
    @Transactional
    public void save(Orders orders){
        repository.save(orders);
    }
}
