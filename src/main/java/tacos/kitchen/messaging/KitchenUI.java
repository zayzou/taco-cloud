package tacos.kitchen.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tacos.model.Taco;

@Component
@Slf4j
public class KitchenUI {

    public void displayTaco(Taco taco) {
        log.info(taco.toString());
    }
}
