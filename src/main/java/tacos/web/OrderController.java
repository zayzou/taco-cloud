package tacos.web;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.data.OrderRepository;
import tacos.data.UserRepository;
import tacos.model.TacoOrder;
import tacos.model.User;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("orders")
@Slf4j
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private OrderProps pageSize;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository,OrderProps pageSize) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.pageSize=pageSize;
    }



    @GetMapping("current")
    public String showOrder() {
        return "orderForm";
    }

    @PostMapping
    public String process(@Valid TacoOrder tacoOrder, Errors errors,
                          SessionStatus sessionStatus, Principal principal) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        var user = userRepository.findByUsername(principal.getName());
        Faker faker = new Faker();
        TacoOrder order = new TacoOrder();
        for (int i = 0; i < 100; i++) {
            tacoOrder = new TacoOrder();
            tacoOrder.setCcNumber(faker.finance().creditCard().replace("-", ""));
            tacoOrder.setDeliveryName(faker.friends().character());
            tacoOrder.setDeliveryCity(faker.friends().location());
            tacoOrder.setDeliveryState(faker.address().state());
            tacoOrder.setDeliveryStreet(faker.address().streetAddress());
            tacoOrder.setDeliveryZip(faker.address().zipCode());
            tacoOrder.setPlacedAt(faker.date().birthday());
            tacoOrder.setCcExpiration("09/24");
            tacoOrder.setCcCVV(faker.number().digits(3));
            tacoOrder.setUser(user);
            order = orderRepository.save(tacoOrder);
            System.out.println("Order submitted - " + tacoOrder);
        }
        sessionStatus.setComplete();
        return "redirect:/orders?id=" + order.getId();
    }

    @GetMapping()
//    @ResponseBody
    public String orderDetail(@RequestParam Long id, Model model) {
        TacoOrder order = orderRepository.findById(id).orElseThrow();
        System.out.println(order);
        model.addAttribute("order", order);
        return "orderInfo";
//        return order.toString();
    }

    @GetMapping("custom-jpa")
    @ResponseBody
    public String customJPA() {
        log.info(orderRepository.findAllByDeliveryCity("DEM").toString());
        log.info((orderRepository.findAllByCcCVVOrderByPlacedAt("123")).toString());
        log.info(orderRepository.findByDeliveryNameAndDeliveryCityIgnoreCase("user", "DEM").toString());
        return "custom-jpa";
    }

    @GetMapping("orderList")
    public String orderForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, pageSize.getPageSize());
        model.addAttribute(
                "orders",
                orderRepository.findByUserOrderByPlacedAtDesc
                        (user, pageable)
        );
        return "orderList";
    }
}
