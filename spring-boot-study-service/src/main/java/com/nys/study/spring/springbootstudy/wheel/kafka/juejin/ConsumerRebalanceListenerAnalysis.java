package com.nys.study.spring.springbootstudy.wheel.kafka.juejin;

import com.google.common.collect.Maps;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 再均衡监听器使用
 * @date 2023/12/12 17:07
 */
public class ConsumerRebalanceListenerAnalysis {

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

        // 暂存消费位移
        Map<TopicPartition, OffsetAndMetadata> currentOffsets = Maps.newHashMap();

        // 方式1：===========================================================================
        consumer.subscribe(Collections.singletonList(TOPIC), new ConsumerRebalanceListener() {
            /**
             * 在 消费者停止读取消息之后 和 再均衡开始之前 被调用。
             * 可以通过这个回调方法来处理消费位移的提交，以此来避免一些不必要的重复消费现象的发生。
             * @param partitions 表示再均衡前所分配到的分区。
             */
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                // 同步提交消费位移
                consumer.commitSync(currentOffsets);
                currentOffsets.clear();
            }

            /**
             * 会在 重新分配分区之后 和 消费者开始读取消费之前 被调用。
             * @param partitions 表示再均衡后所分配到的分区。
             */
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                //do nothing.
            }
        });
        // 方式1：===========================================================================

        // 方式2：配合外部存储使用 ============================================================
        consumer.subscribe(Collections.singletonList(TOPIC), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                //store offset in DB （storeOffsetToDB）
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                for (TopicPartition tp : partitions) {
//                    consumer.seek(tp, getOffsetFromDB(tp));//从DB中读取消费位移
                }
            }
        });
        // 方式2：===========================================================================

        try {
            while (IS_RUNNING.get()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    //process the record.
                    currentOffsets.put(
                            new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1));
                }
                consumer.commitAsync(currentOffsets, null);
            }
        } finally {
            consumer.close();
        }
    }

}
