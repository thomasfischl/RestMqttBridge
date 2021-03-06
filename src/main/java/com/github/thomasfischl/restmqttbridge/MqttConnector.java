package com.github.thomasfischl.restmqttbridge;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@ConfigurationProperties
@Service
public class MqttConnector {

    @Value("${mqtt.broker}")
    private String broker;

    @Value("${mqtt.clientId}")
    private String clientId;

    private MqttClientPersistence persistence = new MemoryPersistence();

    public void sendMessage(String topic, String content, boolean retained) throws MqttException {
        MqttClient client = new MqttClient(broker, clientId, persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        client.connect(connOpts);
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(2);
        message.setRetained(retained);
        client.publish(topic, message);
        client.disconnect();
    }

    public String getBroker() {
        return broker;
    }

    public String getClientId() {
        return clientId;
    }

}
