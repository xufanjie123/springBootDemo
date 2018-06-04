package com.fanjiexu.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQQueueConsumer2 {
    @JmsListener(destination = "queuetest")
    public void receiveMsg(String str)
    {
        System.out.println("queue2收到消息" + str);
    }
}
