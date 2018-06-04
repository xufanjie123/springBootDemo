package com.fanjiexu.demo;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
@Service
public class ActiveMQTopicProducer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMsg(String queueName, String message){

        System.out.println("发送topic消息" + message);
        Destination destination = new ActiveMQTopic(queueName);
        jmsMessagingTemplate.convertAndSend(destination,message);

    }
}
