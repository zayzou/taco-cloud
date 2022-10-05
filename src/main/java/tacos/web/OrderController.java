package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.model.TacoOrder;

import javax.validation.Valid;

@Controller
@RequestMapping("orders")
@Slf4j
@SessionAttributes("tacoOrder")
public class OrderController {

    @GetMapping("current")
    public String showOrder() {
        return "orderForm";
    }

    @PostMapping
    public String process(@Valid TacoOrder tacoOrder, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        log.info("Order submitted - ", tacoOrder);
        sessionStatus.setComplete();
        return "redirect:/design";
    }
}
