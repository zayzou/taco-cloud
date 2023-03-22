package tacos.kitchen.messaging;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;
import tacos.model.Taco;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class JmsTacoReceiver implements TacoReceiver {

    private JmsTemplate jms;
    private MessageConverter messageConverter;

    private Destination destination;

    public JmsTacoReceiver(JmsTemplate jms, MessageConverter messageConverter, Destination destination) {
        this.jms = jms;
        this.messageConverter = messageConverter;
        this.destination = destination;
    }


    @Override
    public Taco receiveTaco() {
        Message message = jms.receive(destination);
        try {
            return (Taco) messageConverter.fromMessage(message);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
