package com.fanjiexu.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ActiveMQQueueConsumer {

    @JmsListener(destination = "queuetest")
    public void receiveMsg(String str)
    {
        System.out.println("queue收到消息" + str);
    }

}
