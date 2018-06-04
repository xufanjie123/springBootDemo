package com.fanjiexu.demo;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;

@Service
public class ActiveMQQueueProducer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMsg(String queueName, String message) {

        System.out.println("发送queue消息" + message);
        Destination destination = new ActiveMQQueue(queueName);
        jmsMessagingTemplate.convertAndSend(destination, message);

    }


}