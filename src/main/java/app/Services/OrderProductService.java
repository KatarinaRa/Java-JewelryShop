package app.Services;

import app.Persistance.Entities.Cart;
import app.Persistance.Entities.CartProduct;
import app.Persistance.Entities.OrderProduct;
import app.Persistance.Entities.Orders;
import app.Persistance.Repositories.OrderProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderProductService {
    private final OrderProductRepository repository;

    public OrderProductService(OrderProductRepository repository) {
        this.repository = repository;
    }

    public OrderProduct save(OrderProduct orderProduct){
       return repository.save(orderProduct);
    }

    public List<OrderProduct> findByOrder(Orders order){
        return  repository.findByOrders(order);
    }
}
