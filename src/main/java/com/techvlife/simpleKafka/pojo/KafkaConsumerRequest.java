package com.techvlife.simpleKafka.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class KafkaConsumerRequest {

    private String topicName;
    private String groupId;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "KafkaConsumerRequest{" +
                "topicName='" + topicName + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}
