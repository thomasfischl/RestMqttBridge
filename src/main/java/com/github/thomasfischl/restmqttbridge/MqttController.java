package com.github.thomasfischl.restmqttbridge;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bridge")
public class MqttController {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private MqttConnector connector;

  @RequestMapping(value = "/send", method = RequestMethod.PUT)
  public void update(@RequestParam(value = "topic") String topic, @RequestBody String content,
      @RequestParam(value = "retain", defaultValue = "true", required = false) String retain) {
    try {
      connector.sendMessage(topic, content, Boolean.valueOf(retain));
    } catch (MqttException e) {
      log.error("An error occurs during sending mqtt message '" + content + "'.", e);
    }
  }

  @RequestMapping("/try")
  public String trySend() {
    try {
      connector.sendMessage("test", "Hello World", false);
      return "OK";
    } catch (MqttException e) {
      log.error("An error occurs during sending mqtt message.", e);
      return e.getMessage();
    }
  }

  @RequestMapping("/config")
  public Map<String, String> getConfig() {
    Map<String, String> config = new HashMap<>();
    config.put("clientId", connector.getClientId());
    config.put("broker", connector.getBroker());
    return config;
  }
}
