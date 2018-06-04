package com.fanjiexu.demo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.*;

@RestController
public class HelloWorld {
    private static final String url = "";
    @Resource
    private ActiveMQQueueProducer queueProducer;
    @Resource
    private ActiveMQTopicProducer topicProducer;
    @RequestMapping("/hello")
    public String Hello()
    {
        return "Hello World!";
    }
    @RequestMapping("/QuartzTest")
    public void QuartzTest() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(QuartzDemo.class).withIdentity("myJob","group1").build();
        Trigger trigger = TriggerBuilder.
                newTrigger().
                withIdentity("myTrigger","group1").
                startNow().
                withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().
                                withIntervalInSeconds(2).repeatForever()).
                build();
         SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);
    }
    @RequestMapping("/ActivemqQueueProducer")
    public void activemqQueueProducer() throws JMSException {
//        String name = "queue-test";
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
//        Connection connection = connectionFactory.createConnection();
//        connection.start();
//        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        Destination destination = session.createQueue(name);
//        MessageProducer producer = session.createProducer(destination);
//        for(int i = 0 ; i < 100; i ++){
//            TextMessage textMessage = session.createTextMessage("test" + i);
//            producer.send(textMessage);
//        }
//        connection.close();
//        System.out.println("消息发送成功");
        for (int i = 0 ; i < 100; i++){
            queueProducer.sendMsg("queuetest","QueueMsg:" + i);
        }
    }

//    @RequestMapping("/ActivemqQueueConsumer")
//    public void activemqQueueCunsumer() throws JMSException {
//        String name = "queue-test";
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
//        Connection connection = connectionFactory.createConnection();
//        connection.start();
//        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        Destination destination = session.createQueue(name);
//        MessageConsumer messageConsumer = session.createConsumer(destination);
//        messageConsumer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                TextMessage textMessage = (TextMessage)message;
//                try {
//                    System.out.println("1接受消息:" + textMessage.getText());
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        //connection.close();
//        System.out.println("消息接收成功");
//    }
//
//    @RequestMapping("/ActivemqQueueConsumer2")
//    public void activemqQueueCunsumer2() throws JMSException {
//        String name = "queue-test";
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
//        Connection connection = connectionFactory.createConnection();
//        connection.start();
//        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        Destination destination = session.createQueue(name);
//        MessageConsumer messageConsumer = session.createConsumer(destination);
//        messageConsumer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                TextMessage textMessage = (TextMessage)message;
//                try {
//                    System.out.println("2接受消息:" + textMessage.getText());
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        //connection.close();
//        System.out.println("消息接收成功");
//    }

    @RequestMapping("/ActivemqTopicProducer")
    public void activemqTopicProducer() throws JMSException {
//        String name = "topic-test";
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
//        Connection connection = connectionFactory.createConnection();
//        connection.start();
//        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        Destination destination = session.createTopic(name);
//        MessageProducer producer = session.createProducer(destination);
//        for(int i = 0 ; i < 100; i ++){
//            TextMessage textMessage = session.createTextMessage("test" + i);
//            producer.send(textMessage);
//        }
//        connection.close();
//        System.out.println("消息发送成功");
        for (int i = 0 ; i < 100; i++){
            topicProducer.sendMsg("topictest","TopicMsg:" + i);
        }
    }
    @RequestMapping("/ActivemqTopicConsumer")
    public void activemqTopicCunsumer() throws JMSException {
        String name = "topic-test";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic(name);
        MessageConsumer messageConsumer = session.createConsumer(destination);
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("topic1接受消息:" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //connection.close();
        System.out.println("消息接收成功");
    }
}

