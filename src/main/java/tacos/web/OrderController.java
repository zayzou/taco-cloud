package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import tacos.data.OrderRepository;
import tacos.model.TacoOrder;

import javax.validation.Valid;

@Controller
@RequestMapping("orders")
@Slf4j
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository repo;

    public OrderController(OrderRepository repo) {
        this.repo = repo;
    }

    @GetMapping("current")
    public String showOrder() {
        return "orderForm";
    }

    @PostMapping
    public String process(@Valid TacoOrder tacoOrder, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        var order = repo.save(tacoOrder);
        log.info("Order submitted - ", tacoOrder);
        sessionStatus.setComplete();
        return "redirect:/orders?id="+order.getId();
    }

    @GetMapping()
    public String orderDetail(@RequestParam Long id, Model model) {
        TacoOrder order = repo.findById(id).orElseThrow();
        model.addAttribute("order",order);
        return "orderInfo";
    }

}
