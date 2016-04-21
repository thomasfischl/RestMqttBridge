# RestMqttBridge

RestMqttBridge is a simple rest server to send MQTT messages. 

## Endpoint Send MQTT Message
```
PUT /mqtt/send?topic=<MQTT Topic>
<Body>
```
The rest endpoint send a mqtt message with the topic <MQTT Topic> and use the body of the rest call as payload.

## Endpoint Try
```
GET /mqtt/try
```
The rest call send a try message with topic "test" and payload "Hello World".

## Endpoint config
```
GET /mqtt/config
```

Response:
```
{"clientId":"bridgetest","broker":"tcp://broker.mqttdashboard.com:1883"}
```
The rest call returns the broker and clientId configuration

## Endpoint logfile
```
GET /logfile
```

Returns the content of the current logfile

## Broker

Use a predefined docker container from https://github.com/toke/docker-mosquitto

```
docker run -d -p 1883:1883 -p 9001:9001 toke/mosquitto
```

# Dev

Build Rest Server

```
gradlew build
gradlew bootRun
```

# Test

HiveMQ has a demo broker and client to send test messages.

Demo Broker: broker.hivemq.com:1883<p>
Demo Client: http://www.hivemq.com/demos/websocket-client/
