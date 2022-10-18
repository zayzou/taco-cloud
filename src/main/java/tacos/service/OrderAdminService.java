package tacos.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import tacos.data.OrderRepository;

@Service
public class OrderAdminService {

    private final OrderRepository repository;


    public OrderAdminService(OrderRepository repository) {
        this.repository = repository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders(){
        repository.deleteAll();
    }
}
