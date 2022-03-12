package com.ile.voda;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WaterSensor {
     String topic;
     String broker;
     String ClientID;
     List<Sensor> sensors;

     public WaterSensor(String topic,String broker,String ClientID, List<Sensor> sensors){
          this.topic = topic;
          this.broker = broker;
          this.ClientID = ClientID;
          this.sensors = sensors;
     }

     public void start(WaterSensor sensor) throws InterruptedException{
          String FilePath = "C:\\Users\\Ilario\\Desktop\\backup\\Folderi\\Java Sranja\\Labovi\\Lab3\\output.json";
          int qos = 2;
          while(true){
               List<Sensor> sensor1 = sensor.getSensors();
               for (Sensor value : sensor1) {
                    value.SetRandomSize();
               }
               sensor.setSensors(sensor1);
               MemoryPersistence persistence = new MemoryPersistence();
               try{
                    JSONConvert.SensorToJSON(sensor,FilePath);
                    MqttClient client = new MqttClient(broker, ClientID, persistence);
                    //MqttConnectOptions options = new MqttConnectOptions();
                    //options.setCleanSession(true);
                    //System.out.println("Broker: \t \n" + broker);
                    //client.connect(options);
                    //System.out.println("Connetcted\n");
                    //System.out.println("Messeage sent: \t \n" + content);
                    //MqttMessage message = new MqttMessage(content.getBytes());
                    //message.setQos(qos);
                    //client.publish(topic,message);
                    //System.out.println("Meassage published\n");
                    WaterSensor sus = JSONConvert.JSONToSensor(FilePath);
                    List<Sensor> sensors2 = sus.getSensors();
                    for (Sensor value : sensors2) {
                         System.out.println("----------------------------------------------");
                         System.out.println("**********************************************");
                         System.out.println("TOPIC: " + sensor.getTopic());
                         System.out.println("BROKER: " + sensor.getBroker());
                         System.out.println("CLIENT ID: " + sensor.getClientID());
                         System.out.println("**********************************************");
                         System.out.println("DATA NAME: " + value.getDataName());
                         System.out.println("DATA TYPE: " + value.getDataType());
                         System.out.println("FACTOR: " + value.getFactor());
                         System.out.println("UNIT: " + value.getUnit());
                         System.out.println("SIZE: " + value.getSize());
                         System.out.println("----------------------------------------------");
                    }


                    System.out.println("*********************************************");
                    System.out.println("JSON READING IS OVER!");
                    System.out.println("*********************************************");
                    Thread.sleep(5000);
               } catch (MqttException error){
                    System.out.println("reason " + error.getReasonCode());
                    System.out.println("msg " + error.getMessage());
                    System.out.println("loc " + error.getLocalizedMessage());
                    System.out.println("cause " + error.getCause());
                    System.out.println("except " + error);
                    error.printStackTrace();
               }
          }
     }

     public static List<Sensor> select_sensors(){
          System.out.println("Please enter the following numbers for the following sensors: \n 1 -> Temperature \n 2 -> Pressure \n 3 -> Usage \n 4 -> Bigger Usage \n 5 -> Exit \n");
          int option;
          Scanner sc = new Scanner(System.in);
          List<Sensor> sensors = new ArrayList<>();
          boolean flag = false;
          while(true){
               option = sc.nextInt();
               switch (option){
                    case 1:
                         sensors.add(new Sensor("temperatura", "int16", 10, "C"));
                         System.out.println("Temperature sensor added!\n");
                         break;
                    case 2:
                         sensors.add(new Sensor("tlak", "uint16", 1000, "Bar"));
                         System.out.println("Pressure sensor added!\n");
                         break;
                    case 3:
                         sensors.add(new Sensor("potrosnja", "uint16", 0, "litra"));
                         System.out.println("Usage added!\n");
                         break;
                    case 4:
                         sensors.add(new Sensor("Veca Potrosnja", "uint16", 10, "m3"));
                         System.out.println("Bigger Usage added!\n");
                         break;
                    case 5:
                         System.out.println("Exiting options!\n");
                         flag = true;
                         break;
               default:
                    System.out.println("Wrong option!\n");
                    break;
               }
               if(flag){
                    return  sensors;
               }
          }
     }

     public List<Sensor> getSensors(){
          return this.sensors;
     }

     public void setSensors(List<Sensor> sensors){
          this.sensors = sensors;
     }

     public String getTopic(){
          return this.topic;
     }

     public String getBroker(){
          return this.broker;
     }

     public String getClientID(){
          return this.ClientID;
     }

     public void setTopic(String topic){
          this.topic = topic;
     }

     public void setBroker(String broker){
          this.broker = broker;
     }

     public void setClientID(String ClientID){
          this.ClientID = ClientID;
     }
}
