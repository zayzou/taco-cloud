package tacos.messaging.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import tacos.messaging.TacoMessagingService;
import tacos.model.Taco;

@Service
@Slf4j
public class RabbitTacoMessagingService implements TacoMessagingService {

    RabbitTemplate rabbit;

    public RabbitTacoMessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbit = rabbitTemplate;
    }

    @Override
    public void sendTaco(Taco taco) {
        rabbit.convertAndSend("test", taco);
    }
}
