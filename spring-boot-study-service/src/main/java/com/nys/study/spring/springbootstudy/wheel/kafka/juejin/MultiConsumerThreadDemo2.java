package com.nys.study.spring.springbootstudy.wheel.kafka.juejin;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: 消费者多线程消费
 * @date 2023/12/12 17:33
 */
public class MultiConsumerThreadDemo2 {

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
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaConsumerThread consumerThread = new KafkaConsumerThread(props, TOPIC, Runtime.getRuntime().availableProcessors());
        consumerThread.start();
    }

    /**
     * 消费线程
     */
    public static class KafkaConsumerThread extends Thread {
        private KafkaConsumer<String, String> kafkaConsumer;
        private ExecutorService executorService;
        private int threadNumber;

        public KafkaConsumerThread(Properties props, String topic, int threadNumber) {
            kafkaConsumer = new KafkaConsumer<>(props);
            kafkaConsumer.subscribe(Collections.singletonList(topic));
            this.threadNumber = threadNumber;
            executorService = new ThreadPoolExecutor(threadNumber, threadNumber, 0L, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<>(1000),
                    new ThreadPoolExecutor.CallerRunsPolicy());
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                    if (!records.isEmpty()) {
                        executorService.submit(new RecordsHandler(records));
                    }
                    // todo nys 第14节 关于这块不是很明白，后续再重新看 offsets作用，怎么处理，手动提交？
//                    synchronized (offsets) {
//                        if (!offsets.isEmpty()) {
//                            kafkaConsumer.commitSync(offsets);
//                            offsets.clear();
//                        }
//                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                kafkaConsumer.close();
            }
        }

    }

    /**
     * 真正处理消息逻辑的Handler
     */
    public static class RecordsHandler extends Thread {
        public final ConsumerRecords<String, String> records;

        public RecordsHandler(ConsumerRecords<String, String> records) {
            this.records = records;
        }

        @Override
        public void run() {
            // todo nys 第14节 关于这块不是很明白，后续再重新看 offsets作用，怎么处理，手动提交？
            Map<TopicPartition, OffsetAndMetadata> offsets = null;
//            for (TopicPartition tp : records.partitions()) {
//                List<ConsumerRecord<String, String>> tpRecords = records.records(tp);
//                //处理tpRecords.
//                long lastConsumedOffset = tpRecords.get(tpRecords.size() - 1).offset();
//                synchronized (offsets) {
//                    if (!offsets.containsKey(tp)) {
//                        offsets.put(tp, new OffsetAndMetadata(lastConsumedOffset + 1));
//                    } else {
//                        long position = offsets.get(tp).offset();
//                        if (position < lastConsumedOffset + 1) {
//                            offsets.put(tp, new OffsetAndMetadata(lastConsumedOffset + 1));
//                        }
//                    }
//                }
//            }
        }
    }

}
