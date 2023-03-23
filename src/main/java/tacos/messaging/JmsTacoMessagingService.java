package tacos.messaging;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tacos.model.Taco;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class JmsTacoMessagingService implements TacoMessagingService {

    private final JmsTemplate jms;
    private final Destination tacoQueue;

    public JmsTacoMessagingService(JmsTemplate jms, Destination tacoQueue) {
        this.jms = jms;
        this.tacoQueue = tacoQueue;
    }


    @Override
    public void sendTaco(Taco taco) {
        jms.convertAndSend(tacoQueue, taco, this::addTacoSource);
//        jms.send(session -> session.createObjectMessage(taco));
    }

    private Message addTacoSource(Message message) throws JMSException {
        message.setStringProperty("X_TACO_SOURCE", "WEB");
        return message;
    }
}
