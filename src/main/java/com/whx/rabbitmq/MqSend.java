package com.whx.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqSend {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendEmail(String email){
        rabbitTemplate.convertAndSend("Email","email.register",email);
    }
}
