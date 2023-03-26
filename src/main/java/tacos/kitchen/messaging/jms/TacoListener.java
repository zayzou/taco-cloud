package tacos.kitchen.messaging.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import tacos.kitchen.messaging.KitchenUI;
import tacos.model.Taco;


//@Component
public class TacoListener {

    private final KitchenUI ui;

    public TacoListener(KitchenUI ui) {
        this.ui = ui;
    }

    @JmsListener(destination = "tacocloud.taco.queue")
    public void receiveTaco(Taco taco) {
        System.out.println("Is this method called ");
        System.out.println(taco.getName());
        ui.displayTaco(taco);
    }
}
