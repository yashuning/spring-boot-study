package com.nys.study.spring.springbootstudy.wheel.kafka.juejin;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @author ningyashu
 * @program: spring-boot-study
 * @Description: 生产者客户端示例代码
 * @date 2023/12/5 19:42
 */
public class KafkaProducerAnalysis {
    public static final String BROKER_LIST = "49.233.50.193:9092";
    public static final String TOPIC = "topic-demo";

    public static Properties initConfig() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.id.demo");
        /*
         * KafkaProducer 中一般会发生两种类型的异常：可重试的异常和不可重试的异常。<p>
         * 常见的可重试异常有：NetworkException、LeaderNotAvailableException、UnknownTopicOrPartitionException、NotEnoughReplicasException、NotCoordinatorException 等<p>
         * 不可重试的异常：比如 RecordTooLargeException 异常，表示发送消息太大，KafkaProducer 对此不会进行任何重试，直接抛出异常。
         */
        // 配置重试次数
        props.put(ProducerConfig.RETRIES_CONFIG, 10);
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, "Hello, Kafka!");
        try {
            /*
             * 发送模式：
             *  1、发后即忘（fire-and-forget）
             *  2、同步（sync）
             *  3、异步（async）
             */
            // 方式1：只管往 Kafka 中发送消息而并不关心消息是否正确到达；这种发送方式的性能最高，可靠性也最差
//            producer.send(record);

            // 方式2：同步发送等待，后续没有其他动作
//            producer.send(record).get();
            // 方式2：同步发送等待，并获取消息发送结果
//            Future<RecordMetadata> future = producer.send(record);
//            RecordMetadata metadata = future.get();
//            System.out.println(metadata.topic() + "-" + metadata.partition() + ":" + metadata.offset());

            // 方式3：异步发送
            /*
                为什么 send() 方法本身的返回值类型就是 Future，而 Future 本身就可以用作异步的逻辑处理，为什么还会有 callback 回调方式处理？
                因为 Future 里的 get() 方法在何时调用，以及怎么调用都是需要面对的问题，消息不停地发送，那么诸多消息对应的 Future 对象的处理难免会引起代码处理逻辑的混乱。
             */
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (Objects.nonNull(e)) {
                        System.out.println("消息发送异常！e=" + e);
                    } else {
                        System.out.println(metadata.topic() + "-" + metadata.partition() + ":" + metadata.offset());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 调用 KafkaProducer 的 close() 方法来回收资源
        producer.close();
    }
}
