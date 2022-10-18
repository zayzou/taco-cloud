package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.data.OrderRepository;
import tacos.data.UserRepository;
import tacos.model.TacoOrder;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("orders")
@Slf4j
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepository;
    private UserRepository userRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
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
        System.out.println("User : "+user);
        tacoOrder.setUser(user);
        var order = orderRepository.save(tacoOrder);
        log.info("Order submitted - ", tacoOrder );
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

}
