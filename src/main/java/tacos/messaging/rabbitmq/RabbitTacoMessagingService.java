package tacos.messaging.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
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
        MessageConverter converter = rabbit.getMessageConverter();
        MessageProperties properties = new MessageProperties();
        Message message = converter.toMessage(taco, properties);
        log.info("Sending message : " + message);
        this.rabbit.send("test", message);
//        this.rabbit.convertAndSend("test","this is a message from my app");
    }
}
