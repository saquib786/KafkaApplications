package com.techvlife.simpleKafka.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class KafkaProducerResponse {

    private long offset;
    private long timeStamp;
    private int partition;
    private String topic;
    private String errorMessage;

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getPartition() {
        return partition;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "KafkaProducerResponse{" +
                "offset=" + offset +
                ", timeStamp=" + timeStamp +
                ", partition=" + partition +
                ", topic='" + topic + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
