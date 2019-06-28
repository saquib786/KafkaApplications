package com.techvlife.simpleKafka.service.consumer;

import com.techvlife.simpleKafka.pojo.KafkaConsumerRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Component
public class SimpleConsumer {

    Logger logger = LoggerFactory.getLogger(SimpleConsumer.class);

    public void consume_messages(KafkaConsumerRequest request){

        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG,request.getGroupId());
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");// This value can be earliest/latest/none

        //Create a consumer
        KafkaConsumer<String,String> consumer= new KafkaConsumer<>(props);

        //Subscribe to a topic or list of topics
        consumer.subscribe(Arrays.asList(request.getTopicName()));

        //poll for new data
        while(true){
            //below will get kafka data every 100 milliseconds until stopped
            ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(100));

            for(ConsumerRecord record : records){
                logger.info("key: {}, data: {}, partition: {} and timeStamp: {}",
                        record.key(),record.value(),record.partition(),record.timestamp());
            }
        }

    }
}
