package tacos.messaging;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tacos.model.Taco;

@Service
public class JmsTacoMessagingService implements TacoMessagingService {

    private JmsTemplate jms;

    public JmsTacoMessagingService(JmsTemplate jms) {
        this.jms = jms;
    }


    @Override
    public void sendTaco(Taco taco) {
        jms.send(session -> session.createObjectMessage(taco));
    }
}
