package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.service.OrderAdminService;

@Controller
@Service
@Component
@RequestMapping("/admin")
@Slf4j
public class AdminController {


    OrderAdminService orderAdminService;

    public AdminController(OrderAdminService orderAdminService) {
        this.orderAdminService = orderAdminService;
    }

    @GetMapping
    public String showAdmin() {
        return "admin";
    }

    @PostMapping("/deleteAllOrders")
    public String deleteAll() {
        orderAdminService.deleteAllOrders();
        log.info("Orders Deleted 👿");
        return "redirect:/admin";
    }
}
