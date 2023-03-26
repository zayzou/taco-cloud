package tacos.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.data.TacoRepository;
import tacos.kitchen.messaging.rabbitmq.RabbitTacoReceiver;
import tacos.messaging.rabbitmq.RabbitTacoMessagingService;
import tacos.model.Taco;


@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "https://localhost:8443")
@Slf4j
public class TacoController {

    TacoRepository tacoRepository;
    RabbitTacoMessagingService messagingService;

    RabbitTacoReceiver tacoReceiver;

    public TacoController(TacoRepository tacoRepository, RabbitTacoMessagingService messagingService, RabbitTacoReceiver tacoReceiver) {
        this.tacoRepository = tacoRepository;
        this.messagingService = messagingService;
        this.tacoReceiver = tacoReceiver;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos() {
        Iterable<Taco> all = tacoRepository.findAll();
        return all;
    }

    @GetMapping(params = "first")
    public Taco first() {
        Taco taco = tacoRepository.findById(105L).get();
        this.messagingService.sendTaco(taco);
        return taco;
    }

    @GetMapping(params = "receive")
    public Taco receive() {
        Taco taco = tacoReceiver.receiveTaco();
        return taco;
    }


}
