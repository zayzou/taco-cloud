package tacos.kitchen.messaging.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import tacos.kitchen.messaging.TacoReceiver;
import tacos.model.Taco;

@Service
@Slf4j
public class RabbitTacoReceiver implements TacoReceiver {

    private RabbitTemplate rabbit;


    public RabbitTacoReceiver(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public Taco receiveTaco() {
        log.info("Receiving message from rabbitmq broker");
    /*    Message message = rabbit.receive("test");
        return message != null ? (Taco) messageConverter.fromMessage(message) : null;
        return rabbit.receiveAndConvert("test", new ParameterizedTypeReference<Taco>() {
        });*/
        return (Taco) rabbit.receiveAndConvert("test");
    }
}
