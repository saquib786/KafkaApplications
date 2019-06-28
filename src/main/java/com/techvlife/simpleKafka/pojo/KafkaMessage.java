package com.techvlife.simpleKafka.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class KafkaMessage {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "KafkaMessage{" +
                "data='" + data + '\'' +
                '}';
    }
}
