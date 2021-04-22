package pl.spring.rabbit.springRabbit.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import pl.spring.rabbit.springRabbit.MessageDatabase;
import pl.spring.rabbit.springRabbit.configuration.RabbitConfiguration;
import pl.spring.rabbit.springRabbit.repository.MessageRepository;

@Component
public class AllMessageListener {

    private final MessageRepository messageRepository;
    private final Logger log = LoggerFactory.getLogger(AllMessageListener.class);

    public AllMessageListener(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @RabbitListener(queues = RabbitConfiguration.queueAllMessages)
    public void handleMessage(String text, Message message){
        String routingKey = (String) message.getHeaders().get("amqp_receivedRoutingKey");
        log.info("message: " + text + "routingKey " + routingKey);

        MessageDatabase messageDatabase = new MessageDatabase();
        messageDatabase.setText(text);
        messageDatabase.setRoutingKey(routingKey);
        messageRepository.save(messageDatabase);
        log.info("Message saved");
    }
}
