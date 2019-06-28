package com.techvlife.simpleKafka.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class KafkaMessage {

    private String key;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "KafkaMessage{" +
                "key='" + key + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
