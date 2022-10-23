package tacos.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import tacos.model.TacoOrder;
import tacos.model.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    public List<TacoOrder> findAllByDeliveryCity(String city);

    public List<TacoOrder> findAllByCcCVVOrderByPlacedAt(String ccCVV);

    List<TacoOrder> findByDeliveryNameAndDeliveryCityIgnoreCase(
            String deliveryTo, String deliveryCity);


    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
