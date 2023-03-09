package cn.itcast.mq.helloworld.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqPublisherTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendSimpleMessage() {

        String queueName = "simple.queue";
        String message = "hello simple rabbitmq";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testWorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        for (int i = 0; i < 50; i++) {
            String message = "hello work queue" + i;
            rabbitTemplate.convertAndSend(queueName, message);
            Thread.sleep(20);
        }
    }


    @Test
    public void testFanoutExchange() {

        String exchangeName = "ilovend.fanout";
        String msg = "fanout message";
        rabbitTemplate.convertAndSend(exchangeName, "", msg);
    }

    @Test
    public void testSendDirectExchange() {
        // 交换机名称
        String exchangeName = "ilovend.direct";
        // 消息
        String message = "红色警报！日本乱排核废水，导致海洋生物变异，惊现哥斯拉！";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "blue", message);
    }


    @Test
    public void testSendTopicExchange() {
        // 交换机名称
        String exchangeName = "ilovend.topic";
        // 消息
        String message = "喜报！孙悟空大战哥斯拉，胜!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "china.news", message);
    }

    @Test
    public void testObjectQueue() {
        String queue = "object.queue";
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("name", "jack");
        messageMap.put("age", "17");
        rabbitTemplate.convertAndSend(queue, messageMap);
    }


}
