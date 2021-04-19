package pl.spring.rabbit.springRabbit.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.spring.rabbit.springRabbit.ScheduledTasks;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Receiver {

    private static final Logger log = (Logger) LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public void receiveMessage(String message){
        log.info("Received <" + message + ">", "Received :", dateFormat.format(new Date()));
    }

}
