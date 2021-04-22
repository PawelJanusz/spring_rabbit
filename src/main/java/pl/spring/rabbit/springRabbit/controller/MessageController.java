package pl.spring.rabbit.springRabbit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pl.spring.rabbit.springRabbit.MessageDatabase;
import pl.spring.rabbit.springRabbit.configuration.RabbitConfiguration;
import pl.spring.rabbit.springRabbit.model.Message;
import pl.spring.rabbit.springRabbit.repository.MessageRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final RabbitTemplate rabbitTemplate;
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);
    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(RabbitTemplate rabbitTemplate, MessageRepository messageRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageRepository = messageRepository;
    }

    @PostMapping
    public void sendMessage(@RequestBody @Valid Message message){
        log.info("Message received:" + message.getText());
        rabbitTemplate.convertAndSend(RabbitConfiguration.topicExchangeName,
                message.getRoutingKey(),
                message.getText());
    }

    @GetMapping
    public List<MessageDatabase> findAllMessages(@RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "100") int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return messageRepository.findAll(pageRequest).getContent();
    }

}
