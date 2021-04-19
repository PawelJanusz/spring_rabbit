package pl.spring.rabbit.springRabbit.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.spring.rabbit.springRabbit.MessageDatabase;

public interface MessageRepository extends MongoRepository<MessageDatabase, String> {
}
