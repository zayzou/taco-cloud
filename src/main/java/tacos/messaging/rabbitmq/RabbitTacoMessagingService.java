package tacos.messaging.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;
import tacos.messaging.TacoMessagingService;
import tacos.model.Taco;

@Service
public class RabbitTacoMessagingService implements TacoMessagingService {

    RabbitTemplate rabbit;

    public RabbitTacoMessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbit = rabbitTemplate;
    }

    @Override
    public void sendTaco(Taco taco) {
        MessageConverter converter = rabbit.getMessageConverter();
        MessageProperties properties = new MessageProperties();
        Message message = converter.toMessage(taco, properties);
        this.rabbit.send("tacocloud.tacos", message);
    }
}
