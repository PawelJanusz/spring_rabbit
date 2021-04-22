package pl.spring.rabbit.springRabbit.mvc;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.spring.rabbit.springRabbit.configuration.RabbitConfiguration;
import pl.spring.rabbit.springRabbit.model.Message;

@Controller
@RequestMapping("/mvc/message")
public class MvcMessageController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MvcMessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public String greetingFrom(Model model){
        model.addAttribute("message", new Message());
        return "message";
    }

    @PostMapping
    public String greetingSubmit(@ModelAttribute Message message, Model model){
        model.addAttribute("message", message);
        rabbitTemplate.convertAndSend(RabbitConfiguration.topicExchangeName,
                "foo.bar.bar");
        message.getRoutingKey();
        message.getText();
        return "result";
    }


}
