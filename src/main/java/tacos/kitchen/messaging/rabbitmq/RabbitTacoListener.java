package tacos.kitchen.messaging.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tacos.kitchen.messaging.KitchenUI;
import tacos.model.Taco;

@Component
@Slf4j
public class RabbitTacoListener {

    private KitchenUI ui;

    public RabbitTacoListener(KitchenUI ui) {
        this.ui = ui;
    }

    @RabbitListener(queues = "test")
    public void receiveTaco(Taco taco) {
        log.info("Call from rabbit listener ");
        ui.displayTaco(taco);
    }
}
