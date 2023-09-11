package com.nys.study.spring.springbootstudy.main.repository.kafka;

import com.google.common.collect.Maps;
import com.nys.study.spring.springbootstudy.main.BaseTest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author nys
 * @program: spring-boot-study
 * @Description: kafka学习
 * @date 2023/6/7 8:06 PM
 */
public class StudyKafkaTest extends BaseTest {
    @Test
    public void producer() throws ExecutionException, InterruptedException {
        /*
         * 创建 topic
         *  kafka-topics.sh --bootstrap-server study:9092,study:9093,study:9094 --create --topic ooxx --partitions 2 --replication-factor 2
         */
        String topic = "ooxx";
        Properties p = new Properties();
        p.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "49.233.50.193:9092,49.233.50.193:9093,49.233.50.193:9094");
        // kafka 持久化数据MQ  数据->byte[]， 不会对数据进行干预，生产者消费者双方要约定编码
        // kafka 是一个 app，使用零拷贝方式， sendfile 系统调用实现快速数据消费
        p.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        /*
         * 0 - 没有任何确认机制，发出去就返回
         * 1 - 发送至leader主ack后返回
         * -1 - 经过所有副本
         */
        p.setProperty(ProducerConfig.ACKS_CONFIG, "0");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(p);

        /*
         * 2partition
         */
        for (int i = 0; i< 10;i++){
            for (int j = 0; j< 20;j++){
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, i + "型商品", "iterm" + j);
                Future<RecordMetadata> send = producer.send(record);
                RecordMetadata rm = send.get();
                int partition = rm.partition();
                long offset = rm.offset();
                System.out.println("key: "+record.key()+" val: "+record.value()+" partition:"+partition+" offset"+offset);
            }
        }

    }

    @Test
    public void consumer(){
        // 基础配置
        Properties p = new Properties();
        p.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "49.233.50.193:9092,49.233.50.193:9093,49.233.50.193:9094");
        p.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 消费细节
        p.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "ooxx_consumer_nys");

        /**
         *          What to do when there is no initial offset in Kafka or if the current offset does not exist any more on the server
         *          (e.g. because that data has been deleted):
         *          <ul>
         *              <li>earliest: automatically reset the offset to the earliest offset
         *              <li>latest: automatically reset the offset to the latest offset</li>
         *              <li>none: throw exception to the consumer if no previous offset is found for the consumer's group</li><li>anything else: throw exception to the consumer.</li>
         *          </ul>
         */
        p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");   // 第一次启动没有offset

        /**
         * 一个运行的consumer，会自己维护自己的消费进度
         *  1、未到提交时间，consumer 挂了，没有提交 offset，重新起一个 consumer，拉取 offset 时，会重新消费
         *  2、一个批次的数据写数据库还没成功，但是这个批次的 offset 已经异步提交了，这批次就失败了
         */
        p.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");    // 自动提交时异步提交 可能会丢数据&&重复数据
//        p.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "");    // 5s

//        p.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "");  // poll 拉取数据 弹性，按需拉取

        KafkaConsumer<Object, Object> consumer = new KafkaConsumer<>(p);

        // kafka 的 consumer 会负载均衡
        consumer.subscribe(Arrays.asList("ooxx"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                System.out.println("====================onPartitionsRevoked====================");
                Iterator<TopicPartition> iter = partitions.iterator();
                while (iter.hasNext()){
                    System.out.println(iter.next().partition());
                }
            }
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                System.out.println("====================onPartitionsAssigned====================");
                Iterator<TopicPartition> iter = partitions.iterator();
                while (iter.hasNext()){
                    System.out.println(iter.next().partition());
                }
            }
        });

        // ========================= 按照时间戳拉取数据 =========================
        /*
         * 这段代码是通过自定义时间点的方式，自定义消费数据的位置
         * 本质：通过 seek() 方法！！！
         */
//        Map<TopicPartition, Long> tts = Maps.newHashMap();
//        Set<TopicPartition> as = consumer.assignment();
//        // 等待集群建立连接
//        while (as.size() == 0){
//            consumer.poll(Duration.ofMillis(100));
//            as = consumer.assignment();
//        }
//        // 为每个分区设置对应的时间戳
//        for (TopicPartition partition:as){
//            tts.put(partition, 11111L);
//        }
//        // 通过consumer api，取回timeindex数据
//        Map<TopicPartition, OffsetAndTimestamp> offTime = consumer.offsetsForTimes(tts);
//        for (TopicPartition partition:as){
//            // 通过取回的offset数据，通过 consumer的seek方法，修正自己的消费偏移值
//            OffsetAndTimestamp offsetAndTimestamp = offTime.get(partition);
//            long offset = offsetAndTimestamp.offset();
//            consumer.seek(partition, offset);
//        }
        // ===================================================================

        while (true){
            ConsumerRecords<Object, Object> records = consumer.poll(Duration.ofMillis(0));  // 0~n

            if (!records.isEmpty()){
                System.out.println("=========================当前批次拉取"+records.count()+"条=========================");
                Set<TopicPartition> partitions = records.partitions();

                /**
                 * 如果手动提交 offset 【前提：ENABLE_AUTO_COMMIT_CONFIG 设置 false - 不自动提交】
                 *  1、按消息粒度同步提交
                 *  2、按分区粒度同步提交
                 *  3、按当前 poll 批次同步提交
                 *
                 * 思考：如果在多个线程下
                 *  1、以上 1、3 方式不需要多线程
                 */
                for (TopicPartition partition : partitions) {
                    List<ConsumerRecord<Object, Object>> pRecords = records.records(partition);
                    Iterator<ConsumerRecord<Object, Object>> pIter = pRecords.iterator();
                    while (pIter.hasNext()){
                        ConsumerRecord<Object, Object> record = pIter.next();
                        int par = record.partition();
                        long offset = record.offset();
                        System.out.println("key: "+record.key()+" val: "+record.value()+" partition:"+par+" offset"+offset);

                        // 方式1提交
                        TopicPartition tp = new TopicPartition("ooxx", par);
                        OffsetAndMetadata om = new OffsetAndMetadata(offset);
                        HashMap<TopicPartition, OffsetAndMetadata> map = new HashMap<>();
                        map.put(tp, om);
                        consumer.commitSync(map);
                    }

                    // 方式2提交
                    long pOffset = pRecords.get(pRecords.size() - 1).offset();
                    OffsetAndMetadata pom = new OffsetAndMetadata(pOffset);
                    HashMap<TopicPartition, OffsetAndMetadata> pMap = new HashMap<>();
                    pMap.put(partition, pom);
                    consumer.commitSync(pMap);
                }
            }
        }
    }
}
