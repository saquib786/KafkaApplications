package com.techvlife.simpleKafka.service.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class SimpleProducer {

    public boolean push_data(String value){

        try{
            //Define Properties
            //We can get info about properties to be set from : https://kafka.apache.org/documentation/#producerconfigs
            Properties props = new Properties();
            props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
            //We can also write above line as below. ProducerConfig contains all those properties that can be set.
            // props.setProperty("bootstrap.servers","127.0.0.1:9092");
            props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

            //Create the producer, Key is string and value is string
            KafkaProducer<String,String> producer = new KafkaProducer<>(props);

            //Create producer Record, we are not defining key now
            ProducerRecord<String,String> record = new ProducerRecord<>("first_topic",value);

            producer.send(record); // This is async call. So better flush or wait till it closes

            producer.flush();
            producer.close();
            return true;
        }catch (Exception ex) {
            return false;
        }
    }
}
