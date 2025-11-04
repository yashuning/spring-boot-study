package com.nys.study.spring.springbootstudy.wheel.kafka.juejin;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 消费位移分析
 * @date 2023/12/11 16:29
 */
@Slf4j
public class KafkaConsumerOffsetAnalysis {

    public static final String BROKER_LIST = "49.233.50.193:9092";
    public static final String TOPIC = "topic-demo";
    public static final String GROUP_ID = "topic-demo";
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

        TopicPartition tp = new TopicPartition(TOPIC, 0);
        consumer.assign(Collections.singletonList(tp));

        long lastConsumedOffset = -1;   //当前消费到的位移

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                if (records.isEmpty()) {
                    System.out.println("consumer pull empty");
                    break;
                }
                List<ConsumerRecord<String, String>> partitionRecords = records.records(tp);
                lastConsumedOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                consumer.commitSync();  //同步提交消费位移


                System.out.println("consumer comsumed offset is " + lastConsumedOffset);

                OffsetAndMetadata offsetAndMetadata = consumer.committed(tp);

                System.out.println("consumer commited offset is " + offsetAndMetadata.offset());

                long posititon = consumer.position(tp);
                System.out.println("consumer the offset of the next record is " + posititon);
            }
        } catch (Exception e) {
            log.error("occur exception ", e);
        } finally {
            consumer.close();
        }
    }

}
