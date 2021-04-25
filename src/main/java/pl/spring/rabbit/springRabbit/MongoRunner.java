package pl.spring.rabbit.springRabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.spring.rabbit.springRabbit.data.MessageDatabase;
import pl.spring.rabbit.springRabbit.repository.MessageRepository;

import java.util.List;

@Component
public class MongoRunner implements CommandLineRunner {

    private final MessageRepository messageRepository;
    private final Logger log = LoggerFactory.getLogger(MongoRunner.class);

    public MongoRunner(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        messageRepository.deleteAll();

        MessageDatabase messageDatabase1 = new MessageDatabase();
        messageDatabase1.setText("App is not secured");
        messageDatabase1.setRoutingKey("foo.bar.run");

        MessageDatabase messageDatabase2 = new MessageDatabase();
        messageDatabase2.setText("It is text");
        messageDatabase2.setRoutingKey("key2");

        messageRepository.saveAll(List.of(messageDatabase1, messageDatabase2));

        log.info("Message save");

        messageRepository.findAll()
                .forEach(messageDatabase -> {
                    log.info("Message from DB: ");
                    log.info(messageDatabase1.getId());
                    log.info(messageDatabase1.getText());
                });
    }
}
