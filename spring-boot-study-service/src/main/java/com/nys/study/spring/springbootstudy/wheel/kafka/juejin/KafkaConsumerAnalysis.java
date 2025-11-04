package com.nys.study.spring.springbootstudy.wheel.kafka.juejin;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 消费者客户端
 * @date 2023/12/8 16:19
 */
@Slf4j
public class KafkaConsumerAnalysis {

    /*
        一个正常的消费逻辑需要具备以下几个步骤：
            1、配置消费者客户端参数及创建相应的消费者实例。
            2、订阅主题。
            3、拉取消息并消费。
            4、提交消费位移。
            5、关闭消费`者实例。
     */

    public static final String BROKER_LIST = "49.233.50.193:9092";
    public static final String TOPIC = "topic-demo";
    public static final String GROUP_ID = "group.demo";
    public static final AtomicBoolean IS_RUNNING = new AtomicBoolean(true);

    public static Properties initConfig() {
        Properties props = new Properties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer.client.id.demo");
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        /*
            消费者订阅topic方式：
                1、subscribe(): 有消费者自动再均衡的功能，在多个消费者的情况下可以根据分区分配策略来自动分配各个消费者与分区的关系。当消费组内的消费者增加或减少时，分区分配关系会自动调整，以实现消费负载均衡及故障自动转移。
                2、assign(): 不具备消费者自动均衡的功能
         */
        consumer.subscribe(Collections.singletonList(TOPIC));

        try {
            while (IS_RUNNING.get()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
//                for (ConsumerRecord<String, String> record : records) {
//                    System.out.println("topic = " + record.topic() + ", partition = " + record.partition() + ", offset = " + record.offset());
//                    System.out.println("key = " + record.key() + ", value = " + record.value());
//                    //do something to process record.
//                }

                for (TopicPartition tp : records.partitions()) {    //  ConsumerRecords.partitions() 方法用来获取消息集中所有分区
                    for (ConsumerRecord<String, String> record : records.records(tp)) {
                        System.out.println(record.partition() + " : " + record.value());
                    }
                }

            }
        } catch (Exception e) {
            log.error("occur exception ", e);
        } finally {
            consumer.close();
        }

        /*
            消费者取消订阅topic三种方式：
                1、consumer.unsubscribe();
                2、consumer.subscribe(new ArrayList<String>());
                3、consumer.assign(new ArrayList<TopicPartition>());
         */
    }

}
