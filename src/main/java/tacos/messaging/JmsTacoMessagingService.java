package tacos.messaging;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import tacos.model.Taco;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.Serializable;

@Service
public class JmsTacoMessagingService implements TacoMessagingService{

    private JmsTemplate jms;

    public JmsTacoMessagingService(JmsTemplate jms) {
        this.jms = jms;
    }


    @Override
    public void sendTaco(Taco taco) {
        jms.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("this is life");
            }
        });
    }
}
