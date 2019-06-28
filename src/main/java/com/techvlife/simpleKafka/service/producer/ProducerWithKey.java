package com.techvlife.simpleKafka.service.producer;

import com.techvlife.simpleKafka.pojo.KafkaMessage;
import com.techvlife.simpleKafka.pojo.KafkaProducerResponse;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class ProducerWithKey {

    public KafkaProducerResponse push_data(KafkaMessage message){
        KafkaProducerResponse response = new KafkaProducerResponse();
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

            //Use of key is that all records with same key goes to same partition
            ProducerRecord<String,String> record = new ProducerRecord<>("first_topic",message.getKey(),message.getData());

            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if(e == null){
                        response.setOffset(recordMetadata.offset());
                        response.setPartition(recordMetadata.partition());
                        response.setTimeStamp(recordMetadata.timestamp());
                        response.setTopic(recordMetadata.topic());
                    }else{
                        response.setErrorMessage(e.getMessage());
                    }
                }
            }); // This is async call. So better flush or wait till it closes

            producer.flush();
            producer.close();
            return response;
        }catch (Exception ex) {
            response.setErrorMessage("Error in saving data to Kafka");
            return response;
        }
    }

}
