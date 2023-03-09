package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class RabbitMqListener {

/*
    @RabbitListener(queues = "simple.queue")
    public void rabbitSimpleQueueListener(String msg) {
        System.out.println("消费者收到消息 ["+msg+"]");
    }
*/


    @RabbitListener(queues = "simple.queue")
    public void rabbitSimpleQueueListener1(String msg) throws InterruptedException {
        System.out.println("消费者1收到消息 [" + msg + "]");
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void rabbitSimpleQueueListener2(String msg) throws InterruptedException {
        System.out.println("消费者2收到消息 [" + msg + "]");
        Thread.sleep(100);

    }


    @RabbitListener(queues = "fanout.queue1")
    public void rabbitFanoutQueueListener1(String msg) throws InterruptedException {
        System.out.println("消费者1收到fanout消息 [" + msg + "]");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void rabbitFanoutQueueListener2(String msg) throws InterruptedException {
        System.out.println("消费者2收到fanout消息 [" + msg + "]");
    }

    //    [Direct]
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "ilovend.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenDirectQueue1(String msg) {
        System.out.println("消费者接收到direct.queue1的消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "ilovend.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    public void listenDirectQueue2(String msg) {
        System.out.println("消费者接收到direct.queue2的消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "ilovend.topic", type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenTopicQueue1(String msg) {
        System.out.println("消费者接收到topic.queue1的消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "ilovend.topic", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueue2(String msg) {
        System.out.println("消费者接收到topic.queue2的消息：【" + msg + "】");
    }

    @RabbitListener(queues = "object.queue")
    public void rabbitSimpleQueueListener(Map map) {
        System.out.println("消费者接收到的对象消息：【" + map + "】");
    }
}
