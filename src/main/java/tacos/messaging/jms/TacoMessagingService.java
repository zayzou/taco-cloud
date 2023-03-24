package tacos.messaging.jms;


import tacos.model.Taco;

public interface TacoMessagingService {

    void sendTaco(Taco taco);
}
