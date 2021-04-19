package pl.spring.rabbit.springRabbit.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Message {

    @Size(max = 300)
    @NotBlank
    private String text;

    @Size(max = 30)
    @NotBlank
    private String routingKey;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
}
