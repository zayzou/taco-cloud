package tacos.kitchen.messaging.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;
import tacos.kitchen.messaging.TacoReceiver;
import tacos.model.Taco;

import javax.jms.Destination;

@Service
public class JmsTacoReceiver implements TacoReceiver {

    private final JmsTemplate jms;
    private final MessageConverter messageConverter;

    private final Destination destination;

    public JmsTacoReceiver(JmsTemplate jms, MessageConverter messageConverter, Destination destination) {
        this.jms = jms;
        this.messageConverter = messageConverter;
        this.destination = destination;
    }


    @Override
    public Taco receiveTaco() {
        return (Taco) jms.receiveAndConvert(destination);
    }
}
